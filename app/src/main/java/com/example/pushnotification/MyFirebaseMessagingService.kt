package com.example.pushnotification

import android.app.PendingIntent
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService: FirebaseMessagingService() {


    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        getFirebaseMessage(p0.notification?.title, p0.notification?.body)
    }

    private fun getFirebaseMessage(title: String?, body: String?) {
        //intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK


        val myIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            myIntent,0)

        //builder ki help se message ready kiya hmne
        var builder = NotificationCompat.Builder(this, "FirebaseChannel")
            .setSmallIcon(R.drawable.ic_baseline_notifications_24)
            .setContentTitle(title)
            .setContentText(body)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        //manager ki help se hmne use notify kar diya mtlb print kar diya
        var manager=NotificationManagerCompat.from(this)
        manager.notify(101, builder.build())


    }
}