package com.cieslak.lab04

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckedTextView
import android.widget.ListView

class StudentsListActivity : AppCompatActivity() {
    var studentsList: MutableList<Student> = mutableListOf();
    var index = 0;
    lateinit var adapter: StudentsAdapter;
    lateinit var studentsListView: ListView;
    lateinit var addButton: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_students_list)

        adapter = StudentsAdapter(this, studentsList)
        studentsListView = findViewById(R.id.StudentsListView)
        studentsListView!!.adapter = adapter

        val intent = Intent(this, AddStudentActivity::class.java)
        addButton = findViewById<Button>(R.id.addButton)

        addButton.setOnClickListener {
            startActivityForResult(intent,1)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === 1) { //drugi argument funkcji startActivityForResult
            if (resultCode === Activity.RESULT_OK) {
                var resultName: String? = data!!.getStringExtra("name")
                var resultPhone: String? = data!!.getStringExtra("phone")
                if(resultName != null && resultName is String && resultPhone != null && resultPhone is String) {
                    studentsList.add(Student(index, resultName, resultPhone))
                    index++
                    adapter.notifyDataSetChanged()
                }
                //Obsługa rezultatów które otrzymaliśmy z wywołanej aktywności
            }
            if (resultCode === Activity.RESULT_CANCELED) {
                //W przpyadku otrzymania błędnych rezultatów
            }
        }
    }
}