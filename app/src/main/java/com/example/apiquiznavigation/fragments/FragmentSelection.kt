package com.example.apiquiznavigation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.apiquiznavigation.R
import com.example.apiquiznavigation.models.QuizCat
import com.example.apiquiznavigation.viewmodel.ViewModelQuestions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

//https://www.youtube.com/watch?v=1_WHwXAjkN0
@AndroidEntryPoint
class FragmentSelection : Fragment() {

    private lateinit var spinner:Spinner
    private var selection:String? = null
    private lateinit var viewModel:ViewModelQuestions

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewModel = ViewModelProvider(requireActivity())[ViewModelQuestions::class.java]
        val buttonStart = view.findViewById<Button>(R.id.buttonStart)
        spinner = view.findViewById(R.id.dropdownList)

        lifecycleScope.launch {
            val x = viewModel.getCategories().awaitResponse()
            if(x.isSuccessful){
                spinnerFiller(x.body()!!)
            }else{
                Toast.makeText(requireContext(),"Network Issue",Toast.LENGTH_SHORT).show()
            }

//            val b = viewModel.getQuestions("science")
//            if(b.isSuccessful){
//                val listA = b.body()!!
//                listA.forEachIndexed { index, questionModelItem ->
//                    Log.e("Q $index",listA[index].question)
//                }
//            }

        }

        buttonStart.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentSelection_to_fragmentMidPage)
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
                selection = y[0]
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

}