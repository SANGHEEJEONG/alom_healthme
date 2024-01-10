package com.example.alomtest.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.example.alomtest.R
import com.example.alomtest.databinding.Account2LayoutBinding
import com.example.alomtest.retrofit.Api
import com.example.alomtest.retrofit.LoginBackendResponse2
import com.example.alomtest.retrofit.LoginBackendResponse3
import com.google.gson.JsonParser
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class account2 : AppCompatActivity() {
    lateinit var binding: Account2LayoutBinding
    lateinit var email: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = Account2LayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        email=intent.getStringExtra("useremail").toString()
        Log.d("이메일 인텐트 테스트",email)


//            binding.sendCode.setOnClickListener {
//            email = binding.emailInput.text.toString().trim()//trim : 문자열 공백제거
//                val jsonObject= JSONObject()
//                jsonObject.put("email",email)
//
//
//            // == 백엔드 통신 부분 ==
//            val api = Api.create()
//
//            api.send_authetication_code(JsonParser.parseString(jsonObject.toString()))
//                .enqueue(object : Callback<LoginBackendResponse2> {
//                override fun onResponse(
//                    call: Call<LoginBackendResponse2>,
//                    response: Response<LoginBackendResponse2>
//                ) {
//                    Log.d("로그인 통신 성공",response.toString())
//                    Log.d("로그인 통신 성공", response.body().toString())
//                    Log.d("response코드",response.code().toString())
//
//                    when (response.code()) {
//                        200-> Toast.makeText(this@account2,"인증코드를 이메일로 발송했습니다.", Toast.LENGTH_SHORT).show()
//                        401-> Toast.makeText(this@account2,"인증코드가 올바르지 않습니다.", Toast.LENGTH_SHORT).show()
//                        403-> Toast.makeText(this@account2,"로그인 실패 : 서버 접근 권한이 없습니다.", Toast.LENGTH_SHORT).show()
//                        404 -> Toast.makeText(this@account2, "로그인 실패 : 아이디나 비번이 올바르지 않습니다", Toast.LENGTH_LONG).show()
//                        500 -> Toast.makeText(this@account2, "로그인 실패 : 서버 오류", Toast.LENGTH_LONG).show()
//                    }
//                }
//
//                override fun onFailure(call: Call<LoginBackendResponse2>, t: Throwable) {
//                    Log.d("로그인 통신 실패",t.message.toString())
//                    Log.d("로그인 통신 실패","fail")
//                }
//            })
//        }




        binding.nextBtn.setOnClickListener {

            Log.d("비번1", binding.settingPassword.text.toString())
            Log.d("비번2", binding.password2.text.toString())
            Log.d("같은지 비교", binding.settingPassword.text.toString().equals(binding.password2.text.toString()).toString())

            if(binding.settingPassword.text.toString().equals(binding.password2.text.toString())){
                val intent = Intent(this@account2,information::class.java)
                intent.putExtra("useremail",email)
                intent.putExtra("userpassword",binding.settingPassword.text.toString())
                startActivity(intent)
                finish()

            }
            else{
                Toast.makeText(this, "두 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            }
        }




        //뒤로가기 처리
        val callback = object : OnBackPressedCallback(true /* enabled by default */) {
            override fun handleOnBackPressed() {
                // 뒤로가기 이벤트가 발생했을 때 수행할 작업
                // 예를 들어 특정 상황에서만 뒤로가기를 처리하고 싶은 경우 여기에 작성

                val intent = Intent(this@account2, first::class.java)
                startActivity(intent)
                finish()

            }
        }



        this@account2.onBackPressedDispatcher.addCallback(this, callback)


    }
}