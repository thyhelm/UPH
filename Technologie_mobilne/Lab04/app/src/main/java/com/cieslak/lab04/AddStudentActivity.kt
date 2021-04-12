package com.cieslak.lab04

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.Button
import android.widget.TextView

class AddStudentActivity : AppCompatActivity() {
    lateinit var getNumberButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        getNumberButton = findViewById(R.id.getNumberButton)
        val intentContact = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        intentContact.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);

        getNumberButton.setOnClickListener{
            startActivityForResult(intentContact, 1)
        }
    }

    fun onFinishAddClick(view: View?) {
        val textFieldName = findViewById<TextView>(R.id.StudentNameEditText)
        val textFieldPhoneNumber = findViewById<TextView>(R.id.EditTextPhoneNumber)
        val returnIntent = intent
        returnIntent.putExtra("name", textFieldName.text.toString())
        returnIntent.putExtra("phone", textFieldPhoneNumber.text.toString())
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === 1) {
            if (resultCode === Activity.RESULT_OK) {
                // Pobieramy URI przekazany z książki adresowej do wybranego kontaktu
                val contactUri: Uri? = data!!.getData()
                // Potrzebujemy tylko imienia
                val projection = arrayOf(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER)
                // Powinniśmy to robić w oddzielnym wątku bo operacje z użyciem kursora mogą być
                // czasochłonne. Można wykorzystać klasę CursorLoader.
                val cursor: Cursor? = contentResolver
                    .query(contactUri!!, projection, null, null, null)
                //pobranie pierwszego wiersza
                cursor!!.moveToFirst()
                // Pobranie kolumny o odpowiednim indeksie
                val column: Int =
                    cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                val displayName: String = cursor.getString(column)
                val tv = findViewById<View>(R.id.StudentNameEditText) as TextView
                tv.text = displayName
            }
        }
    }
}