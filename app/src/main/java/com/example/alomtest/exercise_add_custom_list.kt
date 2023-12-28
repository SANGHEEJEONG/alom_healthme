package com.example.alomtest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alomtest.databinding.FragmentExerciseAddCustomListBinding
import com.example.alomtest.databinding.FragmentExerciseMainBinding

private lateinit var binding: FragmentExerciseAddCustomListBinding


class exercise_add_custom_list : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=FragmentExerciseAddCustomListBinding.inflate(layoutInflater)

        val backicon = binding.cancelicon

        backicon.setOnClickListener {
            replaceFragment(add_routine_page())
        }
    }
    private var _binding: FragmentExerciseAddCustomListBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        _binding = FragmentExerciseAddCustomListBinding.inflate(inflater,container,false)
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