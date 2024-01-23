package com.example.alomtest.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.alomtest.MainActivity
import com.example.alomtest.databinding.ActivityMainBinding
import com.example.alomtest.databinding.FirstLayoutBinding
import kotlin.system.exitProcess

class first : AppCompatActivity() {
    lateinit var binding: FirstLayoutBinding
    private var backPressedTime: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FirstLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.login1.setOnClickListener{
            val intent = Intent(this, login::class.java)
            startActivity(intent)
            finish()
        }
        binding.account1.setOnClickListener{
            val intent = Intent(this, account::class.java)
            startActivity(intent)
            finish()
        }

        //출시 후 지워야 하는 코드!
        binding.imageView.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }







//240107 뒤로가기 매커니즘 추가
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val currentTime = System.currentTimeMillis()

                if (currentTime - backPressedTime > 3000) {
                    // 첫 번째 뒤로가기
                    backPressedTime = currentTime
                    // 3초 안에 두 번 뒤로가기를 누르면 앱 종료

                    Toast.makeText(this@first, "버튼을 한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()

                    Handler(Looper.getMainLooper()).postDelayed({
                        backPressedTime = 0
                    }, 3000)
                } else {
                    // 두 번째 뒤로가기
                    this@first.finish()
                    exitProcess(0)
                }
            }
        }

        this@first.onBackPressedDispatcher.addCallback(this, callback)



    }
}