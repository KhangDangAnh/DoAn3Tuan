package com.example.doan_3tuan.Model.LoadRss

import androidx.compose.runtime.Immutable

data class HomeUiState(
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
)