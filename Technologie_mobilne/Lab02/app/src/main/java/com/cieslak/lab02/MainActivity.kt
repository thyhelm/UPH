package com.cieslak.lab02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.cieslak.lab02.models.CodeManager

class MainActivity : AppCompatActivity() {
    var cm = CodeManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        cm.name = "No name"
        cm.counter = 3

        var editTextName = findViewById<EditText>(R.id.EditTextName)
        editTextName.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                cm.name = p0.toString()
            }
        })

        var displayText = findViewById<TextView>(R.id.TextViewDisplay)
        displayText.text = "xDDD"

        var buttonAddOne = findViewById<Button>(R.id.ButtonAddOne)
        buttonAddOne.setOnClickListener {
            cm.increaseCounter()
            displayText.text = cm.showMotivationText()
        }
    }

    public fun cleanCounter(v: View){
        cm.cleanCounter()
        var displayText = findViewById<TextView>(R.id.TextViewDisplay)
        displayText.text = cm.showMotivationText()
    }
}