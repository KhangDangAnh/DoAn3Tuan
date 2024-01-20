package com.example.doan_3tuan.ViewModel

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doan_3tuan.Model.Notification
import com.google.firebase.Firebase
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
        getAllContact()
    }

    @SuppressLint("SuspiciousIndentation")
    fun getAllContact() {
        viewModelScope.launch {
            var ls = mutableListOf<Notification>()
            Firebase.firestore.collection("Notification")
                .addSnapshotListener { value, error ->
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

    private val _deleteSuccess = MutableStateFlow<Boolean>(false)
    val deleteSuccess: StateFlow<Boolean>
        get() = _deleteSuccess

    fun deleteNotification(notification: Notification) {
        Log.d("NotificationViewModel", "Deleting notification with ID: ${notification.id}")
        Firebase.firestore.collection("Notification")
            .whereEqualTo("id", notification.id)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                     val documentId = document . reference . id
                            Log.d(
                                "NotificationViewModelFirebase",
                                "Deleting notification with ID: $documentId"
                            )
                    document.reference.delete()
                    _deleteSuccess.value = true
                }
            }
            .addOnFailureListener { e ->
                _deleteSuccess.value = false
                Log.e("NotificationViewModelFirebase", "Error deleting notification", e)
            }
    }
    fun resetDeleteSuccess() {
        _deleteSuccess.value = false
    }
        var sortByTime by mutableStateOf(true)
        private set

    // Hàm sắp xếp danh sách theo thời gian tạo
    fun sortNotificationsByTime() {
        state = state.copy(
            contactList = state.contactList.sortedByDescending { it.time }
        )
        sortByTime = true
    }
     suspend fun sortNotificationByOldestFromFirestore(): List<Notification> {
        val notifications = Firebase.firestore
            .collection("Notification")
            .get()
            .await()
            .toObjects(Notification::class.java)

        val sortedNotifications = notifications.sortedBy { notification ->
            parseDateTime(notification.time)
        }

        return sortedNotifications
    }

    suspend fun sortNotificationByLastestFromFirestore(): List<Notification> {
        return sortNotificationByOldestFromFirestore().reversed()
    }

    private fun parseDateTime(dateTimeString: String): LocalDateTime {
        val formatter = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        return LocalDateTime.parse(dateTimeString, formatter)
    }

}
data class NotificationScreenState(
    val contactList: List<Notification> = emptyList()
)
