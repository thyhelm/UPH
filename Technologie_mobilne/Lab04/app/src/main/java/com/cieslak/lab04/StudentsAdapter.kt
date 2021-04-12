package com.cieslak.lab04

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class StudentsAdapter(context: Context, students: List<Student>): BaseAdapter() {
    private var context = context
    var students = students
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val inflater = LayoutInflater.from(context)
        val row: View = inflater.inflate(R.layout.student_list_item, null)
        val studentName = row.findViewById<TextView>(R.id.studentName)
        studentName.text = getItem(p0).name
        val studentPhone = row.findViewById<TextView>(R.id.studentPhone)
        studentPhone.text = getItem(p0).phone
        return row
    }
    override fun getItem(p0: Int): Student {
        return students.get(p0)
    }
    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }
    override fun getCount(): Int {
        return students.count()
    }
}