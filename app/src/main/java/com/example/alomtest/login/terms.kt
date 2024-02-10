package com.example.alomtest.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import com.example.alomtest.R
import com.example.alomtest.databinding.Account2LayoutBinding
import com.example.alomtest.databinding.InformationLayoutBinding
import com.example.alomtest.databinding.TermsLayoutBinding
import com.example.alomtest.retrofit.Api
import com.example.alomtest.retrofit.LoginBackendResponse2
import com.example.alomtest.retrofit.LoginBackendResponse3
import com.example.alomtest.retrofit.LoginBackendResponse6
import com.google.gson.JsonParser
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class terms : AppCompatActivity() {
    lateinit var binding: TermsLayoutBinding
    lateinit var email: String
    lateinit var password:String
    lateinit var name:String
    lateinit var birthday:String
    lateinit var gender:String
    lateinit var height:String
    lateinit var weight:String





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = TermsLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        email=intent.getStringExtra("useremail").toString()
        password=intent.getStringExtra("userpassword").toString()
        name=intent.getStringExtra("username").toString()
        birthday=intent.getStringExtra("userbirth").toString()
        gender=intent.getStringExtra("usergender").toString()
        weight=intent.getStringExtra("userweight").toString()
        height=intent.getStringExtra("userheight").toString()
        gender=intent.getStringExtra("usergender").toString()





        Log.d("이메일 인텐트 테스트",email)
        Log.d("비밀번호 인텐트 테스트",password)
        //

        Log.d("이름 인텐트 테스트",name)
        Log.d("생년월일 인텐트 테스트",birthday)
        Log.d("성별 인텐트 테스트",gender)
        Log.d("몸무게 인텐트 테스트",weight)
        Log.d("키 인텐트 테스트",height)





//        binding.nextBtn.setOnClickListener {
//
//
//
//
//
//        }







        //뒤로가기 처리
        val callback = object : OnBackPressedCallback(true /* enabled by default */) {
            override fun handleOnBackPressed() {
                // 뒤로가기 이벤트가 발생했을 때 수행할 작업
                // 예를 들어 특정 상황에서만 뒤로가기를 처리하고 싶은 경우 여기에 작성

                val intent = Intent(this@terms, first::class.java)
                startActivity(intent)
                finish()

            }
        }



        this@terms.onBackPressedDispatcher.addCallback(this, callback)


//
//        val imageView4 : ImageView = findViewById<ImageView>(R.id.imageView4)
//        val imageView3 : ImageView = findViewById<ImageView>(R.id.imageView3)
//        val textView5 : TextView = findViewById<TextView>(R.id.textView4)
//
        // 인텐트에서 호출한 액티비티가 TermsacceptpageActivity인 경우에만 드로어블을 변경합니다.
        val fromTermsAcceptPage = intent.getBooleanExtra("fromTermsAcceptPage", false)
        if (fromTermsAcceptPage) {
            Log.d("로그테스트1","진입")
            //binding.termsAgreeBtn.setBackgroundResource(R.drawable.button_sample2)
            binding.nextBtn.setBackgroundResource(R.drawable.button_sample2)
            binding.nextBtn.setOnClickListener{
                val intent = Intent(this, create_account_success::class.java)
                startActivity(intent)
                finish()
            }
        }
        else {
            binding.nextBtn.setOnClickListener(null)
        }
        val customDialog = Termsacceptpage_dialog(this)

        binding.termsAgreeBtn.setOnClickListener{
            customDialog.show()
        }
        customDialog.setOnDismissListener {
            finish()

        //val fromTermsAcceptPage = intent.getBooleanExtra("fromTermsAcceptPage", false)

//            if(fromTermsAcceptPage){
//                Log.d("로그테스트2","진입")
//
//                binding.nextBtn.setBackgroundResource(R.drawable.button_sample2)
//            }
            // 다이얼로그가 닫혔을 때 실행될 코드
        }


    }

    private fun signup(){
        val jsonObject= JSONObject()

        //jsonObject.put("email",email)
//
//            "height": 0,
//            "weight": 0,
//            "gender": "string",
//            "birthday": "2024-01-07T12:32:05.907Z",
//            "name": "string",
//            "email": "string"

        jsonObject.put("height",height)
        jsonObject.put("weight",weight)
        //jsonObject.put("gender",binding.height.text.toString())

        val year = birthday.toString().substring(0, 4)
        val month = birthday.toString().substring(4, 6)
        val day = birthday.toString().substring(6,8)

        val newBirthday = "$year-$month-$day"



        jsonObject.put("birthday",newBirthday)


        jsonObject.put("name",name.toString())
        jsonObject.put("email",email.toString())
        jsonObject.put("password",password)
        jsonObject.put("gender",gender.toString())

        Log.d("json출력",jsonObject.toString())

        //val jsonObject2=JSONObject()

//
//            "id": 0,
//            "email": "string",
//            "name": "string",
//            "password": "string",
//            "authCode": "string"

//            jsonObject2.put("email",email.toString())
//            jsonObject2.put("name",binding.name.text.toString())
//            jsonObject2.put("password",password)




//signup부
        val api = Api.create()

        api.signup(JsonParser.parseString(jsonObject.toString()))
            .enqueue(object : Callback<LoginBackendResponse6> {
                override fun onResponse(
                    call: Call<LoginBackendResponse6>,
                    response: Response<LoginBackendResponse6>
                ) {
                    Log.d("로그인 통신 성공",response.toString())
                    Log.d("로그인 통신 성공", response.body().toString())
                    Log.d("response코드",response.code().toString())

                    when (response.code()) {
                        200-> {Toast.makeText(this@terms,"계정 생성 성공", Toast.LENGTH_SHORT).show()

                            val intent = Intent(this@terms,first::class.java)
                            startActivity(intent)
                            finish()


                        }
                        401-> Toast.makeText(this@terms,"서버가 동작하지 않습니다. ", Toast.LENGTH_SHORT).show()
                        403-> Toast.makeText(this@terms,"로그인 실패 : 서버 접근 권한이 없습니다.", Toast.LENGTH_SHORT).show()
                        404 -> Toast.makeText(this@terms, "로그인 실패 : 아이디나 비번이 올바르지 않습니다", Toast.LENGTH_LONG).show()
                        500 -> Toast.makeText(this@terms, "로그인 실패 : 서버 오류", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<LoginBackendResponse6>, t: Throwable) {
                    Log.d("로그인 통신 실패",t.message.toString())
                    Log.d("로그인 통신 실패","fail")
                }
            })
    }
}