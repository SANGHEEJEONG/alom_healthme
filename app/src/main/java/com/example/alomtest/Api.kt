package com.example.alomtest

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Api{


    @POST("api/user/login")
    fun userLogin(
//        @Query("user_id") username: String,
//        @Query("password") password: String

        @Body jsonParams: JsonElement
    ): Call<LoginBackendResponse>


    @POST("api/email/verification-request")
    fun send_authetication_code(
        @Body jsonParams : JsonElement
    ): Call<LoginBackendResponse2>



    @POST("api/email/verification")
    fun check_email(
        @Body jsonParams : JsonElement
    ): Call<LoginBackendResponse3>


    companion object {
        private const val BASE_URL = "http://3.34.218.4:8080"
        val gson : Gson =   GsonBuilder().setLenient().create();

        fun create() : Api{

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                //.client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(Api::class.java)
        }
    }

}