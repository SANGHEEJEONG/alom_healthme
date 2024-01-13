package com.example.alomtest.mypage

import SharedPreferenceUtils
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.example.alomtest.R
import com.example.alomtest.databinding.FragmentMypageBodyInformationEditmodeBinding
import com.example.alomtest.retrofit.Api
import com.example.alomtest.retrofit.LoginBackendResponse12
import com.example.alomtest.retrofit.LoginBackendResponse13
import com.example.alomtest.retrofit.LoginBackendResponse6
import com.example.alomtest.retrofit.LoginBackendResponse7
import com.example.alomtest.retrofit.LoginBackendResponse8
import com.example.alomtest.retrofit.email
import com.google.gson.JsonParser
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class mypage_body_information_editmode : Fragment() {

    private lateinit var binding:FragmentMypageBodyInformationEditmodeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=FragmentMypageBodyInformationEditmodeBinding.inflate(layoutInflater)

        //성별 체크박스 코드

        val current_gender=(SharedPreferenceUtils.loadData(requireContext(),"gender",""))
        val current_weight=(SharedPreferenceUtils.loadData(requireContext(),"weight",""))
        val current_height=(SharedPreferenceUtils.loadData(requireContext(),"height",""))

        val man=binding.manCheckbox
        val woman=binding.womanCheckbox
        if(current_gender=="남성"){
            man.isChecked=true
        }
        else if(current_gender=="여성"){
            woman.isChecked=true
        }



        man.setOnCheckedChangeListener { _, isChecked -> if(isChecked){
            woman.isChecked = false
        }
        }
        woman.setOnCheckedChangeListener { _, isChecked -> if(isChecked){
            man.isChecked = false
        }
        }

//

//
//        val current_w=(SharedPreferenceUtils.loadData(requireContext(),"weight","")).toDouble()
//        val current_h=(SharedPreferenceUtils.loadData(requireContext(),"height","")).toDouble()

        binding.backiconBtn.setOnClickListener {
            replaceFragment(mypage_body_information())
        }
                binding.savemode.setOnClickListener {
            //변수에 입력한 값들을 저장하고 fragment 변경

            //if문으로 예외처리
            //공백이 입력된 경우
//            if (binding.heightOutput.text.toString()
//                    .isEmpty() || binding.weightOutput.text.toString().isEmpty()
//            ) {
//                Toast.makeText(requireContext(), "신장 또는 몸무게를 입력 후 저장버튼을 누르세요.", Toast.LENGTH_SHORT)
//                    .show()
//
//            }

//            else
                if(!(binding.manCheckbox.isChecked) && !(binding.womanCheckbox.isChecked)){
                Toast.makeText(requireContext(), "성별을 선택해 주세요.", Toast.LENGTH_SHORT)
                    .show()
            }

            //신장 혹은 몸무게가 0으로 입력된 경우
            else if (binding.heightOutput.text.toString()
                    .toDouble() == 0.0 || binding.weightOutput.text.toString().toDouble() == 0.0
            ) {
                Toast.makeText(requireContext(), "신장 또는 몸무게가 0이 될 수 없습니다.", Toast.LENGTH_SHORT)
                    .show()

            } else {

                if(binding.heightOutput.text.isNotEmpty()){
                    SharedPreferenceUtils.saveData(requireContext(), "height", binding.heightOutput.text.toString())
                }
                else if(binding.heightOutput.text.isEmpty()){
                    SharedPreferenceUtils.saveData(requireContext(), "height", current_height)

                }
                if(binding.weightOutput.text.isNotEmpty()){
                    SharedPreferenceUtils.saveData(requireContext(), "weight", binding.weightOutput.text.toString())
                }
                else{
                    SharedPreferenceUtils.saveData(requireContext(), "weight", current_weight)

                }

//                if(binding.genderOutput.text.isNotEmpty()){
//                    SharedPreferenceUtils.saveData(requireContext(), "gender", binding.genderOutput.text.toString())
//                }
                if(binding.manCheckbox.isChecked){
                    SharedPreferenceUtils.saveData(requireContext(), "gender", "남성")
                }
                else{
                    SharedPreferenceUtils.saveData(requireContext(), "gender", "여성")
                }


                if(binding.birthdayInput.text.isNotEmpty()){

                    if(binding.birthdayInput.text.length==10){
                        SharedPreferenceUtils.saveData(requireContext(), "birthday", binding.birthdayInput.text.toString())
                        Log.d("birthday출력",binding.birthdayInput.text.toString())


                    }
                    else{
                        val y=binding.birthdayInput.text.substring(0,4)
                        val m=binding.birthdayInput.text.substring(4,6)
                        val d=binding.birthdayInput.text.substring(6,8)
                        val new_birthday = "$y-$m-$d"
                        Log.d("birthday출력",new_birthday)

                        SharedPreferenceUtils.saveData(requireContext(), "birthday", new_birthday.toString())

                    }
                }
//                if(binding.nameOutput.text.isNotEmpty()){
//                    SharedPreferenceUtils.saveData(requireContext(), "name", binding.nameOutput.text.toString())
//                }

//                SharedPreferenceUtils.saveData(requireContext(), "name", binding.nameOutput.text.toString())
//                SharedPreferenceUtils.saveData(requireContext(), "gender", binding.genderOutput.text.toString())
//                SharedPreferenceUtils.saveData(requireContext(), "birthday", binding.birthdayInput.text.toString())
                //bmi 계산

                var weight:Double = (SharedPreferenceUtils.loadData(requireContext(),"weight","")).toDouble()
                var height:Double = (SharedPreferenceUtils.loadData(requireContext(),"height","")).toDouble()

                    var calculate_bmi: Double = (weight) / ((height / 100.0) * (height / 100.0)) * 10


                    //
                    if(calculate_bmi>300){
                        calculate_bmi=300.0
                    }


                    SharedPreferenceUtils.saveData(requireContext(), "bmi", (calculate_bmi.toString()))


                    //home_height=binding.heightOutput.text.toString().toDouble()
                    //home_weight=binding.weightOutput.text.toString().toDouble()
                    //home_bmi=(home_weight)/((home_height/100.0)*(home_height/100.0))*10
//

                    //println("변경된이후 키:" + home_height)
                    //println("변경된이후 몸무게:" + home_weight)

                    //레트로핏으로 서버에 저장




                    val save_height = SharedPreferenceUtils.loadData(requireContext(), "height", "")
                    val save_weight = SharedPreferenceUtils.loadData(requireContext(), "weight", "")
                    val save_bmi = SharedPreferenceUtils.loadData(requireContext(), "bmi", "")
                    val save_gender = SharedPreferenceUtils.loadData(requireContext(), "gender", "")
                    val save_birthday = SharedPreferenceUtils.loadData(requireContext(), "birthday", "")
                    var email=(SharedPreferenceUtils.loadData(requireContext(),"email",""))
                    val name = SharedPreferenceUtils.loadData(requireContext(), "name", "")

                    val jsonObject= JSONObject()

                    jsonObject.put("height",save_height.toString())
                    jsonObject.put("weight",save_weight.toString())
                    jsonObject.put("bmi",save_bmi.toString())
                    jsonObject.put("gender",save_gender.toString())
                    jsonObject.put("birthday",save_birthday.toString())
                    jsonObject.put("email",email)
                    jsonObject.put("name",name)




                    val usertoken = SharedPreferenceUtils.loadData(requireContext(), "accessToken", "")
                    Log.d("user토큰",usertoken)
                    Log.d("JSON출력",jsonObject.toString())
                    val api = Api.create()

                    //api.loadData(JsonParser.parseString(jsonObject.toString()))
                    api.change_bodyinfo(accessToken = "Bearer $usertoken",JsonParser.parseString(jsonObject.toString()))
                        .enqueue(object : Callback<LoginBackendResponse12> {
                            override fun onResponse(
                                call: Call<LoginBackendResponse12>,
                                response: Response<LoginBackendResponse12>
                            ) {
                                Log.d("로그인 통신 성공",response.toString())
                                Log.d("로그인 통신 성공", response.body().toString())
                                Log.d("response코드",response.code().toString())

                                when (response.code()) {
                                    200-> {Toast.makeText(requireContext(),"변경 성공", Toast.LENGTH_SHORT).show()
                                        replaceFragment(mypage_body_information())





                                    }
                                    401-> Toast.makeText(requireContext(),"서버가 동작하지 않습니다. ", Toast.LENGTH_SHORT).show()
                                    403-> Toast.makeText(requireContext(),"로그인 실패 : 서버 접근 권한이 없습니다.", Toast.LENGTH_SHORT).show()
                                    404 -> Toast.makeText(requireContext(), "로그인 실패 : 아이디나 비번이 올바르지 않습니다", Toast.LENGTH_LONG).show()

                                    408->{//토큰만료

                                        val refreshToken = SharedPreferenceUtils.loadData(requireContext(), "refreshToken", "")
                                        //val accessToken = SharedPreferenceUtils.loadData(requireContext(), "acessToken", "")

                                        email = SharedPreferenceUtils.loadData(requireContext(), "email", "")



                                        val jsonObject2= JSONObject()
                                        jsonObject2.put("email",email)
                                        jsonObject2.put("refreshToken",refreshToken)
                                        jsonObject2.put("accessToken",usertoken)

                                        Log.d("refreshToken", refreshToken.toString())
                                        Log.d("json출력", jsonObject2.toString())




                                        api.refreshToken(JsonParser.parseString(jsonObject2.toString())).enqueue(object :
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
                                                        val accesstoken:String? = tok?.accessToken

                                                        SharedPreferenceUtils.saveData(requireContext(), "accessToken", accesstoken.toString())
                                                        Log.d("408이후 accessToken 출력", accesstoken.toString())
                                                        val jsonObject3= JSONObject()

                                                        val save_height = SharedPreferenceUtils.loadData(requireContext(), "height", "")
                                                        val save_weight = SharedPreferenceUtils.loadData(requireContext(), "weight", "")
                                                        val save_bmi = SharedPreferenceUtils.loadData(requireContext(), "bmi", "")
                                                        val save_gender = SharedPreferenceUtils.loadData(requireContext(), "gender", "")
                                                        val save_birthday = SharedPreferenceUtils.loadData(requireContext(), "birthday", "")
                                                        val email=(SharedPreferenceUtils.loadData(requireContext(),"email",""))
                                                        val name = SharedPreferenceUtils.loadData(requireContext(), "name", "")





                                                        jsonObject3.put("height",save_height.toString())
                                                        jsonObject3.put("weight",save_weight.toString())
                                                        jsonObject3.put("bmi",save_bmi.toString())
                                                        jsonObject3.put("gender",save_gender.toString())
                                                        jsonObject3.put("birthday",save_birthday.toString())
                                                        jsonObject3.put("email",email)
                                                        jsonObject3.put("name",name)
                                                        //


                                                        var usertoken = SharedPreferenceUtils.loadData(requireContext(), "accessToken", "")
                                                        val refreshtoken = SharedPreferenceUtils.loadData(requireContext(), "refreshToken", "")

                                                        Log.d("user토큰",usertoken)
                                                        Log.d("JSON출력",jsonObject3.toString())




                                                        api.change_bodyinfo(accessToken = "Bearer $usertoken",JsonParser.parseString(jsonObject3.toString()))
                                                            .enqueue(object : Callback<LoginBackendResponse12> {
                                                                override fun onResponse(
                                                                    call: Call<LoginBackendResponse12>,
                                                                    response: Response<LoginBackendResponse12>
                                                                ) {
                                                                    Log.d("로그인 통신 성공",response.toString())
                                                                    Log.d("로그인 통신 성공", response.body().toString())
                                                                    Log.d("response코드",response.code().toString())

                                                                    when (response.code()) {
                                                                        200-> {Toast.makeText(requireContext(),"로드성공", Toast.LENGTH_SHORT).show()
                                                                            val tok: LoginBackendResponse12?=response.body()
                                                                            Log.d("response바디 출력",tok.toString())
                                                                            Log.d("sharedpre에 저장 완료","")
                                                                            replaceFragment(mypage_body_information())

                                                                        }
                                                                        401-> Toast.makeText(requireContext(),"서버가 동작하지 않습니다. ", Toast.LENGTH_SHORT).show()
                                                                        403-> Toast.makeText(requireContext(),"로그인 실패 : 서버 접근 권한이 없습니다.", Toast.LENGTH_SHORT).show()
                                                                        404 -> Toast.makeText(requireContext(), "로그인 실패 : 아이디나 비번이 올바르지 않습니다", Toast.LENGTH_LONG).show()
                                                                        500 -> Toast.makeText(requireContext(), "로그인 실패 : 서버 오류", Toast.LENGTH_LONG).show()
                                                                    }
                                                                }

                                                                override fun onFailure(call: Call<LoginBackendResponse12>, t: Throwable) {
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


                                        //통신 다시한번 실행

                                       //













                                        //





                                    }
                                    500 -> Toast.makeText(requireContext(), "로그인 실패 : 서버 오류", Toast.LENGTH_LONG).show()
                                }
                            }

                            override fun onFailure(call: Call<LoginBackendResponse12>, t: Throwable) {
                                Log.d("로그인 통신 실패",t.message.toString())
                                Log.d("로그인 통신 실패","fail")
                            }
                        })










            }

        }





        //뒤로가기 처리
        val callback = object : OnBackPressedCallback(true /* enabled by default */) {
            override fun handleOnBackPressed() {
                // 뒤로가기 이벤트가 발생했을 때 수행할 작업
                // 예를 들어 특정 상황에서만 뒤로가기를 처리하고 싶은 경우 여기에 작성

                replaceFragment(mypage_body_information())

            }
        }



        requireActivity().onBackPressedDispatcher.addCallback(this, callback)


    }




    private var _binding: FragmentMypageBodyInformationEditmodeBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        _binding = FragmentMypageBodyInformationEditmodeBinding.inflate(inflater,container,false)
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


    override fun onResume() {
        super.onResume()
        //val homeinstance = Home()

//        val w:String=homeinstance.weight.toString()
//        val h:String=homeinstance.height.toString()

        val w: String = SharedPreferenceUtils.loadData(requireContext(), "weight", "")
        //val w:String=home_weight.toString()
        //val h:String= home_height.toString()
        val h: String = SharedPreferenceUtils.loadData(requireContext(), "height", "")

        val bmi:String= SharedPreferenceUtils.loadData(requireContext(), "bmi", "")

        val name:String= SharedPreferenceUtils.loadData(requireContext(), "name", "")

        val birthday:String= SharedPreferenceUtils.loadData(requireContext(), "birthday", "")

        val gender:String= SharedPreferenceUtils.loadData(requireContext(), "gender", "")
//2000-02-09
        val year = birthday.substring(0,4)
        val month = birthday.substring(5,7)
        val day = birthday.substring(8,10)
        val new_birthday:String = "$year$month$day"



        binding.birthdayInput.hint=new_birthday
        //binding.genderOutput.hint=gender
        binding.heightOutput.hint=h
        binding.weightOutput.hint=w

        binding.nameOutput2.setText(name)

        println("변경된 w"+w)
        println("변경된 h"+h)
    }

    companion object {

    }
}