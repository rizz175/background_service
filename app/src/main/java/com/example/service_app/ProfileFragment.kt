package com.example.service_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.appcompat.widget.AppCompatSpinner
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {

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
        return view
    }


    private fun clearSpinnerData() {
        items.clear()
        adapter.notifyDataSetChanged()
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
