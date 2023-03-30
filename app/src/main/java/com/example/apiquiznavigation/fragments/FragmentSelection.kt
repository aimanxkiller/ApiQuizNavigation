package com.example.apiquiznavigation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.apiquiznavigation.R
import com.example.apiquiznavigation.viewmodel.ViewModelQuestions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import retrofit2.await
import retrofit2.awaitResponse

@AndroidEntryPoint
class FragmentSelection : Fragment() {

    private lateinit var spinner:Spinner
    private val viewModel:ViewModelQuestions by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val buttonStart = view.findViewById<Button>(R.id.buttonStart)
        spinner = view.findViewById(R.id.dropdownList)

        lifecycleScope.launch {
            val response = viewModel.repoCategories()
            spinnerFiller(response)
        }

        buttonStart.setOnClickListener {
            if(viewModel.selection != null) {
                findNavController().navigate(R.id.action_fragmentSelection_to_fragmentMidPage)
            }else{
                Toast.makeText(requireContext(),"Please select a category",Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun spinnerFiller(x: Map<String, ArrayList<String>>?){
        val categories = arrayListOf<String>()

        x!!.entries.forEach {
            categories.add(it.key)
        }

        val arrayAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_dropdown_item,categories)
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.setPick(x[categories[position]]!![0])
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

}