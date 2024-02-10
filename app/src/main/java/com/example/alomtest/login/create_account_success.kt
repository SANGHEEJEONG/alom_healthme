package com.example.alomtest.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.alomtest.databinding.ForgetPasswordLayoutBinding
import com.example.alomtest.databinding.LoginCreateAccountSuccessBinding

class create_account_success:AppCompatActivity() {
    lateinit var binding: LoginCreateAccountSuccessBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginCreateAccountSuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }




}