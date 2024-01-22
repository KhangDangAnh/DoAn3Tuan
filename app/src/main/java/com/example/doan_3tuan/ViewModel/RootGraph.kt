package com.example.doan_3tuan.ViewModel

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.doan_3tuan.Model.LoadRss.Baiviet
import com.example.doan_3tuan.Model.NavRoot
import com.example.doan_3tuan.View.Screen.Chitiet_Screen
import com.example.doan_3tuan.View.Screen.NotificationScreen
import com.example.doan_3tuan.View.Screen.SaveNewsScreen
import com.example.doan_3tuan.View.Screen.TimKiemScreen
import com.example.doan_3tuan.View.Screen.TrangChuScreen
import com.example.doan_3tuan.View.Screen.XuhuongScreen
import com.example.doan_3tuan.ViewModel.BVviewModel.NewsViewModel

@Composable
fun RootGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = NavRoot.trangchu.root)
    {
        composable(NavRoot.timkiem.root+"?email={email}",
            arguments = listOf(navArgument("email") { nullable = true }))
        {
            val email = it.arguments?.getString("email")
            TimKiemScreen(navController = navHostController,email?:"")
        }
        composable(
            NavRoot.luunews.root + "?id={id}",
            arguments = listOf(navArgument("id") { nullable = true })
        )
        {
            val id = it.arguments?.getString("id")
            SaveNewsScreen(navHostController, id ?: "")
        }
        composable(NavRoot.thongbao.root)
        {
            NotificationScreen(navController = navHostController)
        }
    }
}