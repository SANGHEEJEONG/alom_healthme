package com.example.alomtest.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import com.example.alomtest.R
import com.example.alomtest.databinding.Account2LayoutBinding
import com.example.alomtest.databinding.InformationLayoutBinding
import com.example.alomtest.retrofit.Api
import com.example.alomtest.retrofit.LoginBackendResponse2
import com.example.alomtest.retrofit.LoginBackendResponse3
import com.example.alomtest.retrofit.LoginBackendResponse5
import com.example.alomtest.retrofit.LoginBackendResponse6
import com.google.gson.JsonParser
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class information : AppCompatActivity() {
    lateinit var binding: InformationLayoutBinding
    lateinit var email: String
    lateinit var password:String
    var gender:String = "non"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = InformationLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        email=intent.getStringExtra("useremail").toString()
        password=intent.getStringExtra("userpassword").toString()
        Log.d("이메일 인텐트 테스트",email)
        Log.d("비밀번호 인텐트 테스트",password)




        binding.femaleBtn.setOnClickListener {


            if(binding.femaleBtn.isSelected)//이미 버튼이 선택된 경우
            {
                binding.femaleBtn.setBackgroundResource(R.drawable.button_sample3)
                gender="non"
            }
            else {


                gender = "여성"
                binding.femaleBtn.setBackgroundResource(R.drawable.button_sample2)
                binding.maleBtn.setBackgroundResource(R.drawable.button_sample3)
            }

        }
        binding.maleBtn.setOnClickListener {
            if(binding.maleBtn.isSelected){
                binding.maleBtn.setBackgroundResource(R.drawable.button_sample3)
                gender="non"
            }
            else{
                gender="남성"

                binding.femaleBtn.setBackgroundResource(R.drawable.button_sample3)
                binding.maleBtn.setBackgroundResource(R.drawable.button_sample2)

            }

        }

//        if(binding.maleBtn.isSelected==true){
//            //binding.femaleBtn.isSelected =false
//
//            binding.maleBtn.setBackgroundResource(R.drawable.button_sample2)
//        }
//        if(binding.femaleBtn.isSelected==true){
//            //binding.maleBtn.isSelected =false
//            binding.femaleBtn.setBackgroundResource(R.drawable.button_sample2)
//
//        }

        binding.nextBtn.setOnClickListener {

            Log.d("성별 로그",gender)

            if(gender!="여성" && gender!="남성"){//예외처리부 - 성별이 선택되지 않은경우
                Toast.makeText(this@information,"성별을 선택해주세요", Toast.LENGTH_SHORT).show()
            }
            else{

            val intent = Intent(this@information,terms::class.java)
            intent.putExtra("useremail",email)
            intent.putExtra("userpassword",password)
            intent.putExtra("username",binding.name.text.toString())
            intent.putExtra("userbirth",binding.birthday.text.toString())
            intent.putExtra("usergender",gender.toString())
            intent.putExtra("userheight",binding.height.text.toString())
            intent.putExtra("userweight",binding.weight.text.toString())
            startActivity(intent)
            finish()






             //== 백엔드 통신 부분 ==
//            val api = Api.create()

//            api.newuserinput(JsonParser.parseString(jsonObject.toString()))
//                .enqueue(object : Callback<LoginBackendResponse5> {
//                    override fun onResponse(
//                        call: Call<LoginBackendResponse5>,
//                        response: Response<LoginBackendResponse5>
//                    ) {
//                        Log.d("로그인 통신 성공",response.toString())
//                        Log.d("로그인 통신 성공", response.body().toString())
//                        Log.d("response코드",response.code().toString())
//
//                        when (response.code()) {
//                            200-> Toast.makeText(this@information,"인증코드를 이메일로 발송했습니다.", Toast.LENGTH_SHORT).show()
//                            401-> Toast.makeText(this@information,"서버가 동작하지 않습니다. ", Toast.LENGTH_SHORT).show()
//                            403-> Toast.makeText(this@information,"로그인 실패 : 서버 접근 권한이 없습니다.", Toast.LENGTH_SHORT).show()
//                            404 -> Toast.makeText(this@information, "로그인 실패 : 아이디나 비번이 올바르지 않습니다", Toast.LENGTH_LONG).show()
//                            500 -> Toast.makeText(this@information, "로그인 실패 : 서버 오류", Toast.LENGTH_LONG).show()
//                        }
//                    }
//
//                    override fun onFailure(call: Call<LoginBackendResponse5>, t: Throwable) {
//                        Log.d("로그인 통신 실패",t.message.toString())
//                        Log.d("로그인 통신 실패","fail")
//                    }
//                })









        }


        }
        




        //뒤로가기 처리
        val callback = object : OnBackPressedCallback(true /* enabled by default */) {
            override fun handleOnBackPressed() {
                // 뒤로가기 이벤트가 발생했을 때 수행할 작업
                // 예를 들어 특정 상황에서만 뒤로가기를 처리하고 싶은 경우 여기에 작성

                val intent = Intent(this@information, first::class.java)
                startActivity(intent)
                finish()

            }
        }



        this@information.onBackPressedDispatcher.addCallback(this, callback)


    }
}