package com.example.apiquiznavigation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import com.example.apiquiznavigation.fragments.FragmentMidPage
import com.example.apiquiznavigation.fragments.FragmentQuestions
import com.example.apiquiznavigation.models.QuestionModelItem
import com.example.apiquiznavigation.viewmodel.ViewModelQuestions


class MyAdapterFragment(
    private val context: FragmentMidPage,
    private val list: List<QuestionModelItem>
): FragmentStateAdapter(context as Fragment){

    override fun getItemCount(): Int {

        return list.size
    }

    //Updated Here Use Single Fragment
    override fun createFragment(position: Int): Fragment {
        return FragmentQuestions(list,position,context)
    }

    override fun onBindViewHolder(
        holder: FragmentViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        super.onBindViewHolder(holder, position, payloads)
    }
}