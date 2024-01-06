package com.example.alomtest.retrofit

data class UserModel(
    var id : String ?= null,
    var pw : String ?= null
)

data class email_authetication(
    var email:String?=null,
    var code:Int?=null,
)

data class email(
    var toEmail:String,
    var title:String,
    var authCode:String,
    var flag:Int)

data class LoginBackendResponse(
    val code: Int=0,
    val error: String="",
    val message: String="",
    val status: Int=0,
    val timestamp: String="",

    val grantType:String="",
    val accessToken:String="",
    val refreshToken:String=""
)
data class LoginBackendResponse2(
    val code: Int=0,
    val bool:Boolean=false,
)
data class LoginBackendResponse3(
    val code: Int=0,
    val bool:Boolean=false,
)

