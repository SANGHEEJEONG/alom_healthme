package com.example.alomtest.exercise.custompage01

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alomtest.MyViewModel
import com.example.alomtest.R
import com.example.alomtest.databinding.FragmentAddRoutinePageBinding
import com.example.alomtest.exercise.mainpage.exercise_main_copy
import com.example.alomtest.exerciseData
import com.example.alomtest.exercise.custompage02.exercise_add_custom_list
import com.example.alomtest.exercise.custompage03.exercise_selcet_list_adapter
import com.example.alomtest.retrofit.exercise_list


class add_routine_page : Fragment() {

    private lateinit var binding: FragmentAddRoutinePageBinding
    private lateinit var viewmodel : MyViewModel
    lateinit var exercise_recycler_view : RecyclerView
    var custom_cnt:Int =0
    var receive_data: String? = " " //공백으로 하면 되고 왜 null로 하면 안되는거지?



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= FragmentAddRoutinePageBinding.inflate(layoutInflater)

        val addbtn = binding.exerciseAddBtn
        val backicon=binding.cancelicon

        val bundle = arguments

        if(bundle?.getString("exercise_name")==null){
            receive_data=" "
        }
        else{
            receive_data = bundle.getString("exercise_name").toString()
        }

        Log.d("번들 테스트", receive_data.toString())





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
        viewmodel = ViewModelProvider(requireActivity()).get(MyViewModel::class.java)

        //customList.add(exerciseData("대흉근 발달, 3대 운동","01 바벨 벤치 프레스"))



        println("routine page 진입1")
        exercise_recycler_view = view.findViewById(R.id.exercise_view)
        println("routine page 진입2")
        exercise_recycler_view.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        println("routine page 진입3")
        exercise_recycler_view.setHasFixedSize(true)
        println("routine page 진입4")

        exercise_recycler_view.adapter = viewmodel._myList.value?.let { exercise_list_adpater(it) }
        println("routine page 진입5")

        Log.d("if문 진입 전 receive data 확인", receive_data.toString())
        Log.d("true or false", (receive_data!!.isNotBlank()).toString())
        if(receive_data!!.isNotBlank()){

            Log.d("exerciseData 추가하는 곧 진입",receive_data.toString())
            customList.add(exerciseData("부제목테스트",receive_data.toString(),ArrayList<set_list_item>()))
            viewmodel.addItem(exerciseData("부제목테스트",receive_data.toString(),ArrayList<set_list_item>()))
            viewmodel._myList.value!!.get(viewmodel._myList.value!!.size-1).set_list.add(0,set_list_item(0,1))

//            if(viewmodel._myList.value?.size==0){
//                viewmodel.addItem(exerciseData("부제목테스트",receive_data.toString(),ArrayList<set_list_item>()))
//                viewmodel._myList.value[0].set_list.add()
//            }
//            else{
//                viewmodel.addItem(exerciseData("부제목테스트",receive_data.toString(),ArrayList<set_list_item>()))
//
//            }


            //Log.d("커스텀 리스트 출력", customList.toString())
            Log.d("커스텀 리스트 출력", viewmodel._myList.value.toString())
            receive_data=""

//            val ad=exercise_recycler_view.adapter
//            ad?.notifyDataSetChanged()

            binding.courseNo.text="총 ${viewmodel._myList.value?.size}코스"
        }
    }
    private var _binding: FragmentAddRoutinePageBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        _binding = FragmentAddRoutinePageBinding.inflate(inflater,container,false)
        return binding.root
        //return inflater.inflate(R.layout.fragment_mypage_body_measurement_editmode, container, false)
    }

    override fun onResume() {
        super.onResume()
        binding.courseNo.text="총 ${viewmodel._myList.value?.size}코스"
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