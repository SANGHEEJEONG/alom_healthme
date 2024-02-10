package com.example.alomtest

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.alomtest.databinding.ActivityMainBinding
import com.example.alomtest.databinding.FragmentMypageMainBinding
import com.example.alomtest.exercise.mainpage.exercise_main_copy
import com.example.alomtest.food.mainpage.Food
import com.example.alomtest.home.Home
import com.example.alomtest.mypage.mypage_main
import com.example.alomtest.retrofit.Api
import com.example.alomtest.retrofit.LoginBackendResponse13
import com.example.alomtest.retrofit.LoginBackendResponse7
import com.google.gson.JsonParser
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var binding2:FragmentMypageMainBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //loadData

        //레트로핏으로 서버에 저장
        val email=intent.getStringExtra("useremail").toString()



        val jsonObject= JSONObject()

        jsonObject.put("email",email)


        var usertoken = SharedPreferenceUtils.loadData(this@MainActivity, "accessToken", "")
        val refreshtoken = SharedPreferenceUtils.loadData(this@MainActivity, "refreshToken", "")

        Log.d("user토큰",usertoken)
        Log.d("JSON출력",jsonObject.toString())
        val api = Api.create()

        //api.loadData(JsonParser.parseString(jsonObject.toString()))
        api.loadData(accessToken = "Bearer $usertoken",JsonParser.parseString(jsonObject.toString()))
            .enqueue(object : retrofit2.Callback<LoginBackendResponse7> {
                override fun onResponse(
                    call: Call<LoginBackendResponse7>,
                    response: Response<LoginBackendResponse7>
                ) {
                    Log.d("로그인 통신 성공",response.toString())
                    Log.d("로그인 통신 성공", response.body().toString())
                    Log.d("response코드",response.code().toString())

                    when (response.code()) {
                        200-> {Toast.makeText(this@MainActivity,"로드성공", Toast.LENGTH_SHORT).show()
                            val tok: LoginBackendResponse7?=response.body()
                            Log.d("response바디 출력",tok.toString())

                            SharedPreferenceUtils.saveData(this@MainActivity, "height", tok?.height.toString())
                            SharedPreferenceUtils.saveData(this@MainActivity, "weight", tok?.weight.toString())
                            SharedPreferenceUtils.saveData(this@MainActivity, "name", tok?.name.toString())

                            SharedPreferenceUtils.saveData(this@MainActivity, "email", email)
                            SharedPreferenceUtils.saveData(this@MainActivity, "birthday", tok?.birthday.toString().substring(0,10))
                            SharedPreferenceUtils.saveData(this@MainActivity, "gender", tok?.gender.toString())

                            SharedPreferenceUtils.saveData(this@MainActivity, "bmi", tok?.bmi.toString())

                            Log.d("sharedpre에 저장 완료","")






                        }
                        401-> Toast.makeText(this@MainActivity,"서버가 동작하지 않습니다. ", Toast.LENGTH_SHORT).show()
                        403-> Toast.makeText(this@MainActivity,"로그인 실패 : 서버 접근 권한이 없습니다.", Toast.LENGTH_SHORT).show()
                        404 -> Toast.makeText(this@MainActivity, "로그인 실패 : 아이디나 비번이 올바르지 않습니다", Toast.LENGTH_LONG).show()

                        408->{//토큰만료


                            val jsonObject2= JSONObject()
                            jsonObject2.put("email",email)
                            jsonObject2.put("refreshToken",refreshtoken)




                            api.refreshToken(accessToken = "Bearer $usertoken",JsonParser.parseString(jsonObject2.toString())).enqueue(object :
                                retrofit2.Callback<LoginBackendResponse13> {
                                override fun onResponse(
                                    call: Call<LoginBackendResponse13>,
                                    response: Response<LoginBackendResponse13>
                                ) {
                                    Log.d("로그인 통신 성공",response.toString())
                                    Log.d("로그인 통신 성공", response.body().toString())
                                    Log.d("response코드",response.code().toString())




                                    //Log.d("반환 메시지",response.body())


                                    when (response.code()) {
                                        200 -> {

                                            Toast.makeText(this@MainActivity, "access토큰 재발급 성공", Toast.LENGTH_LONG).show()

                                            val tok:LoginBackendResponse13?=response.body()
                                            val accesstoken:String? = tok?.accessToken

                                            SharedPreferenceUtils.saveData(this@MainActivity, "accessToken", accesstoken.toString())



//fragment구현

                                        }
                                        500 -> Toast.makeText(this@MainActivity, "로그인 실패 : 아이디나 비번이 올바르지 않습니다", Toast.LENGTH_LONG).show()
                                        403-> Toast.makeText(this@MainActivity,"로그인 실패 : 서버 접근 권한이 없습니다.", Toast.LENGTH_SHORT).show()

                                        else -> Toast.makeText(this@MainActivity, "LOGIN ERROR", Toast.LENGTH_LONG).show()


                                    }
                                }

                                override fun onFailure(call: Call<LoginBackendResponse13>, t: Throwable) {
                                    // 실패
                                    Log.d("로그인 통신 실패",t.message.toString())
                                    Log.d("로그인 통신 실패","fail")
                                }
                            })


                           //통신 다시한번 실행
                            val jsonObject3= JSONObject()

                            jsonObject3.put("email",email)


                            var usertoken = SharedPreferenceUtils.loadData(this@MainActivity, "accessToken", "")
                            val refreshtoken = SharedPreferenceUtils.loadData(this@MainActivity, "refreshToken", "")

                            Log.d("user토큰",usertoken)
                            Log.d("JSON출력",jsonObject3.toString())




                            api.loadData(accessToken = "Bearer $usertoken",JsonParser.parseString(jsonObject3.toString()))
                                .enqueue(object : retrofit2.Callback<LoginBackendResponse7> {
                                    override fun onResponse(
                                        call: Call<LoginBackendResponse7>,
                                        response: Response<LoginBackendResponse7>
                                    ) {
                                        Log.d("로그인 통신 성공",response.toString())
                                        Log.d("로그인 통신 성공", response.body().toString())
                                        Log.d("response코드",response.code().toString())

                                        when (response.code()) {
                                            200-> {Toast.makeText(this@MainActivity,"로드성공", Toast.LENGTH_SHORT).show()
                                                val tok: LoginBackendResponse7?=response.body()
                                                Log.d("response바디 출력",tok.toString())

                                                SharedPreferenceUtils.saveData(this@MainActivity, "height", tok?.height.toString())
                                                SharedPreferenceUtils.saveData(this@MainActivity, "weight", tok?.weight.toString())
                                                SharedPreferenceUtils.saveData(this@MainActivity, "name", tok?.name.toString())
                                                SharedPreferenceUtils.saveData(this@MainActivity, "email", email)
                                                SharedPreferenceUtils.saveData(this@MainActivity, "birthday", tok?.birthday.toString().substring(0,10))
                                                SharedPreferenceUtils.saveData(this@MainActivity, "gender", tok?.gender.toString())

                                                Log.d("sharedpre에 저장 완료","")






                                            }
                                            401-> Toast.makeText(this@MainActivity,"서버가 동작하지 않습니다. ", Toast.LENGTH_SHORT).show()
                                            403-> Toast.makeText(this@MainActivity,"로그인 실패 : 서버 접근 권한이 없습니다.", Toast.LENGTH_SHORT).show()
                                            404 -> Toast.makeText(this@MainActivity, "로그인 실패 : 아이디나 비번이 올바르지 않습니다", Toast.LENGTH_LONG).show()
                                            500 -> Toast.makeText(this@MainActivity, "로그인 실패 : 서버 오류", Toast.LENGTH_LONG).show()
                                        }
                                    }

                                    override fun onFailure(call: Call<LoginBackendResponse7>, t: Throwable) {
                                        Log.d("로그인 통신 실패",t.message.toString())
                                        Log.d("로그인 통신 실패","fail")
                                    }
                                })












                            //





                        }//408 오류 끝

                        500 -> Toast.makeText(this@MainActivity, "로그인 실패 : 서버 오류", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<LoginBackendResponse7>, t: Throwable) {
                    Log.d("로그인 통신 실패",t.message.toString())
                    Log.d("로그인 통신 실패","fail")
                }
            })



//bottomnav부


        binding = ActivityMainBinding.inflate(layoutInflater)
        binding2 = FragmentMypageMainBinding.inflate(layoutInflater)
        setContentView(binding2.root)
        setContentView(binding.root)
        replaceFragment(Home())
        binding.bottomNavigationView.selectedItemId=R.id.home //11/09 추가 - 첫 화면을 홈화면으로 설정

        binding.bottomNavigationView.setOnItemSelectedListener { menuItem->
            when(menuItem.itemId){
                //R.id.exercise -> {replaceFragment()
               R.id.exercise -> {

                   replaceFragment(exercise_main_copy())
                   menuItem.setIcon(R.drawable.exercise_selected)
                   //R.id.exercise -> {replaceFragment(exercise())
                }
                R.id.food -> {
                    replaceFragment(Food())
                    menuItem.setIcon(R.drawable.food_selected) // 아이콘 변경
                }
                R.id.home -> {
                    replaceFragment(Home())
                    menuItem.setIcon(R.drawable.home_selected) // 아이콘 변경
                }
                R.id.profile -> {
                    replaceFragment(Profile())
                    menuItem.setIcon(R.drawable.schedule_selected) // 아이콘 변경
                }
                R.id.settings -> {
                    replaceFragment(mypage_main())
                    menuItem.setIcon(R.drawable.mypage_selected) // 아이콘 변경
                }
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