package com.example.alomtest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.example.alomtest.databinding.FragmentExerciseMainBinding
import com.example.alomtest.exercise.custompage01.add_routine_page
import com.example.alomtest.home.Home


class exercise_main : Fragment() {
    private lateinit var binding:FragmentExerciseMainBinding

    val numbers: Array<Int> = arrayOf(1, 6,11)
    //private val binding get() = _binding!!

    lateinit var routininfo:LinearLayout
    var cnt=0




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=FragmentExerciseMainBinding.inflate(layoutInflater)

        //버튼 위에 겹치는 코드

        //btn2가 editmode
//
//        binding.btn1.setOnLongClickListener {
//            binding.btn2.visibility=View.VISIBLE
//            binding.btn1.visibility=View.GONE
//
//
//            true
//        }
//
//
//        binding.delete.setOnClickListener {
//            removeButton(binding.edit)
//            removeButton(binding.btn1)
//            removeButton(binding.delete)
//            removeLinearLayout(binding.btn2)
//            removeRelativeLayout(binding.allocationBtnLayout)
//        }
//
//
//        //binding.addbtn.setBackgroundResource(R.drawable.exercise_routine_background_design)
//
//
       routininfo=binding.routineInfoLayout
        val addbutton=binding.addbtn
        addbutton.setOnClickListener {

            //일단 기능 해제
            replaceFragment(add_routine_page())



            //addnewbtn_new()
            //most_back_btn()





        }




    }

//+버튼을 누르면 버튼을 추가하는 코드
    private fun addnewbtn_new(){
    var debug_cnt:Int =0

// println("press addnewbtn ${++cnt}")


    val relativeLayout = RelativeLayout(context)



    val params = RelativeLayout.LayoutParams(
        RelativeLayout.LayoutParams.MATCH_PARENT,
        RelativeLayout.LayoutParams.MATCH_PARENT
    )
    params.setMargins(0,0,10,0)
    relativeLayout.layoutParams=params

    relativeLayout.id = View.generateViewId()
    println("${cnt}relativelayout id=${relativeLayout.id}")

//커스텀
    //val frameLayout = FrameLayout(context)


//    frameLayout.id=View.generateViewId()



    val btn1 = Button(context)


    btn1.id = View.generateViewId()//?
    println("${cnt}커스텀버튼 id=${btn1.id}")
    btn1.layoutParams = RelativeLayout.LayoutParams(
        RelativeLayout.LayoutParams.MATCH_PARENT,
        RelativeLayout.LayoutParams.MATCH_PARENT
    )
    btn1.setBackgroundResource(R.drawable.exercise_routine_background_design)







    // 두 번째 버튼들을 담을 LinearLayout 생성
    val linearLayout = LinearLayout(context)
    linearLayout.layoutParams = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.MATCH_PARENT
    )

    linearLayout.orientation = LinearLayout.VERTICAL
    linearLayout.id = View.generateViewId()
    println("${cnt}두번째 linearlayout id=${linearLayout.id}")
    linearLayout.gravity = android.view.Gravity.CENTER_HORIZONTAL


    // '수정' 버튼 생성 및 설정
    val editButton = Button(context)
    editButton.id = View.generateViewId()



    println("${cnt}수정버튼 id=${editButton.id}")

    editButton.layoutParams = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        0,
        1f
    )
    editButton.text = "수정"
    editButton.gravity = android.view.Gravity.CENTER
    editButton.setTextColor(resources.getColor(android.R.color.white)) // 텍스트 색상 변경
    editButton.setBackgroundResource(R.drawable.exercise_routine_background_design) // 배경 설정

    // '삭제' 버튼 생성 및 설정
    val deleteButton = Button(context)
    deleteButton.id = View.generateViewId()
    println("${cnt}삭제버튼 id=${deleteButton.id}")
    deleteButton.layoutParams = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        0,
        1f
    )
    deleteButton.text = "삭제"
    deleteButton.gravity = android.view.Gravity.CENTER
    deleteButton.setTextColor(resources.getColor(android.R.color.white)) // 텍스트 색상 변경
    deleteButton.setBackgroundResource(R.drawable.exercise_routine_background_design) // 배경 설정
    // 각 버튼을 LinearLayout에 추가
    linearLayout.addView(editButton)
    linearLayout.addView(deleteButton)

    // RelativeLayout에 버튼들 추가

    relativeLayout.addView(linearLayout)
    relativeLayout.addView(btn1)
    binding.routineInfoLayout.addView(relativeLayout)

    // Activity의 Content View로 설정
    //setContentView(relativeLayout)
    println("press addnewbtn ${++cnt}")

    //editmode

    //linearLayout.visibility=View.GONE
    btn1.setOnLongClickListener {
        println("Set on click Listener")
        linearLayout.visibility=View.VISIBLE
        btn1.visibility=View.GONE

        //문제
        //색이 너무 많다
        //쩅하다



        true
    }
    btn1.setOnClickListener {
        replaceFragment(Home()) //임의로 넣은 기능
    }




    deleteButton.setOnClickListener {

        removeButton(editButton)
        removeButton(btn1)
        removeButton(deleteButton)
        removeLinearLayout(linearLayout)
        removeRelativeLayout(relativeLayout)


    }




}











//    private fun addnewbtn() {
//
//    //btn1
//        val newTextView = Button(context)
//        newTextView.text="${++cnt}번버튼"
//        newTextView.setBackgroundResource(R.drawable.exercise_routine_background_design)
//        newTextView.id=cnt
//
//
//
//
//    newTextView.layoutParams = LinearLayout.LayoutParams(
//        LinearLayout.LayoutParams.MATCH_PARENT,
//        LinearLayout.LayoutParams.MATCH_PARENT
//    )
//
//    newTextView.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
//    newTextView.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
//    newTextView.layoutParams = LinearLayout.LayoutParams(
//        LinearLayout.LayoutParams.MATCH_PARENT,
//        LinearLayout.LayoutParams.MATCH_PARENT
//    )
//    val params = newTextView.layoutParams as ViewGroup.MarginLayoutParams
//    params.leftMargin = 10
//    params.rightMargin=10
//    newTextView.layoutParams=params
//
//        routininfo.addView(newTextView)
//    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
        println("success")

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


    private fun removeButton(button: Button) {
        val parentLayout = button.parent as? LinearLayout
        parentLayout?.removeView(button)
    }
    private fun removeLinearLayout(LinearLayout: LinearLayout) {
        val parentLayout = LinearLayout.parent as? RelativeLayout
        parentLayout?.removeView(LinearLayout)
    }
    private fun removeRelativeLayout(RelativeLayout: RelativeLayout) {
        val parentLayout = RelativeLayout.parent as? LinearLayout
        parentLayout?.removeView(RelativeLayout)
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