package com.example.alomtest.exercise.custompage03

import SharedPreferenceUtils
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alomtest.R
import com.example.alomtest.databinding.FragmentExerciseSelectExerciseBinding
import com.example.alomtest.exercise.custompage02.exercise_add_custom_list
import com.example.alomtest.retrofit.Api
import com.example.alomtest.retrofit.LoginBackendResponse13
import com.example.alomtest.retrofit.exercise_list

import com.google.gson.JsonParser
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class exercise_select_exercise : Fragment() {
    private lateinit var binding: FragmentExerciseSelectExerciseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentExerciseSelectExerciseBinding.inflate(layoutInflater)

        val backicon = binding.cancelicon
        //val total_exercise_List:ArrayList<exercise_list>?

        val validexercise = ArrayList<exercise_list>()

        //val receive_data:String? = arguments?.getString("type")

        backicon.setOnClickListener {
            replaceFragment(exercise_add_custom_list())
        }


        val bundle = arguments
        val receive_data = bundle?.getString("type").toString()

        var usertoken = SharedPreferenceUtils.loadData(requireContext(), "accessToken", "")

        binding.partIndicator.text = "${receive_data} 운동"
        Log.d("번들 테스트",receive_data)


        binding.get.setOnClickListener {
            val api = Api.create()

            api.load_exercise(accessToken = "Bearer $usertoken")
                .enqueue(object : Callback<ArrayList<exercise_list>> {
                    override fun onResponse(
                        call: Call<ArrayList<exercise_list>>,
                        response: Response<ArrayList<exercise_list>>
                    ) {
                        val total_exercise_List = response.body()
                        Log.d("로그인 통신 성공",response.toString())
                        Log.d("로그인 통신 성공", response.body().toString())
                        Log.d("response코드",response.code().toString())



                        when (response.code()) {
                            200-> {
                                if (total_exercise_List != null) {
                                    for (i: Int in 0 until total_exercise_List.size) {
                                        if(receive_data==total_exercise_List[i].category){
                                            validexercise.add(total_exercise_List[i])
                                        }
                                    }

                                    Log.d("succ",validexercise.toString())
                                }
                                else{
                                    Log.d("error","total_exercise_List가 null임")
                                }
                            }
                            401-> Toast.makeText(requireContext(),"서버가 동작하지 않습니다. ", Toast.LENGTH_SHORT).show()
                            403-> Toast.makeText(requireContext(),"로그인 실패 : 서버 접근 권한이 없습니다.", Toast.LENGTH_SHORT).show()
                            404 -> Toast.makeText(requireContext(), "로그인 실패 : 아이디나 비번이 올바르지 않습니다", Toast.LENGTH_LONG).show()
                            500 -> Toast.makeText(requireContext(), "로그인 실패 : 서버 오류", Toast.LENGTH_LONG).show()

                            408->{//토큰만료

                                var refreshToken = SharedPreferenceUtils.loadData(requireContext(), "refreshToken", "")
                                var usertoken = SharedPreferenceUtils.loadData(requireContext(), "accessToken", "")

                                val email = SharedPreferenceUtils.loadData(requireContext(), "email", "")



                                val jsonObject2= JSONObject()
                                jsonObject2.put("email",email)
                                jsonObject2.put("refreshToken",refreshToken)
                                jsonObject2.put("accessToken",usertoken)

                                Log.d("refreshToken", refreshToken.toString())
                                Log.d("accessToken2",usertoken)
                                Log.d("json출력", jsonObject2.toString())




                                api.refreshToken(accessToken = "Bearer $usertoken",JsonParser.parseString(jsonObject2.toString())).enqueue(object :
                                    Callback<LoginBackendResponse13> {
                                    override fun onResponse(
                                        call: Call<LoginBackendResponse13>,
                                        response: Response<LoginBackendResponse13>
                                    ) {
                                        Log.d("로그인 통신 성공",response.toString())
                                        Log.d("로그인 통신 성공", response.body().toString())
                                        Log.d("response코드",response.code().toString())




                                        //Log.d("반환 메시지",response.body())


                                        when (response.code()) {
                                            200 -> {

                                                Toast.makeText(requireContext(), "access토큰 재발급 성공", Toast.LENGTH_LONG).show()

                                                val tok: LoginBackendResponse13?=response.body()
                                                val accesstoken:String? = tok?.accessToken.toString()
                                                var refreshtoken:String? = tok?.refreshToken.toString()


                                                SharedPreferenceUtils.saveData(requireContext(), "accessToken", accesstoken.toString())
                                                SharedPreferenceUtils.saveData(requireContext(), "refreshToken", refreshtoken.toString())

                                                Log.d("408이후 accessToken 출력", accesstoken.toString())
                                                //통신 다시한번 실행



                                                var usertoken = SharedPreferenceUtils.loadData(requireContext(), "accessToken", "")
                                                refreshtoken = SharedPreferenceUtils.loadData(requireContext(), "refreshToken", "")

                                                Log.d("user토큰",usertoken)


                                                api.load_exercise(accessToken = "Bearer $usertoken")
                                                    .enqueue(object : Callback<ArrayList<exercise_list>> {
                                                        override fun onResponse(
                                                            call: Call<ArrayList<exercise_list>>,
                                                            response: Response<ArrayList<exercise_list>>
                                                        ) {
                                                            val total_exercise_List = response.body()
                                                            Log.d("로그인 통신 성공",response.toString())
                                                            Log.d("로그인 통신 성공", response.body().toString())
                                                            Log.d("response코드",response.code().toString())



                                                            when (response.code()) {
                                                                200-> {
                                                                    if (total_exercise_List != null) {
                                                                        for (i: Int in 0 until total_exercise_List.size) {
                                                                            if(receive_data==total_exercise_List[i].category){
                                                                                validexercise.add(total_exercise_List[i])
                                                                            }
                                                                        }

                                                                        Log.d("succ",validexercise.toString())
                                                                    }
                                                                    else{
                                                                        Log.d("error","total_exercise_List가 null임")
                                                                    }
                                                                }
                                                                401-> Toast.makeText(requireContext(),"서버가 동작하지 않습니다. ", Toast.LENGTH_SHORT).show()
                                                                403-> Toast.makeText(requireContext(),"로그인 실패 : 서버 접근 권한이 없습니다.", Toast.LENGTH_SHORT).show()
                                                                404 -> Toast.makeText(requireContext(), "로그인 실패 : 아이디나 비번이 올바르지 않습니다", Toast.LENGTH_LONG).show()

                                                                500 -> Toast.makeText(requireContext(), "로그인 실패 : 서버 오류", Toast.LENGTH_LONG).show()
                                                            }







                                                        }

                                                        override fun onFailure(call: Call<ArrayList<exercise_list>>, t: Throwable) {
                                                            Log.d("로그인 통신 실패",t.message.toString())
                                                            Log.d("로그인 통신 실패","fail")
                                                        }
                                                    })








//fragment구현

                                            }
                                            500 -> Toast.makeText(requireContext(), "로그인 실패 : 아이디나 비번이 올바르지 않습니다", Toast.LENGTH_LONG).show()
                                            403-> Toast.makeText(requireContext(),"로그인 실패 : 서버 접근 권한이 없습니다.", Toast.LENGTH_SHORT).show()
                                            408-> Toast.makeText(requireContext(),"토큰 재발급 실패", Toast.LENGTH_SHORT).show()

                                            else -> Toast.makeText(requireContext(), "LOGIN ERROR", Toast.LENGTH_LONG).show()


                                        }
                                    }

                                    override fun onFailure(call: Call<LoginBackendResponse13>, t: Throwable) {
                                        // 실패
                                        Log.d("로그인 통신 실패",t.message.toString())
                                        Log.d("로그인 통신 실패","fail")
                                    }
                                })







                            }

                        }

                    }

                    override fun onFailure(call: Call<ArrayList<exercise_list>>, t: Throwable) {
                        Log.d("로그인 통신 실패",t.message.toString())
                        Log.d("로그인 통신 실패","fail")
                    }
                })


//리사이클러뷰와 연결
            val layoutManager = LinearLayoutManager(requireContext())
            val recyclerview = binding.selectExerciseList
            recyclerview.layoutManager=layoutManager
            val adapter = exercise_selcet_list_adapter(validexercise)
            recyclerview.adapter = adapter




        }

    }
    private var _binding: FragmentExerciseSelectExerciseBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        _binding = FragmentExerciseSelectExerciseBinding.inflate(inflater, container, false)
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