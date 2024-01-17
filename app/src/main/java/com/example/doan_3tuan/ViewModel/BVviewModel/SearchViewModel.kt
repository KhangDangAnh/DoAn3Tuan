package com.example.doan_3tuan.ViewModel.BVviewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doan_3tuan.Model.LoadRss.Baiviet
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import okhttp3.internal.filterList

class SearchViewModel : ViewModel() {
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearch = _isSearching.asStateFlow()

    private val _LstBaiViet = MutableStateFlow(listOf<Baiviet>())
    val baiviet = searchText.combine(_LstBaiViet) { text, baiviet ->
        if (text.isBlank()) {
            baiviet
        } else {
            delay(2000L)
            baiviet.filter {
                it.SearchQuery(text)
            }
        }.onEach {
            _isSearching.update { false }
        }
    }.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5600),
        _LstBaiViet.value
    )

    fun onSearchText(text: String) {
        _searchText.value = text
    }
}