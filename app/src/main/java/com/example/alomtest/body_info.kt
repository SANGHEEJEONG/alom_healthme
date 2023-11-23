// SharedPreferenceUtils.kt

import android.content.Context
import android.content.SharedPreferences

object SharedPreferenceUtils {

    private const val PREF_NAME = "MyPrefs"
    private const val KEY_INITIALIZED = "initialized"
    private const val KEY_HEIGHT = "height"
    private const val KEY_WEIGHT = "weight"
    private const val KEY_BMI = "bmi"
    // 값을 저장하는 함수	    // 값을 저장하는 함수



    fun isInitialized(context: Context): Boolean {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(KEY_INITIALIZED, false)
    }

    // 앱 초기화 함수
    fun initializeApp(context: Context) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        // 앱이 처음 실행되었음을 저장
        editor.putBoolean(KEY_INITIALIZED, true)

        // 여기에 초기화할 데이터가 있으면 추가
        // editor.putString(KEY_HEIGHT, "default_height")
        // editor.putString(KEY_WEIGHT, "default_weight")
        // editor.putString(KEY_BMI, "default_bmi")

        editor.apply()
    }

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
