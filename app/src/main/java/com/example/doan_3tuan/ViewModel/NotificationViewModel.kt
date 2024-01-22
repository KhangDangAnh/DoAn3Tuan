package com.example.doan_3tuan.ViewModel

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doan_3tuan.Model.Notification
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class NotificationViewModelFirebase:ViewModel() {
    var state by mutableStateOf(NotificationScreenState())
        private set

    init {
        getAllNotification()
    }

    private fun getAllNotification() {
        viewModelScope.launch {
            var ls = mutableListOf<Notification>()
            Firebase.firestore.collection("Notification")
                .addSnapshotListener { value, _ ->
                    if (value != null) {
                        for (doc in value) {
                            var noti = doc.toObject(Notification::class.java)
                            ls.add(noti)
                        }
                    }
                    state = state.copy(
                        contactList = ls
                    )
                }
        }
    }
     fun deleteNotification(notificationId: String, context: Context) {
        val db = FirebaseFirestore.getInstance()
        db.collection("Notification")
            .document(notificationId)
            .delete()
            .addOnSuccessListener {
                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(context, "Không thể xóa: $e", Toast.LENGTH_SHORT).show()
            }
    }
    fun refreshNotifications() {
        getAllNotification()
    }

    fun sortNotifications(byOldest: Boolean) {
        Log.d("SortTest", "Before Sort: $state.contactList")
        state.contactList = if (byOldest) {
            state.contactList.sortedBy { it.time }
        } else {
            state.contactList.sortedByDescending { it.time }
        }
        Log.d("SortTest", "After Sort: $state.contactList")
    }

}
data class NotificationScreenState(
    var contactList: List<Notification> = emptyList()
)
