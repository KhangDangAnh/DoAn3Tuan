package com.example.doan_3tuan.View

import androidx.compose.runtime.Composable

sealed class Screen(val route :String) {
    object Login :Screen("Login_Screen")
    object Register :Screen("Register_Screen")
    object ForgotPassword : Screen("ForgotPassword_Screen")
}

@Composable
fun NavGraph(){}