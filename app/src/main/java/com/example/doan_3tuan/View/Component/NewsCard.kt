package com.example.doan_3tuan.View.Component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun News_Card(img: Int, title: String, Click: () -> Unit) {
    Card(onClick =  Click , modifier = Modifier.padding(5.dp), colors = CardDefaults.cardColors(
        Color.Transparent)) {
        Row(
            Modifier.fillMaxSize()
        ) {
            Box(
                Modifier.height(120.dp)
            ) {
                Image(
                    painter = painterResource(id = img),
                    contentDescription = "",
                    Modifier.clip(RoundedCornerShape(16.dp))
                )
            }
            Spacer(modifier = Modifier.size(30.dp))
            Box(
                Modifier.height(120.dp)
            ) {
                Text(text = title)
            }
        }
    }
}