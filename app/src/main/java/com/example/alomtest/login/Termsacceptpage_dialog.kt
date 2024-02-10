package com.example.alomtest.login


import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.example.alomtest.R


class Termsacceptpage_dialog(context: Context) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms_accept_page)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val checkBox1: CheckBox = findViewById(R.id.checkBox)
        val checkBox2: CheckBox = findViewById(R.id.checkBox2)
        val checkBox3: CheckBox = findViewById(R.id.checkBox3)
        val checkBox4: CheckBox = findViewById(R.id.checkBox4)
        val imageView5: ImageView = findViewById(R.id.imageView5)

        val allacept:ImageView = findViewById(R.id.allaccept)

        val textView5 : TextView = findViewById<TextView>(R.id.textView5)

        allacept.setOnClickListener {
            checkBox1.isChecked = true
            checkBox2.isChecked = true
            checkBox3.isChecked = true
            checkBox4.isChecked = true
        }
        // 체크박스 상태 변경 시 호출될 메서드를 정의합니다.
        val onCheckedChangeListener = { _: Any?, _: Boolean ->
            if (checkBox1.isChecked && checkBox2.isChecked && checkBox3.isChecked && checkBox4.isChecked) {
                // 모든 체크박스가 선택되었을 때 이미지를 변경합니다.
                imageView5.setImageResource(R.drawable.retangle_yelloew)
                allacept.setImageResource(R.drawable.all_accept_blue)
                textView5.setTextColor(ContextCompat.getColor(context, android.R.color.black))
                imageView5.setOnClickListener{
                    val intent = Intent(context, terms::class.java)
                    intent.putExtra("fromTermsAcceptPage", true)
                    context.startActivity(intent)






                    dismiss()
                }
            } else {
                // 어느 하나의 체크박스라도 선택되지 않았을 때 기본 이미지로 변경합니다.
                imageView5.setImageResource(R.drawable.rectangle_gray)
                allacept.setImageResource(R.drawable.gray_accept)
                //textView5.setTextColor(ContextCompat.getColor(context, R.color.gray)) // 텍스트 색상을 회색으로 설정
                imageView5.setOnClickListener(null)
            }
        }





        // 체크박스들에 대한 리스너를 등록합니다.
        checkBox1.setOnCheckedChangeListener(onCheckedChangeListener)
        checkBox2.setOnCheckedChangeListener(onCheckedChangeListener)
        checkBox3.setOnCheckedChangeListener(onCheckedChangeListener)
        checkBox4.setOnCheckedChangeListener(onCheckedChangeListener)
    }
}
