package com.example.doan_3tuan

import android.app.Notification.CarExtender
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CareTrendingScreen(viewModel: TrendingViewModel) {
    val trending by remember {
        mutableStateOf(viewModel.getTrending())
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Được quan tâm nhiều nhất",
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
           items(trending){
               CardTrend(trending = it)
           }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FaviritoTrendingScreen(viewModel: TrendingViewModel) {
    val trending by remember {
        mutableStateOf(viewModel.getTrending())
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Được yêu thích nhiều nhất",
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
            items(trending){
                CardTrend(trending = it)
            }
        }
    }
}
@Composable
fun CardTrend(trending:Trending){
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