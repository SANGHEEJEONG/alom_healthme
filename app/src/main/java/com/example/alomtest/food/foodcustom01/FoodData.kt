package com.example.alomtest.food.foodcustom01


import java.io.Serializable

data class FoodData(
    var title: String,
    var calories: Int
) : Serializable
