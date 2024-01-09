package com.example.doan_3tuan.ViewModel

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.doan_3tuan.Model.NavRoot
import com.example.doan_3tuan.View.Chitiet_Screen
import com.example.doan_3tuan.View.TrangChuScreen
@Composable
fun RootGraph(navHostController: NavHostController)
{
   NavHost(navController = navHostController, startDestination = NavRoot.trangchu.root)
   {
       composable(NavRoot.trangchu.root)
       {
           TrangChuScreen(navCotroller = navHostController)
       }
       composable(NavRoot.chitiet.root)
       {
           Chitiet_Screen(navHostController)
       }
   }
}