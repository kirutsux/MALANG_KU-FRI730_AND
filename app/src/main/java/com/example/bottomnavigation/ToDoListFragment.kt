package com.example.bottomnavigation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ListView
import androidx.fragment.app.Fragment

class ToDoListFragment : Fragment() {

    private lateinit var edtSearch: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.todolist_fragment, container, false)

        edtSearch = view.findViewById(R.id.edtSearch)
        val itemList = mutableListOf(
            ToDoListItem(R.drawable.a, "I am Jericho Loise Batal.", false),
            ToDoListItem(R.drawable.b, "I am Elizabeth Olsen.", true),
            ToDoListItem(R.drawable.c, "I am Madelyn Cline.", false),
            ToDoListItem(R.drawable.d, "I am Joniedel Baltunado.", false),
            ToDoListItem(R.drawable.e, "I am Ungas kaayo ka", false)
        )

        // Find the ListView and set the adapter
        val listView = view.findViewById<ListView>(R.id.toDoListView)
        val adapter = ToDoList_Adapter(requireContext(), itemList)
        listView.adapter = adapter

        edtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter(s.toString())
            }
        })

        return view
    }
}
