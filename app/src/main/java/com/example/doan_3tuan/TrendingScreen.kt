package com.example.doan_3tuan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import coil.compose.AsyncImage
import com.example.doan_3tuan.ui.theme.Doan_3tuanTheme
import java.sql.Driver

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrendingScreen(viewModel: TrendingViewModel) {
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
            )
        }
    ) {
        LazyColumn(
            Modifier
                .padding(it)
                .background(Color.White)
                .fillMaxSize()){
            item { ListTrending(title = "Được quan tâm nhiều", viewModel = viewModel) }
            item { ListTrending(title = "Được yêu thích nhiều", viewModel = viewModel) }
        }
    }
}

@Composable
fun ListTrending(title:String, viewModel: TrendingViewModel){
    var trending by remember {
        mutableStateOf(viewModel.getTrending())
    }
    Column() {
        Row {
            Icon(imageVector = Icons.Default.Star, contentDescription = null)
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
        TextButton(onClick = {  }) {
            Text(text = "Đọc thêm", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        }
        Spacer(modifier = Modifier.height(5.dp))
    }
}
@Composable
fun TrendingCard(trending:Trending){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(5.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(model = trending.imageURL, contentDescription = null,modifier = Modifier
                .size(130.dp)
                .clip(
                    CircleShape
                ))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)

            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (trending.title.length > 20)
                            trending.title.substring(0, 20)+"..."
                        else trending.title, fontSize = 30.sp
                    )
                }
                Text(
                    text = trending.time,
                    fontSize = 15.sp,
                    color = Color.Gray
                )
            }
        }
    }
}



