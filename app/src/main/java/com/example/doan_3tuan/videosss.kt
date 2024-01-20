package com.example.doan_3tuan
//
//import android.media.browse.MediaBrowser
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.DisposableEffect
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.viewinterop.AndroidView
//import com.example.doan_3tuan.ViewModel.VideoPlayer
//import com.google.android.engage.common.datamodel.Image
//import com.google.android.exoplayer2.SimpleExoPlayer
//import com.google.android.exoplayer2.ui.PlayerView
//import com.google.firebase.Firebase
//import com.google.firebase.storage.storage
//
//@Composable
//fun VideoList() {
//    // Replace with your Firebase Storage path
//    val storageRef = Firebase.storage.reference.child("videos")
//
//    var videos by remember { mutableStateOf(emptyList<String>()) }
//    var selectedVideo by remember { mutableStateOf<String?>(null) }
//
//    // Fetch the list of video URLs from Firebase Storage
//    LaunchedEffect(true) {
//        val videoList = mutableListOf<String>()
//        storageRef.listAll().addOnSuccessListener { result ->
//            result.items.forEach { storageReference ->
//                videoList.add(storageReference.downloadUrl.toString())
//            }
//            videos = videoList
//        }
//    }
//
//    Column {
//        LazyColumn {
//            items(videos) { videoUrl ->
//                VideoItem(
//                    videoUrl = videoUrl,
//                    isSelected = videoUrl == selectedVideo,
//                    onVideoSelected = { selectedVideo = it }
//                )
//            }
//        }
//
//        // Display the selected video using ExoPlayer
//        selectedVideo?.let { videoUrl ->
//            VideoPlayer(videoUrl = videoUrl)
//        }
//    }
//}
//
//@Composable
//fun VideoItem(
//    videoUrl: String,
//    isSelected: Boolean,
//    onVideoSelected: (String) -> Unit
//) {
//    val context = LocalContext.current
//    var player = remember {
//        SimpleExoPlayer.Builder(context).build().apply {
//            setMediaItem(MediaBrowser.MediaItem.fromUri(videoUrl))
//            prepare()
//        }
//    }
//
//    DisposableEffect(videoUrl) {
//        onDispose {
//            player.release()
//        }
//    }
//
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp)
//    ) {
//        AndroidView(
//            factory = {
//                PlayerView(context).apply {
//                    player = player
//                }
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(200.dp)
//                .clickable { onVideoSelected(videoUrl) }
//                .background(if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent)
//        )
//    }
//}
//@Composable
//fun VideoPlayer(videoUrl: String) {
//    val context = LocalContext.current
//    var player = remember {
//        SimpleExoPlayer.Builder(context).build().apply {
//            setMediaItem(MediaBrowser.MediaItem.fromUri(videoUrl))
//            prepare()
//        }
//    }
//
//    DisposableEffect(videoUrl) {
//        onDispose {
//            player.release()
//        }
//    }
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//    ) {
//        AndroidView(
//            factory = {
//                PlayerView(context).apply {
//                    player = player
//                }
//            },
//            modifier = Modifier
//                .fillMaxSize()
//        )
//    }
//}
////@Composable
////fun MainScreen() {
////    val context = LocalContext.current
////    val url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
////    val exoPlayer = ExoPlayer.Builder(context).build()
////    val mediaItem = MediaItem.fromUri(Uri.parse(url))
////    exoPlayer.setMediaItem(mediaItem)
////
////
////    val playerView = StyledPlayerView(context)
////    playerView.player = exoPlayer
////
////    DisposableEffect(AndroidView(factory = {playerView})){
////
////        exoPlayer.prepare()
////        exoPlayer.playWhenReady= true
////
////        onDispose {
////            exoPlayer.release()
////        }
////    }
////
////
////}