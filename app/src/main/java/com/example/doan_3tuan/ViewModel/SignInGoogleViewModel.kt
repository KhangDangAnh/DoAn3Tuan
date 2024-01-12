package com.example.doan_3tuan.ViewModel

import androidx.lifecycle.ViewModel
import com.example.doan_3tuan.Model.SignInResult
import com.example.doan_3tuan.Model.SignInState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignInGoogleViewModel :ViewModel(){
    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun onSignInResult(result: SignInResult){
        _state.update {
            it.copy(
                isSignInSuccessful = result.data != null,
                signInError = result.errorMessage
            )
        }
    }

    fun resetData(){
        _state.update { SignInState() }
    }
}