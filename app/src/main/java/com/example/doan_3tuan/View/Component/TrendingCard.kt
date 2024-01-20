package com.example.doan_3tuan.View.Component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.doan_3tuan.Model.Trending

@Composable
fun TrendingCard(trending: Trending){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(1.dp),
        colors = CardDefaults.cardColors(Color(0x5E015A5A))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp, 0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(model = trending.imageURL, contentDescription = null,modifier = Modifier
                .size(200.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween

            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (trending.title.length > 25)
                            trending.title.substring(0, 25)+"..."
                        else trending.title, fontSize = 20.sp
                    )
                }
                Text(
                    text = trending.time,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }
}
