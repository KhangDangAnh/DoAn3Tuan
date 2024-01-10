package com.example.doan_3tuan


import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.text.SimpleDateFormat

class TrendingViewModel(private val context: Context) : ViewModel() {
    private var trending: List<Trending> = emptyList()
    fun getTrending(): List<Trending> {
        if (trending.isEmpty()) {
            val json = readJsonFromFile(context, "notifications.json")
            val type = object : TypeToken<List<Notification>>() {}.type
            trending = Gson().fromJson(json, type)
        }
        return trending
    }

    fun readJsonFromFile(context: Context, filename: String): String {
        val inputStream = context.assets.open(filename)
        val bufferedReader = BufferedReader(inputStream.reader())
        return bufferedReader.use { it.readText() }
    }

}