package com.cieslak.lab03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckedTextView
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    var myStrings : MutableList<String> = mutableListOf()
    var index = 0
    lateinit var adapter: ArrayAdapter<String>
    lateinit var adapterRemove: ArrayAdapter<String>
    lateinit var addOneButton: Button;
    lateinit var removeButton: Button;
    lateinit var cancelButton: Button;
    lateinit var deleteButton: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.setListViewType(false)

        addOneButton = findViewById(R.id.buttonAdd);
        removeButton = findViewById(R.id.buttonRemove);
        cancelButton = findViewById(R.id.buttonCancel);
        deleteButton = findViewById(R.id.buttonDelete);

    }

    fun add(v: View) {
        myStrings.add("Item " + index)
        index++;
        adapter.notifyDataSetChanged()
    }

    fun remove(v: View) {
        this.setListViewType(true)
        addOneButton.visibility = View.GONE;
        removeButton.visibility = View.GONE;
        cancelButton.visibility = View.VISIBLE;
        deleteButton.visibility = View.VISIBLE;
    }

    fun cancel (v: View) {
        this.setListViewType(false)
        cancelButton.visibility = View.GONE;
        deleteButton.visibility = View.GONE;
        addOneButton.visibility = View.VISIBLE;
        removeButton.visibility = View.VISIBLE;
    }

    fun setListViewType(lV: Boolean) {
        val listView : ListView = findViewById(R.id.listView)
        if (lV) {
            adapterRemove = ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_multiple_choice, myStrings)
            listView.adapter = adapterRemove
            listView.setOnItemClickListener { parent, view, position, id ->
                Log.d("TagName", "position:$position id:$id")
                var element = view as CheckedTextView
                element.isChecked = !element.isChecked
            }
        } else {
            adapter = ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,
                    myStrings)
            listView!!.adapter = adapter
            listView.setOnItemClickListener { parent, view, position, id ->
                Log.d("TagName", "position:$position id:$id")
                var element = adapter.getItem(position)
                adapter.remove(element)
                adapter.notifyDataSetChanged()
            }
        }
    }

    fun delete(v: View) {
        val listView : ListView = findViewById(R.id.listView)
        for (i in 0 until myStrings.size) {
            var v = listView.getChildAt(i) as CheckedTextView;
            if(v != null && v.isChecked) {
                adapterRemove.remove(v.text as String);
            }
        }
        this.setListViewType(false);
    }
}