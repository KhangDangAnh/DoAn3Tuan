package com.example.doan_3tuan.View.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.doan_3tuan.Model.NavRoot
import com.example.doan_3tuan.Model.Trending
import com.example.doan_3tuan.View.Component.TrendingCard
import com.example.doan_3tuan.ViewModel.TrendingViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrendingScreen(viewModel: TrendingViewModel,navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Xu Hướng",
                        color = Color.White,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold, textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth())
                },
                colors = TopAppBarDefaults.topAppBarColors(Color(0xFF07899B))
            )
        }
    ) {
        LazyColumn(
            Modifier
                .padding(it)
                .background(Color(0xFFCBEBF7))
                .fillMaxSize()){
            item { ListCareTrending(title = "Được quan tâm nhiều", viewModel = viewModel, navController = navController) }
            item { ListFavouriteTrending(title = "Được yêu thích nhiều", viewModel = viewModel,navController = navController) }
        }
    }
}

@Composable
fun ListCareTrending(title:String, viewModel: TrendingViewModel,navController: NavHostController){
    var trending by remember {
        mutableStateOf(viewModel.getTrending())
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            Icon(imageVector = Icons.Default.Star, contentDescription = null, tint = Color.Yellow)
            Text(text = title, color = Color.Blue)
        }
        LazyColumn(
            Modifier
                .height(450.dp)
                .padding(15.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(trending){
                TrendingCard(trending = it)
            }
        }
        TextButton(onClick = { navController.navigate(NavRoot.xuhuongquantam.root) }) {
            Text(
                text = "Đọc thêm",
                color =Color.Black,
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
    }
}
@Composable
fun ListFavouriteTrending(title:String, viewModel: TrendingViewModel,navController: NavHostController){
    var trending by remember {
        mutableStateOf(viewModel.getTrending())
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            Icon(imageVector = Icons.Default.Star, contentDescription = null, tint = Color.Yellow)
            Text(text = title, color = Color.Blue)
        }
        LazyColumn(
            Modifier
                .height(450.dp)
                .padding(15.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(trending){
                TrendingCard(trending = it)
            }
        }
        TextButton(onClick = { navController.navigate(NavRoot.xuhuongquantam.root) }) {
            Text(
                text = "Đọc thêm",
                color =Color.Black,
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
    }
}



