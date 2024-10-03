package com.example.bottomnavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment(), EditFragment.OnSaveClickListener {

    private lateinit var textViewName: TextView
    private lateinit var textViewGender: TextView
    private lateinit var textViewPreferredLanguages: TextView
    private lateinit var buttonEdit: Button

    private var name: String = "Kurtz Malang"
    private var gender: String = "God/King"
    private var preferredLanguages: String = "Japanese"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.profile_fragment, container, false)

        textViewName = view.findViewById(R.id.textName)
        textViewGender = view.findViewById(R.id.textGender)
        textViewPreferredLanguages = view.findViewById(R.id.textPreferredLanguage)
        buttonEdit = view.findViewById(R.id.btnEdit)

        // Set initial values
        updateProfileViews()

        buttonEdit.setOnClickListener {
            val editFragment = EditFragment()
            editFragment.onSaveClickListener = this // Set the listener
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, editFragment)
                .addToBackStack(null)
                .commit()
        }

        return view
    }

    private fun updateProfileViews() {
        textViewName.text = name
        textViewGender.text = gender
        textViewPreferredLanguages.text = preferredLanguages
    }

    override fun onSave(updatedName: String, updatedGender: String, updatedLanguages: List<String>) {
        name = updatedName
        gender = updatedGender
        preferredLanguages = updatedLanguages.joinToString(", ")
        updateProfileViews() // Update the UI
    }
}
