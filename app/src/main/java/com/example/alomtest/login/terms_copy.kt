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
import com.example.alomtest.databinding.TermsLayoutBinding
import com.example.alomtest.retrofit.Api
import com.example.alomtest.retrofit.LoginBackendResponse2
import com.example.alomtest.retrofit.LoginBackendResponse3
import com.google.gson.JsonParser
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class terms_copy : AppCompatActivity() {
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

                val intent = Intent(this@terms_copy, first::class.java)
                startActivity(intent)
                finish()

            }
        }



        this@terms_copy.onBackPressedDispatcher.addCallback(this, callback)


    }
}