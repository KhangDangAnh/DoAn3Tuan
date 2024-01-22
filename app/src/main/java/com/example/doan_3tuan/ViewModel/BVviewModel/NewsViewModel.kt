package com.example.doan_3tuan.ViewModel.BVviewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.doan_3tuan.Model.LoadRss.Baiviet
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import java.io.BufferedReader

class NewsViewModel (private val context : Context):ViewModel(){
    private var news : List<Baiviet> = emptyList()
    fun getNews():List<Baiviet>{
        if(news.isEmpty())
        {
            val json = readJsonFromFile(context,"news.json")
            val type = object :TypeToken<List<Baiviet>>(){}.type
            news = Gson().fromJson(json,type)
        }
        return news
    }

    private fun readJsonFromFile(context: Context, filename : String): String {
        val inputStream = context.assets.open(filename)
        val buff = BufferedReader(inputStream.reader())
        return buff.use { it.readText() }
    }
}