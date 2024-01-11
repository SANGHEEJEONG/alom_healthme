package com.example.alomtest.mypage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.example.alomtest.R
import com.example.alomtest.databinding.FragmentMypageSettingEditmodeBinding
import com.example.alomtest.retrofit.Api
import com.example.alomtest.retrofit.LoginBackendResponse12
import com.google.gson.JsonParser
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class mypage_setting_editmode : Fragment() {
    private lateinit var binding: FragmentMypageSettingEditmodeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentMypageSettingEditmodeBinding.inflate((layoutInflater))

        binding.backiconBtn.setOnClickListener {
            replaceFragment(mypage_setting())
        }
        binding.savemode.setOnClickListener {
            if(binding.nameOutput2.text.toString().isNotEmpty()) {
                SharedPreferenceUtils.saveData(
                    requireContext(),
                    "name",
                    binding.nameOutput2.text.toString()
                )








            }
            if(binding.emailOutput.text.toString().isNotEmpty()){
                SharedPreferenceUtils.saveData(requireContext(), "email", binding.emailOutput.text.toString())}






            val jsonObject= JSONObject()

            val save_height = SharedPreferenceUtils.loadData(requireContext(), "height", "")
            val save_weight = SharedPreferenceUtils.loadData(requireContext(), "weight", "")
            val save_bmi = SharedPreferenceUtils.loadData(requireContext(), "bmi", "")
            val save_gender = SharedPreferenceUtils.loadData(requireContext(), "gender", "")
            val save_birthday = SharedPreferenceUtils.loadData(requireContext(), "birthday", "")
            val email=SharedPreferenceUtils.loadData(requireContext(),"email","")
            val name = SharedPreferenceUtils.loadData(requireContext(), "name", "")





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
            api.change_bodyinfo(accessToken = "Bearer $usertoken", JsonParser.parseString(jsonObject.toString()))
                .enqueue(object : Callback<LoginBackendResponse12> {
                    override fun onResponse(
                        call: Call<LoginBackendResponse12>,
                        response: Response<LoginBackendResponse12>
                    ) {
                        Log.d("로그인 통신 성공",response.toString())
                        Log.d("로그인 통신 성공", response.body().toString())
                        Log.d("response코드",response.code().toString())

                        when (response.code()) {
                            200-> {
                                Toast.makeText(requireContext(),"변경 성공", Toast.LENGTH_SHORT).show()
                                replaceFragment(mypage_setting())





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









        }
        val callback = object : OnBackPressedCallback(true /* enabled by default */) {
            override fun handleOnBackPressed() {
                // 뒤로가기 이벤트가 발생했을 때 수행할 작업
                // 예를 들어 특정 상황에서만 뒤로가기를 처리하고 싶은 경우 여기에 작성

                replaceFragment(mypage_setting())

            }
        }



        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    private var _binding: FragmentMypageSettingEditmodeBinding? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMypageSettingEditmodeBinding.inflate(inflater,container,false)
        return binding.root
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
        val name:String= SharedPreferenceUtils.loadData(requireContext(), "name", "")
        val email:String= SharedPreferenceUtils.loadData(requireContext(), "email", "")





        binding.nameOutput2.hint=name
        binding.emailOutput.hint=email
        binding.nameOutput.setText(name)

    }

    companion object {


    }
}