package com.example.doan_3tuan.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.doan_3tuan.Model.Notification
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.text.SimpleDateFormat

class NotificationViewModel(private val context: Context) : ViewModel() {
    private var notifications: List<Notification> = emptyList()
    fun getNotifications(): List<Notification> {
        if (notifications.isEmpty()) {
            val json = readJsonFromFile(context, "notifications.json")
            val type = object : TypeToken<List<Notification>>() {}.type
            notifications = Gson().fromJson(json, type)
        }
        return notifications
    }

    fun readJsonFromFile(context: Context, filename: String): String {
        val inputStream = context.assets.open(filename)
        val bufferedReader = BufferedReader(inputStream.reader())
        return bufferedReader.use { it.readText() }
    }
    fun sortNotificationByOldest(): List<Notification> {
        var sortNotification: List<Notification> = emptyList()
        val dateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm")
        if (getNotifications().isNotEmpty()) {
            sortNotification =
                getNotifications().sortedBy { notification ->
                    dateFormat.parse(notification.time) }

        }
        return sortNotification
    }
    fun sortNotificationByLastest(): List<Notification> {
        return sortNotificationByOldest().reversed()
    }
}