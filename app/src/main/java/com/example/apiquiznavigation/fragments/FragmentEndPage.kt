package com.example.apiquiznavigation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.apiquiznavigation.R
import com.example.apiquiznavigation.viewmodel.ViewModelQuestions


class FragmentEndPage : Fragment() {

    private val viewModel: ViewModelQuestions by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_end_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val buttonReset = view.findViewById<Button>(R.id.buttonReset)
        val textView = view.findViewById<TextView>(R.id.textView2)

        val myScore = viewModel.scoreQ!!.sum()
        val score = myScore?.times(5)

        textView.text = ("Your final score is \n $score/25")

        buttonReset.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentEndPage_to_fragmentSelection)
        }
    }
}