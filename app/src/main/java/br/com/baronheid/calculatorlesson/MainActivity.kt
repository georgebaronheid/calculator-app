package br.com.baronheid.calculatorlesson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var resultEditText: EditText
    private lateinit var numberInputEditText: EditText
    private val operationTextView by lazy { findViewById<TextView>(R.id.operation_main_activity)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}