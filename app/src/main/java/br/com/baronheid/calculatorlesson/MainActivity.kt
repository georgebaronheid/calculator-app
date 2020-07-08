package br.com.baronheid.calculatorlesson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var resultEditText: EditText
    private lateinit var numberInputEditText: EditText
    private val operationTextView
            by lazy(LazyThreadSafetyMode.NONE) { findViewById<TextView>(R.id.operation_main_activity) }

    //    Variáveis para guardar os operandos e tipos de cálculos
    private var operandOne: Double? = null
    private var operandTwo: Double = 0.0
    private var pendingOperation = "="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resultEditText = findViewById(R.id.result_main_activity)
        numberInputEditText = findViewById(R.id.number_input_main_activity)

        val numberButtonArray = arrayOfNumberButtons()


        //        Operation Buttons
        val buttonEquals = findViewById<Button>(R.id.button_equals)
        val buttonPlus = findViewById<Button>(R.id.button_plus)
        val buttonMinus = findViewById<Button>(R.id.button_minus)
        val buttonDivide = findViewById<Button>(R.id.button_divide)
        val buttonMultiply = findViewById<Button>(R.id.button_multiply)
        val operationsList =
            arrayOf(buttonEquals, buttonPlus, buttonMinus, buttonDivide, buttonMultiply)


        val listener = View.OnClickListener { v ->
            val b = v as Button
            numberInputEditText.append(b.text)
        }

        numberButtonArray.forEach {
            it.setOnClickListener(listener)
        }

        val operationListener = View.OnClickListener { v ->
            val operation = (v as Button).text.toString()
            val value = numberInputEditText.text.toString()

            if (value.isNotEmpty()) performOperation(value, operation)

            pendingOperation = operation
            operationTextView.text = pendingOperation
        }
        operationsList.forEach {
            it.setOnClickListener(operationListener)
        }


    }

    private fun arrayOfNumberButtons(): Array<Button> {
        //        Data input buttons
        val buttonZero: Button = findViewById(R.id.button_0)
        val buttonOne: Button = findViewById(R.id.button_1)
        val buttonTwo: Button = findViewById(R.id.button_2)
        val buttonThree: Button = findViewById(R.id.button_3)
        val buttonFour: Button = findViewById(R.id.button_4)
        val buttonFive: Button = findViewById(R.id.button_5)
        val buttonSix: Button = findViewById(R.id.button_6)
        val buttonSeven: Button = findViewById(R.id.button_7)
        val buttonEight: Button = findViewById(R.id.button_8)
        val buttonNine: Button = findViewById(R.id.button_9)
        val buttonDot: Button = findViewById(R.id.button_dot)

        return arrayOf(
            buttonZero,
            buttonOne,
            buttonTwo,
            buttonThree,
            buttonFour,
            buttonFive,
            buttonSix,
            buttonSeven,
            buttonEight,
            buttonNine,
            buttonDot
        )
    }

    private fun performOperation(value: String, operation: String) {
        operationTextView.text = operation
    }
}