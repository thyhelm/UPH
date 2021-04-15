package com.cieslak.lab05

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi

class ListActivity : AppCompatActivity() {
    private val PREFERENCES_NAME = "Lab05"
    private val LOGIN_KEY = "LOGIN_KEY"
    val TABLE_NAME = "debtors"

    var debtorsList: MutableList<Debtor> = mutableListOf();
    var index = 0;
    lateinit var preferences : SharedPreferences;
    lateinit var debtorsListView: ListView;
    lateinit var addContactButton: Button;
    lateinit var adapter: DebtorsAdapter;
    lateinit var db :DebtorsData
    lateinit var dbWriter : SQLiteDatabase

    val launchContactsForResults  =  registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult -> handleActivityResult(result)
    }

    val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            isGranted: Boolean ->
                if (isGranted) {
                    addDebtor()
                }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        db = DebtorsData(this)
        dbWriter = db.writableDatabase
        debtorsList = db.getAllDebtorsList()

        addContactButton = findViewById(R.id.addContactButton)
        adapter = DebtorsAdapter(this, debtorsList)
        debtorsListView = findViewById(R.id.debtorsList)
        debtorsListView!!.adapter = adapter

        preferences = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        setTitle(getString(R.string.title_activity_debtors_list, preferences.getString(LOGIN_KEY, "")))

        addContactButton.setOnClickListener {
            var res = this.checkSelfPermission(Manifest.permission.READ_CONTACTS)

            if(res == PackageManager.PERMISSION_GRANTED) {
                addDebtor()
            } else {
                requestPermissionLauncher.launch(Manifest.permission.READ_CONTACTS)
            }
        }

        debtorsListView.setOnItemClickListener {
                adapterView: AdapterView<*>, view1: View, position: Int, l: Long ->

            var element = adapter.getItem(position)
            db.writableDatabase.delete(TABLE_NAME, _ID + "=?", arrayOf(element.id.toString()))
            adapter.debtors = db.getAllDebtorsList()
            adapter.notifyDataSetChanged()
        }
    }

    fun handleActivityResult(result: ActivityResult) {
        if (result.resultCode == Activity.RESULT_OK) {
            val contactUri: Uri? = result.data!!.getData()
            val projection = arrayOf(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Photo.PHOTO_URI)
            val cursor: Cursor? = contentResolver.query(contactUri!!, projection, null, null, null)
            cursor!!.moveToFirst()
            var column: Int = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
            val name: String = cursor.getString(column)
            column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
            val number: String = cursor.getString(column)
            column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Photo.PHOTO_URI)
            val photoUri: String? = cursor.getString(column)


//            debtorsList.add(Debtor(index, name, number, photoUri))
//            index++
//            adapter.notifyDataSetChanged()


            val values = ContentValues()
            values.put(DEBTOR_NAME, name)
            values.put(DEBTOR_PHONE, number)
            values.put(DEBTOR_PHOTO_URI, photoUri)
            dbWriter.insert(TABLE_NAME, null, values)
            adapter.debtors = db.getAllDebtorsList()
            adapter.notifyDataSetChanged()
        }
    }

    fun addDebtor() {
        val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
        intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE)
        launchContactsForResults.launch(intent)
    }
}