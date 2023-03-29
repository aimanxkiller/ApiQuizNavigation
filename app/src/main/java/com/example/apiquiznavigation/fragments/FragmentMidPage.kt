package com.example.apiquiznavigation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.apiquiznavigation.R
import com.example.apiquiznavigation.adapter.MyAdapterFragment
import com.example.apiquiznavigation.viewmodel.ViewModelQuestions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class FragmentMidPage : Fragment(),FragmentCommunicator {

    private val viewModel: ViewModelQuestions by activityViewModels()
    private lateinit var adapter:MyAdapterFragment
    private lateinit var pager:ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mid_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        pager = view.findViewById(R.id.view_pager2_fragment)

        lifecycleScope.launch(Dispatchers.Main) {
            val x = async {
                return@async viewModel.getQuestions().body()!!
            }
            adapter = MyAdapterFragment(this@FragmentMidPage,x.await())
            viewModel.countQ = IntArray(x.await().size)
            viewModel.scoreQ = IntArray(x.await().size)
            pager.adapter = adapter
        }

    }

    override fun onDataPass(score: Int, count: Int, position: Int) {
        viewModel.scoreQ!![position] = score
        viewModel.countQ!![position] = count
    }

    override fun onPagePass(page: Int) {
        pager.currentItem = page+1
        if (page==4){
            if(viewModel.countQ!!.sum()==5){
                findNavController().navigate(R.id.action_fragmentMidPage_to_fragmentEndPage)
            }else{
                Toast.makeText(requireContext(),"Please answer all question !", Toast.LENGTH_SHORT).show()
            }
        }
    }
}