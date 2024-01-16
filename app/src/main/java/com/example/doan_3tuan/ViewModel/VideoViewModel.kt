package com.example.doan_3tuan.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.doan_3tuan.Model.Trending
import com.example.doan_3tuan.Model.Video
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader

class VideoViewModel(private val context: Context) : ViewModel() {
    private var video: List<Video> = emptyList()
    fun getVideo(): List<Video> {
        if (video.isEmpty()) {
            val json = readJsonFromFile(context, "video.json")
            val type = object : TypeToken<List<Trending>>() {}.type
            video = Gson().fromJson(json, type)
        }
        return video
    }

    fun readJsonFromFile(context: Context, filename: String): String {
        val inputStream = context.assets.open(filename)
        val bufferedReader = BufferedReader(inputStream.reader())
        return bufferedReader.use { it.readText() }
    }

}