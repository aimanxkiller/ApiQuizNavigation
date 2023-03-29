package com.example.apiquiznavigation.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.children
import com.example.apiquiznavigation.R
import com.example.apiquiznavigation.models.QuestionModelItem

interface FragmentCommunicator{
    fun onDataPass(score: Int,count:Int,position:Int)
    fun onPagePass(page:Int)
}

class FragmentQuestions(list: List<QuestionModelItem>, position: Int, private val contextSend: FragmentMidPage) : Fragment() {
    private var listener: FragmentCommunicator? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (contextSend is FragmentCommunicator){
            listener=contextSend
        }else
            throw java.lang.ClassCastException("$context must implement FragmentCommunicator")
    }

    private var listA = list
    private var pos = position

    lateinit var text: TextView
    lateinit var radioGroup: RadioGroup
    lateinit var radioButton: RadioButton
    lateinit var buttonL: Button
    lateinit var buttonR: Button

    private lateinit var answerCorrect:String
    private var score:Int =0
    private var count:Int =0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment__questions, container, false)

        text = view.findViewById(R.id.textQuestion2)
        radioGroup = view.findViewById(R.id.radioGroup2)
        buttonL = view.findViewById(R.id.buttonFragLeft)
        buttonR= view.findViewById(R.id.buttonFragRight)

        text.text = "${pos+1}. " +listA[pos].question
        radioSettings(getAnswerCollection(listA[pos]))

        setButtons()

        return view
    }

    private fun setButtons() {
        buttonR.text = "Next"
        buttonL.text = "Previous"
        buttonL.setOnClickListener {
            listener?.onPagePass(pos-2)
        }
        buttonR.setOnClickListener {
            listener?.onPagePass(pos)
        }
        when (pos){
            0 ->{
                buttonL.visibility = View.INVISIBLE
            }
            (listA.size - 1) ->{
                buttonR.text = "Finish"
                buttonR.setOnClickListener {
                    listener?.onPagePass(pos)
                }
            }
            else ->{
                buttonL.visibility = View.VISIBLE
            }
        }
    }

    private fun radioSettings(answerCollection: MutableList<String>){
        radioGroup.children.forEachIndexed { index, view ->
            radioButton = view as RadioButton
            radioButton.text = answerCollection[index]
        }

        //updated no nesting required
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val radio: RadioButton = radioGroup.findViewById(checkedId)
            count = 1
            score = if(radio.text.toString().equals(answerCorrect,true)){
                1
            }else{
                0
            }
            listener?.onDataPass(score,count,pos)
        }
    }

    private fun getAnswerCollection(x:QuestionModelItem): MutableList<String> {
        answerCorrect = x.correctAnswer
        val answerWrong:List<String> = x.incorrectAnswers
        val answerCollect = answerWrong + x.correctAnswer  //Getting answer collections and shuffling
        val answerShuffle = answerCollect.toMutableList()
        answerShuffle.shuffle()

        return answerShuffle
    }

}