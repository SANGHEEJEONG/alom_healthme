package com.example.alomtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.alomtest.databinding.FirstLayoutBinding
import com.example.alomtest.databinding.AccountLayoutBinding
import com.example.alomtest.databinding.LoginLayoutBinding

class first : AppCompatActivity() {
    lateinit var binding: FirstLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FirstLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.login1.setOnClickListener{
            val intent = Intent(this,login::class.java)
            startActivity(intent)
            finish()
        }
        binding.account1.setOnClickListener{
            val intent = Intent(this,account::class.java)
            startActivity(intent)
            finish()

        }


    }
}