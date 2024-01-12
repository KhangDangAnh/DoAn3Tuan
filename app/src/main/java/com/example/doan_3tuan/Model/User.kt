package com.example.doan_3tuan.Model

data class User (
    var email :String= "",
    var password:String = ""
)

data class SignInResult(
    val data: UserData?,
    val errorMessage:String?
)

data class UserData(
    val userId :String,
    val username:String?,
    val profilePictureUrl:String?,
    val email: String?
)