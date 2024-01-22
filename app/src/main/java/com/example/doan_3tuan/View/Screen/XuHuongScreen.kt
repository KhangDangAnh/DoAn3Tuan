package com.example.doan_3tuan.View.Screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.doan_3tuan.Model.BottomNavigationItem
import com.example.doan_3tuan.Model.NavRoot
import com.example.doan_3tuan.Model.LoadRss.UiResult
import com.example.doan_3tuan.R
import com.example.doan_3tuan.View.Component.Baiviet_Card
import com.example.doan_3tuan.View.Component.NavBottomAppBar
import com.example.doan_3tuan.ViewModel.BVviewModel.HomeViewModel
import com.example.doan_3tuan.ViewModel.BVviewModel.NewsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun XuhuongScreen(navController : NavController)
{
    val viewModel: HomeViewModel = viewModel(modelClass = HomeViewModel::class.java)
    val homeState = viewModel.uiState.collectAsStateWithLifecycle()
    val state = homeState.value
    when (state) {
        is UiResult.Fail -> {
            val ctx = LocalContext.current
            val viewModel = NewsViewModel(ctx)
            var news = viewModel.getNews()
            Scaffold(topBar = {
                TopAppBar(
                    title = { Text(text = "Xu hướng")}
                )
            }, bottomBar = {
                NavBottomAppBar(navController = navController)
            }) {
                LazyColumn(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(it)
                ) {
                   item{
                       Text(text = "Bạn đang ngoại tuyến", fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
                       Text(text = "Tin Cũ", fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
                   }
                    items(news){
                        Baiviet_Card(item = it)
                        {
                            navController.navigate(NavRoot.chitiet.root +"?link=${it.link}")
                        }
                    }
                }
            }
        }
        UiResult.Loading -> {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            }
        }
        is UiResult.Success -> {
            val ctx = LocalContext.current
            val viewModel = NewsViewModel(ctx)
            var news = viewModel.getNews()
            var data = state.data
            Scaffold(topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(Color(0xFF07899B)),
                    title = { Text(text = "Xu hướng")}
                )
            }, bottomBar = {
                NavBottomAppBar(navController = navController)
            }) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(it)
                ) {
                    LazyColumn(
                        Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        item{
                            Text(text = "Mới nhất", fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
                        }
                        items(data.baiviet.take(5))
                        {
                            Baiviet_Card(item = it) {
                                navController.navigate(NavRoot.chitiet.root + "?link=${it.link}")
                            }
                        }
                        item{
                            Text(text = "Tin Cũ", fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
                        }
                        items(news)
                        {
                            Baiviet_Card(item = it) {
                                navController.navigate(NavRoot.chitiet.root + "?link=${it.link}")
                            }
                        }
                    }
                }
            }
        }
    }
}