package com.example.alomtest.exercise.mainpage
import SharedPreferenceUtils
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import retrofit2.Callback

import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alomtest.R
import com.example.alomtest.databinding.FragmentExerciseMainCopyBinding
import com.example.alomtest.retrofit.Api
import com.example.alomtest.retrofit.email
import com.example.alomtest.retrofit.exerise_preset_list
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import java.util.Collections.list


class exercise_main_copy : Fragment() {
    private lateinit var binding:FragmentExerciseMainCopyBinding

    //val numbers: Array<Int> = arrayOf(1, 6,11)
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
        load_exercise()
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

    private fun load_exercise(){
        val api = Api.create()

        val jsonObject= JSONObject()//json객체 생성

        val email:String = SharedPreferenceUtils.loadData(requireContext(),"email")
        val usertoken:String = SharedPreferenceUtils.loadData(requireContext(),"accessToken")

        jsonObject.put("email", email)

        Log.d("이메일 출력", email.toString())
        jsonObject.put("presetId", 0)
        //Log.d()





            api.load_preset_exercise_list(accessToken = "Bearer $usertoken", JsonParser.parseString(jsonObject.toString()))
                .enqueue(object : Callback<String> {
                override fun onResponse(
                    call: Call<String>,
                    response: Response<String>
                ) {
                    Log.d("로그인 통신 성공",response.toString())
                    Log.d("로그인 통신 성공", response.body().toString())
                    Log.d("response코드",response.code().toString())

                    when (response.code()) {
                        200->{ Toast.makeText(requireContext(),"200OK", Toast.LENGTH_SHORT).show()
                            Log.d("받은 정보 로그", response.toString())

                        }
//                        401-> Toast.makeText(this@account2,"인증코드가 올바르지 않습니다.", Toast.LENGTH_SHORT).show()
//                        403-> Toast.makeText(this@account2,"로그인 실패 : 서버 접근 권한이 없습니다.", Toast.LENGTH_SHORT).show()
//                        404 -> Toast.makeText(this@account2, "로그인 실패 : 아이디나 비번이 올바르지 않습니다", Toast.LENGTH_LONG).show()
//                        500 -> Toast.makeText(this@account2, "로그인 실패 : 서버 오류", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.d("로그인 통신 실패",t.message.toString())
                    Log.d("로그인 통신 실패","fail")
                }
            })


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