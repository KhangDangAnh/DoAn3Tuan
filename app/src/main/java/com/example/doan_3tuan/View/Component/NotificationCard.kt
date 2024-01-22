package com.example.doan_3tuan.View.Component

import android.widget.Toast
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.doan_3tuan.Model.Notification
import com.example.doan_3tuan.ViewModel.NotificationViewModelFirebase

@Composable
fun NotificationCard(notification: Notification,onClickDelete:()->Unit) {
    var context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding()
                .background(Color(0x5E43A6E9)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(3.dp))
            AsyncImage(model = notification.imageURL, contentDescription = null,modifier = Modifier
                .background(Color.White)
                .size(80.dp)
                )
            Column(
                modifier = Modifier
                    .width(210.dp)
                    .padding(10.dp)

            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(

                        imageVector = Icons.Default.Notifications,
                        contentDescription = null)

                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = if (notification.title.length > 18)
                            notification.title.substring(0, 18)+"..."
                        else notification.title, fontSize = 20.sp
                    )
                }
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = if (notification.content.length > 55)
                        notification.content.substring(0, 55)+"..."
                    else notification.content,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = notification.time,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
            IconButton(onClick = {
                onClickDelete
                Toast.makeText(context,"Xóa thành công",Toast.LENGTH_SHORT).show()
            }) {
                Icon(imageVector = Icons.Default.Delete,
                    contentDescription = "",
                    tint = Color.White,
                )
            }
        }
    }
}