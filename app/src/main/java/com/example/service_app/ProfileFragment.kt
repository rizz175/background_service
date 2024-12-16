package com.example.service_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.widget.AppCompatSpinner
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {
    private lateinit var radioButton1: RadioButton
    private lateinit var radioButton2: RadioButton
    private lateinit var selectButton1: Button
    private lateinit var selectButton2: Button
    private lateinit var checkAllButton: Button
    private lateinit var radioGroup: RadioGroup
    private lateinit var spinner: AppCompatSpinner
    private lateinit var adapter: ArrayAdapter<String>
    private val items = mutableListOf("Item 1", "Item 2", "Item 3", "Item 4")
    private lateinit var clearButton: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        spinner = view.findViewById(R.id.spinner)
        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        spinner.adapter = adapter
        clearButton = view.findViewById(R.id.clear_button)
        clearButton.setOnClickListener {
            clearSpinnerData()
        }
        radioGroup = view.findViewById(R.id.radio_group)
        radioButton1 = view.findViewById(R.id.radio_button_1)
        radioButton2 = view.findViewById(R.id.radio_button_2)

        selectButton1 = view.findViewById(R.id.select_button_1)
        selectButton2 = view.findViewById(R.id.select_button_2)

        checkAllButton = view.findViewById(R.id.check_all_button)

        selectButton1.setOnClickListener {
            selectRadioButton(radioButton1)
        }

        selectButton2.setOnClickListener {
            selectRadioButton(radioButton2)
        }
        checkAllButton.setOnClickListener {
            checkAllRadioButtons()
        }
        return view
    }

    private fun selectRadioButton(radioButton: RadioButton) {
        if (!radioButton.isChecked) {
            radioGroup.check(radioButton.id)
        }
    }
    private fun clearSpinnerData() {
        items.clear()
        adapter.notifyDataSetChanged()
    }
    private fun checkAllRadioButtons() {
        radioButton1.isChecked = true
        radioButton2.isChecked = true
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */

    }
}
