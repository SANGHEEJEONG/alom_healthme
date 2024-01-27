package com.example.alomtest.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.alomtest.databinding.AccountLayoutBinding
import com.example.alomtest.databinding.FirstLayoutBinding
import com.example.alomtest.databinding.SplashactivityLayoutBinding
import com.example.alomtest.login.first

class SplashActivity : AppCompatActivity() {
    lateinit var binding: SplashactivityLayoutBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashactivityLayoutBinding.inflate(layoutInflater)

        setContentView(binding.root)

        Handler().postDelayed({
            val intent = Intent(this, first::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            finish()
        },DURATION)

    }
    companion object {
        private const val DURATION : Long = 3000
    }


}