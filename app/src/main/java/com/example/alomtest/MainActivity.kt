package com.example.alomtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.alomtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(Home())
        binding.bottomNavigationView .selectedItemId=R.id.home //11/09 추가 - 첫 화면을 홈화면으로 설정

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.exercise -> {replaceFragment(exercise())

                }
                R.id.food -> replaceFragment(food())
                R.id.home -> replaceFragment(Home())
                R.id.profile -> replaceFragment(Profile())
                R.id.settings -> replaceFragment(mypage_main())
                else ->{

                }
            }
            true
        }

    }


    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }



}