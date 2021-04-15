package com.cieslak.lab05

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var login: EditText
    lateinit var password: EditText
    lateinit var loginButton: Button
    lateinit var remember: CheckBox
    private val PREFERENCES_NAME = "Lab05"
    private val LOGIN_KEY = "LOGIN_KEY"
    private val PASSWORD_KEY = "PASSWORD_KEY"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login = findViewById(R.id.login)
        password = findViewById(R.id.password)
        loginButton = findViewById(R.id.loginButton)
        remember = findViewById(R.id.remember)

        var preferences = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        login.setText(preferences.getString(LOGIN_KEY, ""))
        password.setText(preferences.getString(PASSWORD_KEY, ""))

        loginButton.setOnClickListener {
            login()
        }
    }

    fun validate()  :Boolean {
        if(login.text.toString().isEmpty()) {
            login.error = getString(R.string.login_empty);
            return false
        }
        if(password.text.toString().isEmpty()) {
            password.error = getString(R.string.password_empty)
            return false
        }
        return true
    }

    fun rememberMe() {
        var preferences = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        try {

            with(preferences.edit()) {
                putString(LOGIN_KEY, login.text.toString())
                putString(PASSWORD_KEY, password.text.toString())
                apply()
            }
        } catch(e :Exception) {
            Toast.makeText(this, getText(R.string.login_data_save_error), Toast.LENGTH_SHORT).show()
        }
    }

    fun cancelRememberMe() {
        var preferences = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        try {

            with(preferences.edit()) {
                putString(LOGIN_KEY, "")
                putString(PASSWORD_KEY, "")
                apply()
            }
        } catch(e :Exception) {
            Toast.makeText(this, getText(R.string.login_data_save_error), Toast.LENGTH_SHORT).show()
        }
    }

    fun login() {
        if(validate()) {
            if (remember.isChecked) rememberMe()
            else cancelRememberMe()
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }
    }
}