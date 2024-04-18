package com.okumu.notificationdemo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {
    private val channelID = "com.okumu.notificationdemo.channel1"
    private var notificationManager: NotificationManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the notification manager
        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager?

        // Create the notification channel
        createNotificationChannel(channelID, "DemoChannel", "this is a Demo")

        // Find the button and set a click listener
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener{
            // Call the function to display the notification
            displayNotification()
        }
    }

    private fun displayNotification(){
        val notificationId = 45
        val notification = NotificationCompat.Builder(this@MainActivity, channelID)
            .setContentTitle("Demo Title")
            .setContentText("This is a demo notification")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        // Notify the NotificationManager to show the notification
        notificationManager?.notify(notificationId, notification)
    }

    private fun createNotificationChannel(id : String, name : String, channelDescription: String){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(id, name, importance).apply {
                description = channelDescription
            }

            // Create the notification channel
            notificationManager?.createNotificationChannel(channel)
        }
    }
}
