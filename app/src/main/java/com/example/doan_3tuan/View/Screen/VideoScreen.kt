package com.example.doan_3tuan.View.Screen


import android.net.Uri
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.doan_3tuan.Model.LoadRss.ktorClient
import com.example.doan_3tuan.Model.VideoFirestore
import com.example.doan_3tuan.R
import com.example.doan_3tuan.Video
import com.example.doan_3tuan.VideoData
import com.example.doan_3tuan.ViewModel.VideoPlayer
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


val verticalPadding = 12.dp
val horizontalPadding = 10.dp


@Composable
fun VideoScreen() {
    Box(Modifier.background(color = Color.Black)) {
        VideoList()
        VideoHeader()
    }
}
//@Composable
//fun MainScreen() {
//    val context = LocalContext.current
//    val url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
//    val exoPlayer = ExoPlayer.Builder(context).build()
//    val mediaItem = MediaItem.fromUri(Uri.parse(url))
//    exoPlayer.setMediaItem(mediaItem)
//
//
//    val playerView = StyledPlayerView(context)
//    playerView.player = exoPlayer
//
//    DisposableEffect(AndroidView(factory = {playerView})){
//
//        exoPlayer.prepare()
//        exoPlayer.playWhenReady= true
//
//        onDispose {
//            exoPlayer.release()
//        }
//    }
//
//
//}

@Composable
fun VideoHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = horizontalPadding,
                vertical = verticalPadding
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Đoạn Phim", color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 21.sp)
        Icon(
            bitmap = ImageBitmap.imageResource(id = R.drawable.ic_outlined_camera),
            tint = Color.White,
            modifier = Modifier.size(24.dp),
            contentDescription = null
        )
    }
}


@Composable
fun VideoList() {
    val video = VideoData.videos

    LazyColumn {
        items(video.size) {index ->
            Box(Modifier.fillParentMaxSize()) {
                VideoPlayer(uri = video[index].getVideoUrl())
                Column(Modifier.align(Alignment.BottomStart)) {
                    VideoFooter(video[index])
                    Divider()
                }
            }
        }
    }
}

@Composable
fun VideoFooter(video: Video) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(start = 18.dp, bottom = 18.dp), verticalAlignment = Alignment.Bottom) {
        FooterUserData(
            video = video,
            modifier = Modifier.weight(8f)
        )
        FooterUserAction(
            video = video,
            modifier = Modifier.weight(2f)
        )
    }
}

@Composable
fun FooterUserAction(video: Video, modifier: Modifier) {
    var isLiked by remember {
        mutableStateOf(video.isLiked)
    }
    var likecount by remember {
        mutableStateOf(video.likesCount)
    }
    var context = LocalContext.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        IconButton(
            onClick = {
                likecount += if(isLiked) -1 else 1
                isLiked = !isLiked
                if(isLiked){
                    Toast.makeText(context,"Bạn đã hủy like",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context,"Bạn đã like",Toast.LENGTH_SHORT).show()
                }
            }
            )
        {
            if (isLiked){
                Icon(
                    bitmap = ImageBitmap.imageResource(id = R.drawable.ic_outlined_favorite),
                    tint = Color(0xFF07899B),
                    modifier = Modifier
                        .size(28.dp),
                    contentDescription = null,
                )
            }else{
                Icon(
                    bitmap = ImageBitmap.imageResource(id = R.drawable.ic_outlined_favorite),
                    tint = Color.White,
                    modifier = Modifier
                        .size(28.dp),
                    contentDescription = null,
                )
            }
        }
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = likecount.toString(),
            color = Color.White,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(28.dp))
        Icon(
            bitmap = ImageBitmap.imageResource(id = R.drawable.ic_outlined_comment),
            tint = Color(0xFF07899B),
            modifier = Modifier
                .size(28.dp),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = video.commentsCount.toString(),
            color = Color.White,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(28.dp))
        Icon(
            bitmap = ImageBitmap.imageResource(id = R.drawable.ic_dm),
            tint = Color(0xFF07899B),
            modifier = Modifier.size(16.dp),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(28.dp))
        Icon(imageVector = Icons.Default.MoreVert, contentDescription = null, tint = Color.White)
        Spacer(modifier = Modifier.height(28.dp))
        GlideImage(
            imageModel = video.userImage,
            modifier = Modifier
                .size(28.dp)
                .background(color = Color.Gray, shape = RoundedCornerShape(6.dp))
                .clip(RoundedCornerShape(6.dp)),
            contentDescription = null
        )
    }
}


@Composable
fun FooterUserData(video: Video, modifier: Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            GlideImage(imageModel = video.userImage,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(28.dp)
                    .background(color = Color.Gray, shape = CircleShape)
                    .clip(CircleShape),
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(horizontalPadding))
            Text(
                text = video.userName,
                color = Color.White,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.width(horizontalPadding))
            Canvas(modifier = Modifier.size(5.dp), onDraw = {
                drawCircle(
                    color = Color.White,
                    radius = 8f
                )
            })
            Spacer(modifier = Modifier.width(horizontalPadding))
            Text(
                text= "Follow",
                color = Color.White,
                style = MaterialTheme.typography.titleMedium
            )
        }

        Spacer(modifier = Modifier.height(horizontalPadding))
        Text(text = video.comment, color = Color.White)
        Spacer(modifier = Modifier.height(horizontalPadding))


        // Audio
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(video.userName, color = Color.White)
            Spacer(modifier = Modifier.width(horizontalPadding))
            Canvas(modifier = Modifier.size(5.dp), onDraw = {
                drawCircle(
                    color = Color.White,
                    radius = 8f
                )
            })
            Spacer(modifier = Modifier.width(horizontalPadding))
            Text(
                text = video.music,
                color = Color.White
            )
        }
    }
}
