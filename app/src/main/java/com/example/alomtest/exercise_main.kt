package com.example.alomtest

import SharedPreferenceUtils
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.view.marginLeft
import com.example.alomtest.databinding.FragmentExerciseBinding
import com.example.alomtest.databinding.FragmentExerciseMainBinding
import com.example.alomtest.databinding.FragmentMypageBodyInformationBinding


class exercise_main : Fragment() {
    private lateinit var binding:FragmentExerciseMainBinding
    //private val binding get() = _binding!!

    lateinit var routininfo:LinearLayout
    var cnt=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=FragmentExerciseMainBinding.inflate(layoutInflater)


        binding.addbtn.setBackgroundResource(R.drawable.exercise_routine_background_design)


        routininfo=binding.routineInfoLayout
        val addbutton=binding.addbtn
        addbutton.setOnClickListener {
            addnewbtn()
            most_back_btn()





        }


    }

//+버튼을 누르면 버튼을 추가하는 코드
    private fun addnewbtn() {
        val newTextView = Button(context)
        newTextView.text="${++cnt}번버튼"
        newTextView.setBackgroundResource(R.drawable.exercise_routine_background_design)
//    newTextView.height=MATCH_PARENT
//    newTextView.width=MATCH_PARENT

    newTextView.layoutParams = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.MATCH_PARENT
    )

    newTextView.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
    newTextView.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
    newTextView.layoutParams = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.MATCH_PARENT
    )
    val params = newTextView.layoutParams as ViewGroup.MarginLayoutParams
    params.leftMargin = 10
    params.rightMargin=10
    newTextView.layoutParams=params

        routininfo.addView(newTextView)







    }
    private fun most_back_btn() {
        val most_back_btn = binding.addbtn

        // Check if Tx2TextView is in the layout
        if (most_back_btn != null) {
            // Remove Tx2TextView
            routininfo.removeView(most_back_btn)

            // Add Tx2TextView to the end
            routininfo.addView(most_back_btn)
        }
    }



private var _binding: FragmentExerciseMainBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        _binding = FragmentExerciseMainBinding.inflate(inflater,container,false)
        return binding.root
        //return inflater.inflate(R.layout.fragment_mypage_body_measurement_editmode, container, false)
    }

    companion object {


    }
}