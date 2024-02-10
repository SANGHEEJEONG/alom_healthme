package com.example.alomtest.food.mainpage

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.TextView
import com.example.alomtest.R

class Food_dialog(context: Context, private val dataTitle: String, private val foodSelect: String="", // 새로 추가된 필드
                  private val foodTime: String="",private val calories:Int=0,private val totalCalories: Int) : Dialog(context){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_dialog)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val dialogTitle = findViewById<TextView>(R.id.dialogTitle)
        val dialogFoodClock = findViewById<TextView>(R.id.dialog_FoodClock)
        val dialogFoodName = findViewById<TextView>(R.id.dialog_Foodname)
        val dialogCalories = findViewById<TextView>(R.id.dialog_FoodCal)
        val calcalories = if(dataTitle !="") {
            (dataTitle.toInt() * 0.01 * calories).toInt()
        }else{
            calories
        }

        val calorieswarning = findViewById<TextView>(R.id.calories_warning)
        if(totalCalories>2500){calorieswarning.visibility= View.VISIBLE}else{calorieswarning.visibility=View.GONE
        }
        if(dataTitle==""){dialogTitle.text="100g"}else{dialogTitle.text = "${dataTitle}g"}
        dialogFoodClock.text=foodTime
        dialogFoodName.text=foodSelect
        dialogCalories.text ="${calcalories}kcal"

    }
}