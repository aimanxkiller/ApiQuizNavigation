package com.example.apiquiznavigation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.apiquiznavigation.R


class FragmentMidPage : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mid_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val pager: ViewPager2 = view.findViewById(R.id.view_pager2_fragment)


    }
}