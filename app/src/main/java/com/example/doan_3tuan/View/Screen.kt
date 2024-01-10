package com.example.doan_3tuan.View

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.doan_3tuan.View.Login.ForgotPasswordScreen
import com.example.doan_3tuan.View.Login.LoginScreen
import com.example.doan_3tuan.View.Login.RegisterScreen

sealed class Screen(val route :String) {
    object Login :Screen("Login_Screen")
    object Register :Screen("Register_Screen")
    object ForgotPassword : Screen("ForgotPassword_Screen")
}

@Composable
fun NavGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ){
        composable(Screen.Login.route){
            LoginScreen(navController)
        }
        composable(Screen.Register.route){
            RegisterScreen(navController)
        }
        composable(Screen.ForgotPassword.route){
            ForgotPasswordScreen(navController)
        }
    }
}