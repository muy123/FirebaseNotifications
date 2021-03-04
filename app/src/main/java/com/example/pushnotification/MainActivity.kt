package com.example.pushnotification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        // oreo se aage waale versions ke liye hme channel create krna pdta h
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val mChannel = NotificationChannel("FirebaseChannel", "FirebaseChannel",NotificationManager.IMPORTANCE_DEFAULT)
            // Register the channel with the system; you know we execute this code for specific android version oreo+ so in that case we will not use
            //notificationManagerCompat class
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }



        //targeting some specific userbase according to their interest(subscribed topic)
        FirebaseMessaging.getInstance().subscribeToTopic("weather")
            .addOnCompleteListener { task ->
                var msg = "Successful"
                if (!task.isSuccessful) {
                    msg = "Failed"
                }
                Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
            }
    }
}