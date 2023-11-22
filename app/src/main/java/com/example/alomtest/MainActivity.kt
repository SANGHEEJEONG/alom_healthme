package com.example.alomtest

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.fragment.app.Fragment
import com.example.alomtest.databinding.ActivityMainBinding
import com.example.alomtest.databinding.FragmentMypageMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var binding2:FragmentMypageMainBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding2 = FragmentMypageMainBinding.inflate(layoutInflater)
        setContentView(binding2.root)
        setContentView(binding.root)
        replaceFragment(Home())
        binding.bottomNavigationView.selectedItemId=R.id.home //11/09 추가 - 첫 화면을 홈화면으로 설정

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.exercise -> {replaceFragment(exercise_main())

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

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)
        binding2.backiconBtn.setOnClickListener {
            binding.bottomNavigationView.selectedItemId=R.id.home
        }

    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }



}