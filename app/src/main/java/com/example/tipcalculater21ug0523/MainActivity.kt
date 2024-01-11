package com.example.tipcalculater21ug0523

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {
    private lateinit var editTextBillAmount: EditText
    private lateinit var radioGroupTipOptions: RadioGroup
    private lateinit var editTextCustomTip: EditText
    private lateinit var buttonCalculate: Button
    private lateinit var textViewTipAmount: TextView
    private lateinit var textViewTotalAmount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextBillAmount = findViewById(R.id.editTextBillAmount)
        radioGroupTipOptions = findViewById(R.id.radioGroupTipOptions)
        editTextCustomTip = findViewById(R.id.editTextCustomTip)
        buttonCalculate = findViewById(R.id.buttonCalculate)
        textViewTipAmount = findViewById(R.id.textViewTipAmount)
        textViewTotalAmount = findViewById(R.id.textViewTotalAmount)

        buttonCalculate.setOnClickListener {
            calculateTip()
        }


        radioGroupTipOptions.setOnCheckedChangeListener { _, checkedId ->
            editTextCustomTip.isEnabled = checkedId == -1
        }


        editTextBillAmount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                calculateTip()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun calculateTip() {
        val billAmount = editTextBillAmount.text.toString().toDoubleOrNull() ?: 0.0
        val tipPercentage = when (radioGroupTipOptions.checkedRadioButtonId) {
            R.id.radioButton10Percent -> 0.10
            R.id.radioButton15Percent -> 0.15
            R.id.radioButton20Percent -> 0.20
            else -> editTextCustomTip.text.toString().toDoubleOrNull() ?: 0.0
        }

        val tipAmount = billAmount * tipPercentage
        val totalAmount = billAmount + tipAmount

        val currencyFormat = DecimalFormat("#0.00")

        textViewTipAmount.text = "Tip Amount: RS" + currencyFormat.format(tipAmount)
        textViewTotalAmount.text = "Total Amount: RS" + currencyFormat.format(totalAmount)
    }
}