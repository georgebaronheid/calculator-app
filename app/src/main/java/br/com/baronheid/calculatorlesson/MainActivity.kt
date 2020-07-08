package br.com.baronheid.calculatorlesson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var resultEditText: EditText
    private val operationTextView
            by lazy(LazyThreadSafetyMode.NONE) { findViewById<TextView>(R.id.operation_main_activity) }

    //    Variáveis para guardar os operandos e tipos de cálculos
    private var operandOne: Double? = null
    private var operandTwo: Double = 0.0
    private var pendingOperation = "="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val numberButtonsArray = arrayOfNumberButtons()
        val operationButtonsArray = arrayOfOperationButtons()

        val buttonInputListener = View.OnClickListener { v ->
            val b = v as Button
            number_input_main_activity.append(b.text)
        }

        numberButtonsArray.forEach {
            it.setOnClickListener(buttonInputListener)
        }

        val operationListener = View.OnClickListener { v ->
            val operation = (v as Button).text.toString()
            val value = number_input_main_activity.text.toString()

            if (value.isNotEmpty()) performOperation(value, operation)

            pendingOperation = operation
            operationTextView.text = pendingOperation
        }
        operationButtonsArray.forEach {
            it.setOnClickListener(operationListener)
        }
    }

    private fun arrayOfNumberButtons(): Array<Button> {
        return arrayOf(
            button_0, button_1, button_2, button_3, button_4, button_5, button_6,
            button_7, button_8, button_9, button_dot
        )
    }

    private fun arrayOfOperationButtons(): Array<Button> {
        return arrayOf(button_equals, button_plus, button_minus, button_divide, button_multiply)
    }

    private fun performOperation(value: String, operation: String) {
        operationTextView.text = operation
    }
}