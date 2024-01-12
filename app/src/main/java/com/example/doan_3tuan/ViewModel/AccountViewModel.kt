package com.example.doan_3tuan.ViewModel

import android.service.credentials.BeginCreateCredentialRequest
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.doan_3tuan.Model.AccountState

import com.example.doan_3tuan.Model.User
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class AccountViewModel : ViewModel() {
    var state by mutableStateOf(AccountState())
    private set

    fun addUser(){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(state.email,state.password).addOnCompleteListener{
            if (!it.isSuccessful){
                state = state.copy(success = false)
            }
            else{
                val user = User(
                    state.email,
                    state.password,
                )
                Firebase.firestore.collection("users").add(user)
                state = state.copy(success = true)
            }
        }
    }

    fun SignIn(){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(state.email,state.password).addOnCompleteListener {
            state = state.copy(success = it.isSuccessful)
        }
    }
    fun onChangeEmail(newEmail:String){
        state = state.copy(email = newEmail)
    }
    fun onChangePassword(newPassword:String){
        state = state.copy(password = newPassword)
    }
    fun onChangeRePassword(newRePassword:String){
        state = state.copy(rePassword = newRePassword)
    }
}
@Composable
fun DialogSample(
    title: String = "",
    content: String = "",
    onDiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDiss,
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(text = "Xác Nhận")
            }
        },
        title = { Text(text = title) },
        text = { Text(text = content) }
    )
}