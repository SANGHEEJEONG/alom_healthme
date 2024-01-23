package com.example.alomtest.exercise.custompage02

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.alomtest.R
import com.example.alomtest.databinding.FragmentExerciseAddCustomListBinding
import com.example.alomtest.exercise.custompage01.add_routine_page

class exercise_add_custom_list : Fragment() {
    private lateinit var binding: FragmentExerciseAddCustomListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentExerciseAddCustomListBinding.inflate(layoutInflater)

        val backicon = binding.cancelicon

        backicon.setOnClickListener {
            replaceFragment(add_routine_page())
        }


        val bundle = Bundle()


        binding.part01.setOnClickListener{//가슴
            bundle.putString("type", "가슴") // 데이터 추가 (key-value 쌍)
            //replaceFragment()
        }

        binding.part02.setOnClickListener{//팔
            bundle.putString("type", "팔") // 데이터 추가 (key-value 쌍)

        }

        binding.part03.setOnClickListener{//등
            bundle.putString("type", "등") // 데이터 추가 (key-value 쌍)

        }

        binding.part04.setOnClickListener{//복근
            bundle.putString("type", "복근") // 데이터 추가 (key-value 쌍)

        }

        binding.part05.setOnClickListener{//하체
            bundle.putString("type", "하체") // 데이터 추가 (key-value 쌍)

        }

        binding.part06.setOnClickListener{//어깨
            bundle.putString("type", "어깨") // 데이터 추가 (key-value 쌍)

        }



    }
    private var _binding: FragmentExerciseAddCustomListBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        _binding = FragmentExerciseAddCustomListBinding.inflate(inflater, container, false)
        return binding.root
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