package com.example.moviereview2

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

class ReviewActivity : AppCompatActivity() {

    private lateinit var movieTitleTextView: TextView
    private lateinit var reviewTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        movieTitleTextView = findViewById(R.id.movieTitleTextView)
        reviewTextView = findViewById(R.id.reviewTextView)

        val movieTitle = intent.getStringExtra("movieTitle") ?: "Unknown Movie"
        movieTitleTextView.text = movieTitle
        reviewTextView.text = "This is a review for $movieTitle."

        showNotification(movieTitle, reviewTextView.text.toString())

        supportFragmentManager.beginTransaction()
            .replace(R.id.reviewFragmentContainer, ReviewFragment())
            .commit()
    }

    private fun showNotification(movieTitle: String, reviewText: String) {
        val channelId = "movie_review_channel"
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Create notification channel for Android O and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Movie Review Notifications",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                enableVibration(true)
                description = "Channel for Movie Review Notifications"
            }
            notificationManager.createNotificationChannel(channel)
        }

        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("New Review for $movieTitle")
            .setContentText(reviewText)
            .setSmallIcon(R.drawable.ic_notification) // Replace with your notification icon
            .setPriority(NotificationCompat.PRIORITY_HIGH) // High priority for top screen pop-up
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent) // Set the pending intent
            .build()

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU || checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            Log.d("NotificationTest", "Notification is being shown")
            notificationManager.notify(1, notification)
        } else {
            Log.d("NotificationTest", "Requesting notification permission")
            requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS), 1001)
        }
    }
}
