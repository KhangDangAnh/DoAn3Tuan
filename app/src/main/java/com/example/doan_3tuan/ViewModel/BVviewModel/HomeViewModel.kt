package com.example.doan_3tuan.ViewModel.BVviewModel
import com.example.doan_3tuan.Model.LoadRss.CloudService
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doan_3tuan.Model.LoadRss.Rss
import com.example.doan_3tuan.Model.UiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
//import com.example.doan_3tuan.Model.LoadRss.ktorClient

class HomeViewModel : ViewModel() {
    private val cloudService = CloudService()
    private val uiMapper = GetRssValue()

    private val _uiState = MutableStateFlow<UiResult<Rss>>(UiResult.Loading)

    val uiState: StateFlow<UiResult<Rss>> = _uiState.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        UiResult.Loading
    )
    init {
        loadBaiviet()
    }
    private fun loadBaiviet() {
        viewModelScope.launch {
            try {
                val home = withContext(Dispatchers.IO) {
                    val response = cloudService.getBaiviet()
                    uiMapper.map(response)
                }
                _uiState.update {
                    UiResult.Success(home)
                }
            } catch (err: Throwable) {
                _uiState.update {UiResult.Fail(err) }
            }
        }
    }
}