package com.example.alomtest.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.example.alomtest.databinding.AccountLayoutBinding
import com.example.alomtest.retrofit.Api
import com.example.alomtest.retrofit.LoginBackendResponse2
import com.example.alomtest.retrofit.LoginBackendResponse3
import com.google.gson.JsonParser
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class account : AppCompatActivity() {
    lateinit var binding: AccountLayoutBinding
    lateinit var email: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = AccountLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)


            binding.sendCode.setOnClickListener {
            email = binding.emailInput.text.toString().trim()//trim : 문자열 공백제거
                val jsonObject= JSONObject()
                jsonObject.put("email",email)


            // == 백엔드 통신 부분 ==
            val api = Api.create()

            api.send_authetication_code(JsonParser.parseString(jsonObject.toString()))
                .enqueue(object : Callback<LoginBackendResponse2> {
                override fun onResponse(
                    call: Call<LoginBackendResponse2>,
                    response: Response<LoginBackendResponse2>
                ) {
                    Log.d("로그인 통신 성공",response.toString())
                    Log.d("로그인 통신 성공", response.body().toString())
                    Log.d("response코드",response.code().toString())

                    when (response.code()) {
                        200-> Toast.makeText(this@account,"인증코드를 이메일로 발송했습니다.", Toast.LENGTH_SHORT).show()
                        401-> Toast.makeText(this@account,"인증코드가 올바르지 않습니다.", Toast.LENGTH_SHORT).show()
                        403-> Toast.makeText(this@account,"로그인 실패 : 서버 접근 권한이 없습니다.", Toast.LENGTH_SHORT).show()
                        404 -> Toast.makeText(this@account, "로그인 실패 : 아이디나 비번이 올바르지 않습니다", Toast.LENGTH_LONG).show()
                        500 -> Toast.makeText(this@account, "로그인 실패 : 서버 오류", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<LoginBackendResponse2>, t: Throwable) {
                    Log.d("로그인 통신 실패",t.message.toString())
                    Log.d("로그인 통신 실패","fail")
                }
            })
        }


        //email 코드 체크부분
        binding.checkEmail.setOnClickListener {

            val code = binding.code.text.toString().trim()//trim : 문자열 공백제거
            val jsonObject= JSONObject()
            jsonObject.put("email",email)
            jsonObject.put("verifyCode",code)
            println(jsonObject)





            val api = Api.create()

            api.check_email(JsonParser.parseString(jsonObject.toString()))
                .enqueue(object : Callback<LoginBackendResponse3> {
                    override fun onResponse(
                        call: Call<LoginBackendResponse3>,
                        response: Response<LoginBackendResponse3>
                    ) {
                        Log.d("로그인 통신 성공",response.toString())
                        Log.d("로그인 통신 성공", response.body().toString())
                        Log.d("response코드",response.code().toString())

                        when (response.code()) {
                            200-> Toast.makeText(this@account,"인증코드가 확인되었습니다.", Toast.LENGTH_SHORT).show()
                            401-> Toast.makeText(this@account,"인증코드가 올바르지 않습니다.", Toast.LENGTH_SHORT).show()
                            403-> Toast.makeText(this@account,"로그인 실패 : 서버 접근 권한이 없습니다.", Toast.LENGTH_SHORT).show()
                            404 -> Toast.makeText(this@account, "로그인 실패 : 아이디나 비번이 올바르지 않습니다", Toast.LENGTH_LONG).show()
                            500 -> Toast.makeText(this@account, "로그인 실패 : 서버 오류", Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(call: Call<LoginBackendResponse3>, t: Throwable) {
                        Log.d("로그인 통신 실패",t.message.toString())
                        Log.d("로그인 통신 실패","fail")
                    }
                })







        }

        //뒤로가기 처리
        val callback = object : OnBackPressedCallback(true /* enabled by default */) {
            override fun handleOnBackPressed() {
                // 뒤로가기 이벤트가 발생했을 때 수행할 작업
                // 예를 들어 특정 상황에서만 뒤로가기를 처리하고 싶은 경우 여기에 작성

                val intent = Intent(this@account, first::class.java)
                startActivity(intent)
                finish()

            }
        }



        this@account.onBackPressedDispatcher.addCallback(this, callback)


    }
}