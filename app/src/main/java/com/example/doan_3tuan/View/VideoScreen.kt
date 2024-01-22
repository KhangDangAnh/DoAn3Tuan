package com.example.doan_3tuan.View

import android.net.Uri
import android.provider.MediaStore.Video
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.doan_3tuan.View.Component.NavBottomAppBar
import com.example.doan_3tuan.ViewModel.VideoViewModel
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoScreen(navController : NavController) {
    val ctx = LocalContext.current
    val viewModel = VideoViewModel(ctx)
    var video by remember {
        mutableStateOf(viewModel.getVideo())
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "SortVideo",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White,
                        fontSize = 25.sp
                    )
                        },
                colors = TopAppBarDefaults.topAppBarColors(Color(0xFF07899B))
            )
        }, bottomBar = {
            NavBottomAppBar(navController)
        }
    ) {
       LazyColumn(modifier = Modifier
           .padding(it)
           .background(Color.White)
           .fillMaxHeight(),
           ){
           items(video){
               CardVideo(video = it)
           }
       }
    }
}

@Composable
fun CardVideo(video:com.example.doan_3tuan.Model.Video) {
    var videoURL= video.videoURL
    var context = LocalContext.current
    var exoPlayer = ExoPlayer.Builder(context).build()
    var mediaItem = MediaItem.fromUri(Uri.parse(videoURL))

    exoPlayer.setMediaItem(mediaItem)

    Card(modifier = Modifier
        .height(700.dp)
        .fillMaxWidth()
        .padding(5.dp)) {
        Column {
            Text(text = video.title, softWrap = true, fontSize = 20.sp, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(200.dp))
            val playerView = StyledPlayerView(context)
            playerView.player = exoPlayer

            DisposableEffect(AndroidView(factory = {playerView})){
                exoPlayer.prepare()
                exoPlayer.playWhenReady

                onDispose {
                    exoPlayer.release()
                }
            }
        }
    }
}