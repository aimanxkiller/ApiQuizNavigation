package com.example.apiquiznavigation.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import com.example.apiquiznavigation.fragments.FragmentQuestions
import com.example.apiquiznavigation.models.QuestionModelItem

class MyAdapterFragment(
    activity: AppCompatActivity,
    private val list: List<QuestionModelItem>
): FragmentStateAdapter(activity){

    override fun getItemCount(): Int {
        return list.size
    }

    //Updated Here Use Single Fragment
    override fun createFragment(position: Int): Fragment {
        return FragmentQuestions(list,position)
    }

    override fun onBindViewHolder(
        holder: FragmentViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        super.onBindViewHolder(holder, position, payloads)
    }
}