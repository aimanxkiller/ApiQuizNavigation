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
import com.example.apiquiznavigation.models.QuizCat
import com.example.apiquiznavigation.viewmodel.ViewModelQuestions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

//https://www.youtube.com/watch?v=1_WHwXAjkN0
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
            val x = viewModel.getCategories().awaitResponse()
            if(x.isSuccessful){
                spinnerFiller(x.body()!!)
            }else{
                Toast.makeText(requireContext(),"Network Issue",Toast.LENGTH_SHORT).show()
            }

        }

        buttonStart.setOnClickListener {
            if(viewModel.selection != null) {
                findNavController().navigate(R.id.action_fragmentSelection_to_fragmentMidPage)
            }else{
                Toast.makeText(requireContext(),"Please select a category",Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun spinnerFiller(x:QuizCat){
        val categoryY = x.getTitle()
        val categoryX = x.getDetails()

        val arrayAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_dropdown_item,categoryX)
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val y = categoryY[position].split(",")
                viewModel.setPick(y[0])
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

}