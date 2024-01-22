package com.example.doan_3tuan.View.Component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.doan_3tuan.Model.NavRoot
import com.example.doan_3tuan.R
import com.example.doan_3tuan.ViewModel.Screens

@Composable
fun NavBottomAppBar(navController: NavController)
{
    androidx.compose.material3.BottomAppBar(
        Modifier.clip(
            RoundedCornerShape(20.dp),
        ),
        containerColor = Color(0xFF07899B)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            IconButton(onClick = { navController.navigate(Screens.HomeScreen.route) }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_article_24),
                    contentDescription = "",
                    tint = Color.DarkGray
                )
            }
            IconButton(onClick = {navController.navigate(NavRoot.video.root)}) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_video_library_24),
                    contentDescription = "",
                    tint = Color.DarkGray
                )
            }
            IconButton(onClick = { navController.navigate(NavRoot.xuhuong.root) }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_data_thresholding_24),
                    contentDescription = "",
                    tint = Color.DarkGray
                )
            }
            IconButton(onClick = {navController.navigate(NavRoot.tienich.root)}) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_dataset_24),
                    contentDescription = "",
                    tint = Color.DarkGray
                )
            }
        }
    }
}