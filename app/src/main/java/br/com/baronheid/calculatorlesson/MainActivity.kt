package br.com.baronheid.calculatorlesson

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.set
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG = "MainActivity"
private const val OPERAND_ONE = "OperandOne"
private const val PENDING_OPERATION = "PendingOperation"
private const val OPERAND_ONE_STORED = "OperandOneStorede"

class MainActivity : AppCompatActivity() {

    //    Variáveis para guardar os operandos e tipos de cálculos
    private var operandOne: Double? = null
    private var pendingOperation = "="

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate: Called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val numberButtonsArray = arrayOfNumberButtons()
        val operationButtonsArray = arrayOfOperationButtons()

        val buttonInputListener = View.OnClickListener { v ->
            val buttonValue = v as Button
            number_input_main_activity.append(buttonValue.text)
        }

        val operationListener = View.OnClickListener { v ->
            val operation = (v as Button).text.toString()
            if (operation == "Neg") negateNumber(number_input_main_activity.text.toString())
            else {
                try {
                    performOperation(
                        number_input_main_activity.text.toString().toDouble(),
                        operation
                    )
                } catch (e: NumberFormatException) {
                    number_input_main_activity.setText("")
                }
                pendingOperation = operation
                operation_main_activity.text = pendingOperation
            }
        }

        numberButtonsArray.forEach {
            it.setOnClickListener(buttonInputListener)
        }

        operationButtonsArray.forEach {
            it.setOnClickListener(operationListener)
        }
    }

    private fun performOperation(value: Double, operation: String) {
        if (operandOne == null) operandOne = value
        else {
            if (pendingOperation == "=") pendingOperation = operation

            when (pendingOperation) {
                "=" -> operandOne = value
                "/" ->
                    operandOne = if (value == 0.0) Double.NaN
                    else operandOne!! / value
                "*" -> operandOne = operandOne!! * value
                "-" -> operandOne = operandOne!! - value
                "+" -> operandOne = operandOne!! + value
            }
        }
        result_main_activity.setText(operandOne.toString())
        number_input_main_activity.setText("")
    }

    private fun negateNumber(value: String) {
        if (value == "") number_input_main_activity.setText("-")
        else {
            number_input_main_activity.setText((value.toDouble() * -1).toString())
        }
    }

    private fun arrayOfNumberButtons(): Array<Button> {
        return arrayOf(
            button_0, button_1, button_2, button_3, button_4, button_5, button_6,
            button_7, button_8, button_9, button_dot
        )
    }

    private fun arrayOfOperationButtons(): Array<Button> {
        return arrayOf(
            button_equals,
            button_plus,
            button_minus,
            button_divide,
            button_multiply,
            button_neg
        )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d(TAG, "onSaveInstanceState: Called")
        super.onSaveInstanceState(outState)

        if (operandOne != null) {
            outState.putDouble(OPERAND_ONE, operandOne!!)
            outState.putBoolean(OPERAND_ONE_STORED, true)
            Log.d(TAG, "outState.putString for operandOne set with value $operandOne")
        }


        outState.putString(PENDING_OPERATION, pendingOperation)
        Log.d(TAG, "outState.putString for pendingOperation set with value $pendingOperation")
        Log.d(TAG, "onSaveInstanceState: Concluded \n")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        Log.d(TAG, "onRestoreInstanceState: Called")
        super.onRestoreInstanceState(savedInstanceState)

        operandOne = if (savedInstanceState.getBoolean(OPERAND_ONE_STORED, false)) {
            savedInstanceState.getDouble(OPERAND_ONE)
        } else null
        Log.d(
            TAG,
            "savedInstanceState.getString for resultEditText retrieved with value $operandOne"
        )
        Log.d(
            TAG,
            "savedInstanceState.getString for pendingOperation retrieved with value $pendingOperation"
        )

        result_main_activity.setText(operandOne.toString())
        pendingOperation = savedInstanceState.getString(PENDING_OPERATION)!!
        operation_main_activity.text = pendingOperation
        if (result_main_activity == null) result_main_activity.setText("")

        Log.d(TAG, "onRestoreInstanceState: Concluded")
    }
}