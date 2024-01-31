package com.example.alomtest.food.foodcustom01

import android.content.Context
import org.json.JSONObject

class UserFoodClass (context: Context) {
    private val sharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    fun saveUserData(foodname: String, gram: Int, time: String) {
        val userDataObject = JSONObject().apply {
            put("foodname", foodname)
            put("gram", gram)
            put("time", time)
        }

        editor.putString(foodname, userDataObject.toString())
        editor.apply()
    }

    fun loadUserData(foodname: String): Triple<String?, Int, String?> {
        val userDataString = sharedPreferences.getString(foodname, null)

        return if (userDataString != null) {
            val userDataObject = JSONObject(userDataString)
            val loadedName = userDataObject.getString("foodname")
            val gram = userDataObject.getInt("gram")
            val time = userDataObject.getString("time")

            Triple(loadedName, gram, time)
        } else {
            Triple(null, -1, null)
        }
    }
}