// SharedPreferenceUtils.kt

import android.content.Context
import android.content.SharedPreferences

object SharedPreferenceUtils {

    // 값을 저장하는 함수
    fun saveData(context: Context, key: String, value: String = "") {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    // 값을 불러오는 함수
    fun loadData(context: Context, key: String, defaultValue: String = ""): String {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString(key, defaultValue) ?: ""
    }
}
