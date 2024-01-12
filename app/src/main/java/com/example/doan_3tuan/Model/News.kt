package com.example.doan_3tuan.Model

data class HomeUiState(
    val title: String,
    val link: String,
    val description: String,
    val baiviet: List<Baiviet>
)

data class Baiviet(
    val title: String,
    val description: String,
    val link: String,
    val imageUrl: String
)