package com.example.doan_3tuan.ViewModel

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doan_3tuan.Model.Notification
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.text.SimpleDateFormat

//class NotificationViewModel(private val context: Context) : ViewModel() {
//    private var notifications: List<Notification> = emptyList()
//    fun getNotifications(): List<Notification> {
//        if (notifications.isEmpty()) {
//            val json = readJsonFromFile(context, "notifications.json")
//            val type = object : TypeToken<List<Notification>>() {}.type
//            notifications = Gson().fromJson(json, type)
//        }
//        return notifications
//    }
//
//    fun readJsonFromFile(context: Context, filename: String): String {
//        val inputStream = context.assets.open(filename)
//        val bufferedReader = BufferedReader(inputStream.reader())
//        return bufferedReader.use { it.readText() }
//    }
//    fun sortNotificationByOldest(): List<Notification> {
//
//        var sortNotification: List<Notification> = emptyList()
//        val dateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm")
//        if (getNotifications().isNotEmpty()) {
//            sortNotification =
//                getNotifications().sortedBy { notification ->
//                    dateFormat.parse(notification.time) }
//
//        }
//        return sortNotification
//    }
//    fun sortNotificationByLastest(): List<Notification> {
//        return sortNotificationByOldest().reversed()
//    }
//}

class NotificationViewModelFirebase:ViewModel(){
var state by mutableStateOf(NotificationScreenState())
    private set
    init {
        getAllContact()
    }

    @SuppressLint("SuspiciousIndentation")
    fun getAllContact(){
        viewModelScope.launch {
            var ls = mutableListOf<Notification>()
                Firebase.firestore.collection("Notification")
                    .addSnapshotListener { value, error ->
                        if(value != null){
                            for(doc in value){
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
    fun deleteNotification(notification: Notification){Log.d("NotificationViewModel", "Deleting notification with ID: ${notification.id}")

        Firebase.firestore.collection("Notification")
             .whereEqualTo("id",notification.id)
            .get()
             .addOnSuccessListener {documents->
                 for (document in documents){

                     val documentId = document.reference.id
                     Log.d("NotificationViewModelFirebase", "Deleting notification with ID: $documentId")
                     document.reference.delete()
                     _deleteSuccess.value = true
                 }
             }
             .addOnFailureListener {e->
                 _deleteSuccess.value= false
                 Log.e("NotificationViewModelFirebase","Error deleting notification",e)
             }
    }

    fun resetDeleteSuccess() {
        _deleteSuccess.value = false
    }

}
data class NotificationScreenState(
    val contactList: List<Notification> = emptyList()
)