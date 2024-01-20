package com.example.doan_3tuan.ViewModel

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.doan_3tuan.Model.NavRoot
import com.example.doan_3tuan.View.Screen.CareTrendingScreen
import com.example.doan_3tuan.View.Screen.Chitiet_Screen
import com.example.doan_3tuan.View.Screen.FavoutiteTrendingScreen
import com.example.doan_3tuan.View.Screen.TrangChuScreen
import com.example.doan_3tuan.View.Screen.TrendingScreen
import com.example.doan_3tuan.View.Screen.VideoScreen

@Composable
fun RootGraph(navHostController: NavHostController,viewModelTrendingViewModel : TrendingViewModel)
{
   NavHost(navController = navHostController, startDestination = NavRoot.trangchu.root)
   {
       composable(NavRoot.trangchu.root)
       {
           TrangChuScreen(navCotroller = navHostController)
       }
       composable(NavRoot.chitiet.root + "?link={link}", arguments = listOf(navArgument("link"){nullable = true}))
       {
           val url = it.arguments?.getString("link")
           Chitiet_Screen(navHostController,url?:"")
       }
       composable(NavRoot.xuhuong.root){
           TrendingScreen(viewModelTrendingViewModel,navHostController)
       }
       composable(NavRoot.xuhuongquantam.root){
           CareTrendingScreen(viewModel = viewModelTrendingViewModel)
       }
       composable(NavRoot.xuhuongyeuthich.root){
           FavoutiteTrendingScreen(viewModel = viewModelTrendingViewModel)
       }
       composable(NavRoot.video.root){
           VideoScreen()
       }
   }
}