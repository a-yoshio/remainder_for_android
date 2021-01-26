package com.example.remainder_for_android.service.notification

import android.app.AlertDialog
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class RemainderMessagingService: FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d("DEBUG", ">>> From: ${remoteMessage.from}")
        // Check if message contains a data payload.
        if (remoteMessage.data.isNotEmpty()) {
            Log.d("DEBUG", ">>> Message data payload: ${remoteMessage.data}")
        }

        // Check if message contains a notification payload.
        remoteMessage.notification?.let {
            Log.d("DEBUG", ">>> Message Notification Body: ${it.body}")
        }
    }

    override fun onNewToken(token: String) {
        Log.d("DEBUG", ">>> Refreshed token: $token")
    }

}