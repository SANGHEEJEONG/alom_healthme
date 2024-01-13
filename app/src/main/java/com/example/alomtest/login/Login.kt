package com.example.alomtest.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import retrofit2.Call
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.alomtest.retrofit.Api
import com.example.alomtest.retrofit.LoginBackendResponse
import com.example.alomtest.MainActivity
import com.example.alomtest.retrofit.UserModel
import com.example.alomtest.databinding.LoginLayoutBinding
import com.example.alomtest.mypage.mypage_main
import com.example.alomtest.retrofit.email
import com.google.gson.JsonParser
import org.json.JSONObject
import retrofit2.Callback
import retrofit2.Response

class login : AppCompatActivity() {
    lateinit var binding: LoginLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.forgetBtn.setOnClickListener {
            val intent = Intent(this@login, forget_password::class.java)
            startActivity(intent)
            finish()
        }

        binding.loginbutton.setOnClickListener {
            val id = binding.loginemail.text.toString().trim()//trim : 문자열 공백제거
            val pw = binding.loginpassword.text.toString().trim()

            saveData(id, pw)//db (shared preference)에 데이터 저장 (자동 로그인 용)

            // == 백엔드 통신 부분 ==
            val api = Api.create()//
            val data = UserModel(id, pw)
            val jsonObject=JSONObject()
            jsonObject.put("email",data.id)
            jsonObject.put("password",data.pw)



            JsonParser.parseString(jsonObject.toString())


            println(JsonParser.parseString(jsonObject.toString()))


            api.userLogin(JsonParser.parseString(jsonObject.toString())).enqueue(object : Callback<LoginBackendResponse> {
                override fun onResponse(
                    call: Call<LoginBackendResponse>,
                    response: Response<LoginBackendResponse>
                ) {
                    Log.d("로그인 통신 성공",response.toString())
                    Log.d("로그인 통신 성공", response.body().toString())
                    Log.d("response코드",response.code().toString())
                    //Log.d("반환 메시지",response.body())


                    when (response.code()) {
                        200 -> {
                            saveData(id, pw)
                            Toast.makeText(this@login, "로그인 성공", Toast.LENGTH_LONG).show()

                            val tok:LoginBackendResponse?=response.body()
                            val accesstoken:String? = tok?.accessToken
                            val refreshtoken:String? = tok?.refreshToken
                            SharedPreferenceUtils.saveData(this@login, "accessToken", (accesstoken.toString()))
                            SharedPreferenceUtils.saveData(this@login, "refreshToken", (refreshtoken.toString()))

                            SharedPreferenceUtils.saveData(this@login, "email", data.id.toString())


                            //인텐트를 이용하여 화면 전환
                            val intent = Intent(this@login, MainActivity::class.java)
                            intent.putExtra("useremail",data.id.toString())
                            startActivity(intent)
                            finish()

                        }
                        500 -> Toast.makeText(this@login, "로그인 실패 : 아이디나 비번이 올바르지 않습니다", Toast.LENGTH_LONG).show()
                        403->Toast.makeText(this@login,"로그인 실패 : 서버 접근 권한이 없습니다.",Toast.LENGTH_SHORT).show()

                        else -> Toast.makeText(this@login, "LOGIN ERROR", Toast.LENGTH_LONG).show()


                    }
                }

                override fun onFailure(call: Call<LoginBackendResponse>, t: Throwable) {
                    // 실패
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

                val intent = Intent(this@login, first::class.java)
                startActivity(intent)
                finish()

            }
        }



        this@login.onBackPressedDispatcher.addCallback(this, callback)







    }fun saveData( id : String, pw : String){
        val prefID = this.getSharedPreferences("userID", MODE_PRIVATE)
        val prefPW = this.getSharedPreferences("userPW", MODE_PRIVATE)
        val editID = prefID.edit()
        val editPW = prefPW.edit()
        editID.putString("id", id)
        editPW.putString("pw", pw)
        editID.apply()//save
        editPW.apply()//save
        Log.d("로그인 데이터", "saved")
    }


}
