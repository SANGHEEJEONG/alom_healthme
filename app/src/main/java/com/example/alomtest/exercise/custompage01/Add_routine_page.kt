package com.example.alomtest.exercise.custompage01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alomtest.R
import com.example.alomtest.databinding.FragmentAddRoutinePageBinding
import com.example.alomtest.exercise.mainpage.exercise_main_copy
import com.example.alomtest.exerciseData
import com.example.alomtest.exercise.custompage02.exercise_add_custom_list


class add_routine_page : Fragment() {

    private lateinit var binding: FragmentAddRoutinePageBinding
    lateinit var exercise_recycler_view : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= FragmentAddRoutinePageBinding.inflate(layoutInflater)

        val addbtn = binding.exerciseAddBtn
        val backicon=binding.cancelicon

        addbtn.setOnClickListener {
            println("test1")
            replaceFragment(exercise_add_custom_list())
            println("test2")

        }
        backicon.setOnClickListener {
            replaceFragment(exercise_main_copy())
        }


        //backicon은 나중에 구현




    //View.findViewById(R.id.exercise_view)


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



//        val customList = arrayListOf(
//            exerciseData("대흉근 발달, 3대 운동","01 바벨 벤치 프레스")
//
//            )
        //data class ExerciseData(val title: String, val description: String)

        var customList = ArrayList<exerciseData>()
        //customList.add(exerciseData("대흉근 발달, 3대 운동","01 바벨 벤치 프레스"))



        println("routine page 진입1")
        exercise_recycler_view = view.findViewById(R.id.exercise_view)
        println("routine page 진입2")
        exercise_recycler_view.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        println("routine page 진입3")
        exercise_recycler_view.setHasFixedSize(true)
        println("routine page 진입4")

        exercise_recycler_view.adapter = exercise_list_adpater(customList)
        println("routine page 진입5")
    }
    private var _binding: FragmentAddRoutinePageBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        _binding = FragmentAddRoutinePageBinding.inflate(inflater,container,false)
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