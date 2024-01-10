package com.example.alomtest.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.example.alomtest.R
import com.example.alomtest.databinding.ForgetPasswordLayoutBinding

import com.example.alomtest.retrofit.Api
import com.example.alomtest.retrofit.LoginBackendResponse10
import com.example.alomtest.retrofit.LoginBackendResponse9
import com.google.gson.JsonParser
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class forget_password : AppCompatActivity() {
    lateinit var binding: ForgetPasswordLayoutBinding
    lateinit var email: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = ForgetPasswordLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.sendCode.setOnClickListener {
            email = binding.emailInput.text.toString().trim()//trim : 문자열 공백제거
            val jsonObject= JSONObject()
            jsonObject.put("email",email)
            Log.d("json출력", jsonObject.toString())



            // == 백엔드 통신 부분 ==
            val api = Api.create()

            api.send_email_forget_password(JsonParser.parseString(jsonObject.toString()))
                .enqueue(object : Callback<LoginBackendResponse9> {
                    override fun onResponse(
                        call: Call<LoginBackendResponse9>,
                        response: Response<LoginBackendResponse9>
                    ) {
                        Log.d("로그인 통신 성공",response.toString())
                        Log.d("로그인 통신 성공", response.body().toString())
                        Log.d("response코드",response.code().toString())

                        when (response.code()) {
                            200-> Toast.makeText(this@forget_password,"인증코드를 이메일로 발송했습니다.", Toast.LENGTH_SHORT).show()
                            401-> Toast.makeText(this@forget_password,"서버가 동작하지 않습니다. ", Toast.LENGTH_SHORT).show()
                            403-> Toast.makeText(this@forget_password,"로그인 실패 : 서버 접근 권한이 없습니다.", Toast.LENGTH_SHORT).show()
                            404 -> Toast.makeText(this@forget_password, "로그인 실패 : 아이디나 비번이 올바르지 않습니다", Toast.LENGTH_LONG).show()
                            500 -> Toast.makeText(this@forget_password, "로그인 실패 : 서버 오류", Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(call: Call<LoginBackendResponse9>, t: Throwable) {
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

            api.check_email_forget_password(JsonParser.parseString(jsonObject.toString()))
                .enqueue(object : Callback<LoginBackendResponse10> {
                    override fun onResponse(
                        call: Call<LoginBackendResponse10>,
                        response: Response<LoginBackendResponse10>
                    ) {
                        Log.d("로그인 통신 성공",response.toString())
                        Log.d("로그인 통신 성공", response.body().toString())
                        Log.d("response코드",response.code().toString())

                        when (response.code()) {
                            200-> {
                                Toast.makeText(this@forget_password,"인증코드가 확인되었습니다.", Toast.LENGTH_SHORT).show()

//                                val info: userInfo? =null
//                                info?.Email=email


                                binding.nextBtn.setBackgroundResource(R.drawable.button_sample3)
                                    binding.nextBtn.setOnClickListener {
                                    Log.d("intent 진입 전 로그","")
                                    //val intent = Intent(this@forget_password, resetpassword::class.java)
                                        val intent = Intent(this@forget_password,resetpassword::class.java)
                                        Log.d("activity 진입 전 로그","")
                                    startActivity(intent)
                                    Log.d("activity 진입 전 로그","")
                                    finish()

                                }

//misterjerry@sju.ac.kr


                            }
                            401-> Toast.makeText(this@forget_password,"인증코드가 올바르지 않습니다.", Toast.LENGTH_SHORT).show()
                            403-> Toast.makeText(this@forget_password,"로그인 실패 : 서버 접근 권한이 없습니다.", Toast.LENGTH_SHORT).show()
                            404 -> Toast.makeText(this@forget_password, "로그인 실패 : 아이디나 비번이 올바르지 않습니다", Toast.LENGTH_LONG).show()
                            500 -> Toast.makeText(this@forget_password, "로그인 실패 : 서버 오류", Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(call: Call<LoginBackendResponse10>, t: Throwable) {
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

                val intent = Intent(this@forget_password, first::class.java)
                startActivity(intent)
                finish()

            }
        }



        this@forget_password.onBackPressedDispatcher.addCallback(this, callback)


    }
}