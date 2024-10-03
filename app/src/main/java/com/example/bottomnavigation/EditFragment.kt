package com.example.bottomnavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment

class EditFragment : Fragment() {

    private lateinit var editTextName: EditText
    private lateinit var radioGroupGender: RadioGroup
    private lateinit var checkBoxKotlin: CheckBox
    private lateinit var checkBoxJava: CheckBox
    private lateinit var buttonSave: Button

    var onSaveClickListener: OnSaveClickListener? = null

    interface OnSaveClickListener {
        fun onSave(name: String, gender: String, preferredLanguages: List<String>)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit, container, false)

        editTextName = view.findViewById(R.id.editTextName)
        radioGroupGender = view.findViewById(R.id.radioGroupGender)
        checkBoxKotlin = view.findViewById(R.id.checkBoxKotlin)
        checkBoxJava = view.findViewById(R.id.checkBoxJava)
        buttonSave = view.findViewById(R.id.buttonSave)

        // Pre-fill the fields with existing data
        editTextName.setText("Kurtz Malang")
        radioGroupGender.check(R.id.radioMale) // Assuming the ID for Male radio button is radioMale
        checkBoxKotlin.isChecked = true
        checkBoxJava.isChecked = false

        buttonSave.setOnClickListener {
            val name = editTextName.text.toString()
            val selectedGenderId = radioGroupGender.checkedRadioButtonId
            val gender = if (selectedGenderId != -1) {
                view.findViewById<RadioButton>(selectedGenderId).text.toString()
            } else {
                "Not specified"
            }

            val preferredLanguages = mutableListOf<String>()
            if (checkBoxKotlin.isChecked) preferredLanguages.add("Japanese")
            if (checkBoxJava.isChecked) preferredLanguages.add("Chinese")

            // Pass the updated data back to ProfileFragment
            onSaveClickListener?.onSave(name, gender, preferredLanguages)

            // Navigate back to ProfileFragment
            parentFragmentManager.popBackStack()
        }

        return view
    }
}
