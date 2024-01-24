package com.example.alomtest.exercise.custompage02

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.alomtest.R
import com.example.alomtest.databinding.FragmentExerciseAddCustomListBinding
import com.example.alomtest.exercise.custompage01.add_routine_page
import com.example.alomtest.exercise.custompage03.exercise_select_exercise

class exercise_add_custom_list : Fragment() {
    private lateinit var binding: FragmentExerciseAddCustomListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentExerciseAddCustomListBinding.inflate(layoutInflater)

        val backicon = binding.cancelicon

        backicon.setOnClickListener {
            replaceFragment(add_routine_page())
        }


    }
    private var _binding: FragmentExerciseAddCustomListBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentExerciseAddCustomListBinding.inflate(inflater, container, false)
        val bundle = Bundle()

        _binding?.part01?.setOnClickListener { //가슴
            bundle.putString("type", "가슴") // 데이터 추가 (key-value 쌍)

            val fragment = exercise_select_exercise()
            fragment.arguments = bundle

            val test = bundle.getString("type")

            Log.d("bundle테스트", test.toString())
            parentFragmentManager.beginTransaction().replace(R.id.frame_layout, fragment).commit() // container는 Fragment를 보여줄 레이아웃의 id입니다.
        }

        _binding?.part02?.setOnClickListener { //팔
            bundle.putString("type", "팔") // 데이터 추가 (key-value 쌍)

            val fragment = exercise_select_exercise()
            fragment.arguments = bundle

            val test = bundle.getString("type")

            Log.d("bundle테스트", test.toString())
            parentFragmentManager.beginTransaction().replace(R.id.frame_layout, fragment).commit() // container는 Fragment를 보여줄 레이아웃의 id입니다.
        }
        _binding?.part03?.setOnClickListener { //등
            bundle.putString("type", "등") // 데이터 추가 (key-value 쌍)

            val fragment = exercise_select_exercise()
            fragment.arguments = bundle

            val test = bundle.getString("type")

            Log.d("bundle테스트", test.toString())
            parentFragmentManager.beginTransaction().replace(R.id.frame_layout, fragment).commit() // container는 Fragment를 보여줄 레이아웃의 id입니다.
        }
        _binding?.part04?.setOnClickListener { //복근
            bundle.putString("type", "복근") // 데이터 추가 (key-value 쌍)

            val fragment = exercise_select_exercise()
            fragment.arguments = bundle

            val test = bundle.getString("type")

            Log.d("bundle테스트", test.toString())
            parentFragmentManager.beginTransaction().replace(R.id.frame_layout, fragment).commit() // container는 Fragment를 보여줄 레이아웃의 id입니다.
        }
        _binding?.part05?.setOnClickListener { //하체
            bundle.putString("type", "하체") // 데이터 추가 (key-value 쌍)

            val fragment = exercise_select_exercise()
            fragment.arguments = bundle

            val test = bundle.getString("type")

            Log.d("bundle테스트", test.toString())
            parentFragmentManager.beginTransaction().replace(R.id.frame_layout, fragment).commit() // container는 Fragment를 보여줄 레이아웃의 id입니다.
        }
        _binding?.part06?.setOnClickListener { //어깨
            bundle.putString("type", "어깨") // 데이터 추가 (key-value 쌍)

            val fragment = exercise_select_exercise()
            fragment.arguments = bundle

            val test = bundle.getString("type")

            Log.d("bundle테스트", test.toString())
            parentFragmentManager.beginTransaction().replace(R.id.frame_layout, fragment).commit() // container는 Fragment를 보여줄 레이아웃의 id입니다.
        }
        return _binding?.root
        //return inflater.inflate(R.layout.fragment_mypage_body_measurement_editmode, container, false)
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
        println("success")

    }
    companion object {

    }
}