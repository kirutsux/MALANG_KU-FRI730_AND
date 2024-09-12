package com.example.to_do_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    private lateinit var edtSearch: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtSearch = findViewById(R.id.edtSearch)
        val itemList = mutableListOf(
            ToDoListItem(R.drawable.a, "I am Jericho Loise Batal.", false),
            ToDoListItem(R.drawable.b, "I am Elizabeth Olsen.", true),
            ToDoListItem(R.drawable.c, "I am Madelyn Cline.", false),
            ToDoListItem(R.drawable.d, "I am Joniedel Baltunado.", false),
            ToDoListItem(R.drawable.e, "I am Ungas kaayo ka", false)
        )

        // Find the ListView and set the adapter
        val listView = findViewById<ListView>(R.id.toDoListView)
        val adapter = ToDoList_Adapter(this, itemList)
        listView.adapter = adapter

        edtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter(s.toString())
            }
        })
    }
}