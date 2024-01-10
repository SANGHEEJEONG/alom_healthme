package com.example.alomtest.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

import com.example.alomtest.R
import com.example.alomtest.databinding.ResetpasswordLayoutBinding
import com.example.alomtest.mypage.mypage_setting
import com.example.alomtest.retrofit.Api
import com.example.alomtest.retrofit.LoginBackendResponse11
import com.google.gson.JsonParser
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class resetpassword : AppCompatActivity() {

    private lateinit var binding: ResetpasswordLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        Log.d("reset 암호 페이지 입장","Hello world")
        binding = ResetpasswordLayoutBinding.inflate((layoutInflater))
        setContentView(binding.root)






        val callback = object : OnBackPressedCallback(true /* enabled by default */) {
            override fun handleOnBackPressed() {
                // 뒤로가기 이벤트가 발생했을 때 수행할 작업
                // 예를 들어 특정 상황에서만 뒤로가기를 처리하고 싶은 경우 여기에 작성

                val intent = Intent(this@resetpassword, first::class.java)
                startActivity(intent)
                finish()

            }
        }


        binding.changeAccept.setOnClickListener {

            //val usertoken = SharedPreferenceUtils.loadData(requireContext(), "accessToken", "")

            // == 백엔드 통신 부분 ==
            val api = Api.create()//
            val jsonObject= JSONObject()
            Log.d("reset 암호 페이지 입장","Hello world")

            val current_email:String = SharedPreferenceUtils.loadData(this@resetpassword, "email", "")
            Log.d("reset 암호 페이지 입장","Hello world")

            //jsonObject.put("password",binding.password1.text.toString())
            jsonObject.put("email",current_email)
            jsonObject.put("changedPassword",binding.password2.text.toString())
            Log.d("reset 암호 페이지 입장","Hello world")





            JsonParser.parseString(jsonObject.toString())


            println(JsonParser.parseString(jsonObject.toString()))


            api.change_forget_password(JsonParser.parseString(jsonObject.toString())).enqueue(object : Callback<LoginBackendResponse11> {
                override fun onResponse(
                    call: Call<LoginBackendResponse11>,
                    response: Response<LoginBackendResponse11>
                ) {
                    Log.d("로그인 통신 성공",response.toString())
                    Log.d("로그인 통신 성공", response.body().toString())
                    Log.d("response코드",response.code().toString())
                    //Log.d("반환 메시지",response.body())


                    when (response.code()) {
                        200 -> {

                            Toast.makeText(this@resetpassword, "비번변경성공", Toast.LENGTH_LONG).show()

//fragment구현

                        }
                        500 -> Toast.makeText(this@resetpassword, "로그인 실패 : 아이디나 비번이 올바르지 않습니다", Toast.LENGTH_LONG).show()
                        403-> Toast.makeText(this@resetpassword,"로그인 실패 : 서버 접근 권한이 없습니다.", Toast.LENGTH_SHORT).show()

                        else -> Toast.makeText(this@resetpassword, "LOGIN ERROR", Toast.LENGTH_LONG).show()


                    }
                }

                override fun onFailure(call: Call<LoginBackendResponse11>, t: Throwable) {
                    // 실패
                    Log.d("로그인 통신 실패",t.message.toString())
                    Log.d("로그인 통신 실패","fail")
                }
            })

        }

    }





    companion object {

    }
}
