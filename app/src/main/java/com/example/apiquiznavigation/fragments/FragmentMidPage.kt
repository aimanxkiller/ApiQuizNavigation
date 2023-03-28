package com.example.apiquiznavigation.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.apiquiznavigation.R
import com.example.apiquiznavigation.adapter.MyAdapterFragment
import com.example.apiquiznavigation.viewmodel.ViewModelQuestions
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class FragmentMidPage : Fragment(),FragmentCommunicator {

    private val viewModel: ViewModelQuestions by activityViewModels()

    private lateinit var pager:ViewPager2
    private lateinit var countQ:IntArray

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mid_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        pager = view.findViewById(R.id.view_pager2_fragment)

//            val b = viewModel.getQuestions("science")
//            if(b.isSuccessful){
//                val listA = b.body()!!
//                listA.forEachIndexed { index, questionModelItem ->
//                    Log.e("Q $index",listA[index].question)
//                }
//            }



        lifecycleScope.launch {
            val x = async {
                return@async viewModel.getQuestions().body()!!
            }
            val adapter = MyAdapterFragment(this@FragmentMidPage,x.await())
            pager.adapter = adapter
        }

//        Log.e("TestFrag2",viewModel.getPick().toString())

    }

    override fun onDataPass(score: Int, count: Int, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onPagePass(page: Int) {
        Toast.makeText(requireContext(),"Haha",Toast.LENGTH_SHORT).show()
        pager.currentItem = page+1
//        if (page==4){
//            if(countQ.sum()==5){
//                val intent = Intent (context, EndActivity::class.java)
//                intent.putExtra("scoreFin",scoreQ.sum().toString())
//                context.startActivity(intent)
//                finish()
//            }else{
//                Toast.makeText(this,"Please answer all question !", Toast.LENGTH_SHORT).show()
//            }
//        }
    }
}