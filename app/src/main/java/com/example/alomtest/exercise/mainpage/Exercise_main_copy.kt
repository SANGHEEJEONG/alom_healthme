package com.example.alomtest.exercise.mainpage
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alomtest.R
import com.example.alomtest.databinding.FragmentExerciseMainCopyBinding
import java.util.Collections.list


class exercise_main_copy : Fragment() {
    private lateinit var binding:FragmentExerciseMainCopyBinding

    val numbers: Array<Int> = arrayOf(1, 6,11)
    //private val binding get() = _binding!!

    lateinit var routininfo:LinearLayout
    var cnt=0



//    private val test_data = listOf(
//        exercise_routine_profile("길똥이의 아침운동", 2),
//        exercise_routine_profile("길똥이의 점심운동", 3),
//        exercise_routine_profile("길똥이의 저녁운동", 4),
//        exercise_routine_profile("길똥이의 야간운동", 6),
//        exercise_routine_profile("길똥이의 새벽운동", 4),
//
//
//    )

    var test_data = mutableListOf<exercise_routine_profile>()





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=FragmentExerciseMainCopyBinding.inflate(layoutInflater)

        //요소간 여백 넣어주는 코드
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing) // 여백 크기
        val recyclerView = binding.routineList

        //est_data.add(exercise_routine_profile("길똥이의 새벽운동", 4))




        // RecyclerView에 LayoutManager 설정 및 ItemDecoration 추가
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.addItemDecoration(exercise_routine_divider(spacingInPixels))

        init()

}



    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = parentFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
        println("success")

    }
    private fun init(){
        binding.routineList.layoutManager = LinearLayoutManager(requireContext())
        binding.routineList.adapter = exercise_routine_adapter(requireContext(), test_data)


    }




private var _binding: FragmentExerciseMainCopyBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        _binding = FragmentExerciseMainCopyBinding.inflate(inflater,container,false)
        return binding.root
        //return inflater.inflate(R.layout.fragment_mypage_body_measurement_editmode, container, false)
    }

    companion object {


    }
}