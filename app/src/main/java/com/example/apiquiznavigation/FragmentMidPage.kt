package com.example.apiquiznavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController


class FragmentMidPage : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mid_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val buttonRight = view.findViewById<Button>(R.id.buttonFragRight)
        val buttonLeft = view.findViewById<Button>(R.id.buttonFragLeft)

        buttonRight.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentMidPage_to_fragmentEndPage)
        }

    }
}