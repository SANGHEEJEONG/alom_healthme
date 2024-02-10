package com.example.alomtest.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.alomtest.R
import com.example.alomtest.databinding.ForgetPasswordLayoutBinding
import com.example.alomtest.databinding.LoginCreateAccountSuccessBinding

class create_account_success:AppCompatActivity() {
    lateinit var binding: LoginCreateAccountSuccessBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginCreateAccountSuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)




//        binding.background.setOnClickListener {
//
//            Log.d("root", "디버깅")
//            val intent = Intent(this@create_account_success, first::class.java)
//            startActivity(intent)
//            finish()
//
//
//        }

        val topLayout = findViewById<ConstraintLayout>(R.id.background)
        topLayout.setOnClickListener {
            Log.d("root", "디버깅")
            val intent = Intent(this@create_account_success, first::class.java)
            startActivity(intent)
            finish()
        }


    }


}