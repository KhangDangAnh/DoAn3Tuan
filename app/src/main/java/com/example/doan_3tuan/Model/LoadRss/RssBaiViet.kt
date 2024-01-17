package com.example.doan_3tuan.Model.LoadRss

import androidx.compose.runtime.Immutable

data class Rss(
    val title: String,
    val link: String,
    val description: String,
    val baiviet: List<Baiviet>
)

@Immutable
data class Baiviet(
    val title: String,
    val description: String,
    val link: String,
    val imageUrl: String
){
    fun SearchQuery(query : String):Boolean{
        val lst = listOf(
            "$title","${title.first()}"
        )
        return lst.any{
            it.contains(query,ignoreCase = true)
        }
    }
}