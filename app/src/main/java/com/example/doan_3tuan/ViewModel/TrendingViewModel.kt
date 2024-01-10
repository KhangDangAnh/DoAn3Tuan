package com.example.doan_3tuan.ViewModel


import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.doan_3tuan.Model.Trending
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader

class TrendingViewModel(private val context: Context) : ViewModel() {
    private var trending: List<Trending> = emptyList()
    fun getTrending(): List<Trending> {
        if (trending.isEmpty()) {
            val json = readJsonFromFile(context, "trending.json")
            val type = object : TypeToken<List<Trending>>() {}.type
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