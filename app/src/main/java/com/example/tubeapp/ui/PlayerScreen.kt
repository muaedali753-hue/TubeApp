package com.example.tubeapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.tubeapp.data.SampleData
import com.example.tubeapp.data.Video

@Composable
fun PlayerScreen(videoId: Int, onBack: () -> Unit, onVideoClick: (Int) -> Unit) {
    val video = SampleData.byId(videoId) ?: return
    val context = LocalContext.current

    val player = remember(videoId) {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(video.url))
            prepare()
            playWhenReady = true
        }
    }
    DisposableEffect(player) { onDispose { player.release() } }

    var liked by rememberSaveable(videoId) { mutableStateOf(false) }
    val related = remember(videoId) { SampleData.videos.filter { it.id != videoId } }

    Column(Modifier.fillMaxSize().statusBarsPadding()) {
        Box {
            AndroidView(
                factory = { ctx -> PlayerView(ctx).apply { this.player = player } },
                modifier = Modifier.fillMaxWidth().aspectRatio(16f / 9f).background(Color.Black)
            )
            IconButton(onClick = onBack, modifier = Modifier.align(Alignment.TopStart)) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, "رجوع", tint = Color.White)
            }
        }

        LazyColumn(Modifier.fillMaxSize()) {
            item { Details(video, liked) { liked = !liked } }
            item {
                Text(
                    "التعليقات (${video.comments.size})",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
            items(video.comments) { c ->
                Column(Modifier.padding(horizontal = 16.dp, vertical = 6.dp)) {
                    Text(c.author, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    Text(c.text)
                }
            }
            item {
                Text("فيديوهات مقترحة", fontWeight = FontWeight.Bold, modifier = Modifier.padding(16.dp))
            }
            items(related, key = { it.id }) { v ->
                VideoCard(v) { onVideoClick(v.id) }
                Spacer(Modifier.height(12.dp))
            }
        }
    }
}

@Composable
private fun Details(video: Video, liked: Boolean, onLike: () -> Unit) {
    Column(Modifier.padding(16.dp)) {
        Text(video.title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Text(
            "${video.views} • ${video.age}",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(Modifier.height(8.dp))
        Text(video.channel, fontWeight = FontWeight.Medium)
        Spacer(Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            FilledTonalButton(onClick = onLike) {
                Icon(
                    if (liked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    null,
                    tint = if (liked) Color.Red else LocalContentColor.current
                )
                Spacer(Modifier.width(6.dp))
                Text((video.likes + if (liked) 1 else 0).toString())
            }
            Spacer(Modifier.width(8.dp))
            FilledTonalButton(onClick = { }) {
                Icon(Icons.Default.Share, null)
                Spacer(Modifier.width(6.dp))
                Text("مشاركة")
            }
        }
        Spacer(Modifier.height(8.dp))
        Text(video.description)
    }
}
