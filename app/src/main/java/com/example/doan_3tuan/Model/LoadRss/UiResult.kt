package com.example.doan_3tuan.Model.LoadRss

sealed interface UiResult<out T> {
    object Loading : UiResult<Nothing>
    data class Success<T>(val data: T) : UiResult<T>
    data class Fail(val error: Throwable) : UiResult<Nothing>
}