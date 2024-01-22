package com.example.doan_3tuan.Model

data class AccountState(
    var id:String? ="",
    val email:String = "",
    val password:String= "",
    val rePassword:String ="",
    val success:Boolean = false
)
data class SignInState(
    val isSignInSuccessful:Boolean =false,
    val signInError:String? = null
)