package com.example.doan_3tuan.ViewModel

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
import com.example.doan_3tuan.Model.UserData
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore

import com.google.firebase.firestore.firestore

class AccountViewModel : ViewModel() {
    var state by mutableStateOf(AccountState())
        private set
    var isLoggedIn by mutableStateOf(false)
    private var user by mutableStateOf(User())
    var idUser by mutableStateOf("")
    fun addUser(){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(state.email,state.password).addOnCompleteListener{
            state = if (!it.isSuccessful){
                state.copy(success = false)
            } else{
                user = User(
                    state.id,
                    state.email,
                    state.password,
                )
                Firebase.firestore.collection("users")
                    .add(user)
                    .addOnSuccessListener {
                            documentReference->
                        var newDocumentID = documentReference.id
                        val updateData = mapOf(
                            "id" to newDocumentID,
                        )
                        user = User(
                            newDocumentID,
                            state.email,
                            state.password,
                        )
                        Firebase.firestore.collection("users")
                            .document(newDocumentID)
                            .update(updateData)
                            .addOnSuccessListener {
                                println("Cập nhật thành công")
                            }
                            .addOnFailureListener {
                                println("Cập nhật thành thụ")
                            }
                    }
                state.copy(success = true)
            }
        }
    }

    fun signIn(){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(state.email,state.password).addOnCompleteListener {
            state = state.copy(success = it.isSuccessful)
            state.id = user.id
            println("ID tài khoản ${state.id}")
        }
    }
    fun getID() {
        val auth = FirebaseAuth.getInstance().currentUser

        if (auth != null) {
            val accountName = auth.email

            if (accountName != null) {
                val firestore = FirebaseFirestore.getInstance()

                firestore.collection("users")
                    .whereEqualTo("email", accountName)
                    .get()
                    .addOnSuccessListener { querySnapshot ->
                        if (!querySnapshot.isEmpty) {
                            // Lấy tài liệu đầu tiên từ danh sách
                            val documentSnapshot = querySnapshot.documents[0]
                            val user = documentSnapshot.toObject(User::class.java)
                            if (user?.id != null) {
                                idUser = user.id!!
                            }
                            println("ID: ${user?.id}, Email: ${user?.email}, Password: ${user?.password}")
                        } else {
                            println("Không có tài liệu với email $accountName trong bộ sưu tập.")
                        }
                    }
                    .addOnFailureListener { e ->
                        println("Lỗi: $e")
                    }
            } else {
                println("Email xác thực là null.")
            }
        } else {
            println("Người dùng chưa được xác thực.")
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

    fun checkLoginStatus() {
        val auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        isLoggedIn = currentUser != null

    }
}
@Composable
fun DialogErrorLogin(
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
@Composable
fun DialogRequireLogin(
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
        dismissButton = {
            TextButton(onClick = onDiss) {
                Text(text = "Hủy")
            }
        },
        title = { Text(text = title) },
        text = { Text(text = content) }
    )
}
