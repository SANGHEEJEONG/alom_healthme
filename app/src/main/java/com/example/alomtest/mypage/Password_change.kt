package com.example.alomtest.mypage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment

import com.example.alomtest.R
import com.example.alomtest.databinding.FragmentPasswordChangeBinding
import com.example.alomtest.retrofit.Api
import com.example.alomtest.retrofit.LoginBackendResponse8
import com.google.gson.JsonParser
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class password_change : Fragment() {

    private lateinit var binding: FragmentPasswordChangeBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = FragmentPasswordChangeBinding.inflate((layoutInflater))


        binding.backiconBtn.setOnClickListener {
            replaceFragment(mypage_setting())
        }

        val callback = object : OnBackPressedCallback(true /* enabled by default */) {
            override fun handleOnBackPressed() {
                // 뒤로가기 이벤트가 발생했을 때 수행할 작업
                // 예를 들어 특정 상황에서만 뒤로가기를 처리하고 싶은 경우 여기에 작성
                replaceFragment(mypage_setting())
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)


        binding.changeAccept.setOnClickListener {

            val usertoken = SharedPreferenceUtils.loadData(requireContext(), "accessToken", "")

            // == 백엔드 통신 부분 ==
            val api = Api.create()//
            val jsonObject= JSONObject()

            val current_email = SharedPreferenceUtils.loadData(requireContext(), "email", "")

            jsonObject.put("password",binding.password1.text.toString())
            jsonObject.put("email",current_email)
            jsonObject.put("changedPassword",binding.password3.text.toString())





            JsonParser.parseString(jsonObject.toString())


            println(JsonParser.parseString(jsonObject.toString()))


            api.change_password(accessToken = "Bearer $usertoken",JsonParser.parseString(jsonObject.toString()))
                .enqueue(object : Callback<LoginBackendResponse8> {
                override fun onResponse(
                    call: Call<LoginBackendResponse8>,
                    response: Response<LoginBackendResponse8>
                ) {
                    Log.d("로그인 통신 성공",response.toString())
                    Log.d("로그인 통신 성공", response.body().toString())
                    Log.d("response코드",response.code().toString())
                    //Log.d("반환 메시지",response.body())


                    when (response.code()) {
                        200 -> {

                            Toast.makeText(requireContext(), "비번변경성공", Toast.LENGTH_LONG).show()

//fragment구현

                        }
                        500 -> Toast.makeText(requireContext(), "로그인 실패 : 아이디나 비번이 올바르지 않습니다", Toast.LENGTH_LONG).show()
                        403-> Toast.makeText(requireContext(),"로그인 실패 : 서버 접근 권한이 없습니다.", Toast.LENGTH_SHORT).show()

                        else -> Toast.makeText(requireContext(), "LOGIN ERROR", Toast.LENGTH_LONG).show()


                    }
                }

                override fun onFailure(call: Call<LoginBackendResponse8>, t: Throwable) {
                    // 실패
                    Log.d("로그인 통신 실패",t.message.toString())
                    Log.d("로그인 통신 실패","fail")
                }
            })

        }

    }
    private var _binding: FragmentPasswordChangeBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentPasswordChangeBinding.inflate(inflater,container,false)
        return binding.root
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
