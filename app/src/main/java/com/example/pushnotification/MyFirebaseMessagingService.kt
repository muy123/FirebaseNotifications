package com.example.pushnotification

import android.app.NotificationChannel
import android.app.NotificationManager
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService: FirebaseMessagingService() {


    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        getFirebaseMessage(p0.notification?.title,p0.notification?.body)
    }

    private fun getFirebaseMessage(title: String?, body: String?) {
        //builder ki help se message ready kiya hmne
        var builder = NotificationCompat.Builder(this, "FirebaseChannel")
            .setSmallIcon(R.drawable.ic_baseline_notifications_24)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)

        //manager ki help se hmne use notify kar diya mtlb print kar diya
        var manager=NotificationManagerCompat.from(this)
        manager.notify(101,builder.build())


    }
}