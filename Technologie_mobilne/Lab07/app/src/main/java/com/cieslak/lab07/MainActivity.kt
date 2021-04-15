package com.cieslak.lab07

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.RemoteViews
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    val CHANNEL_ID: String = "com.cielak.lab07"
    lateinit var showToastButton: Button
    lateinit var showDogeButton: Button
    lateinit var showDialogButton: Button
    lateinit var showSingleNotification: Button
    lateinit var showMultipleNotifications: Button
    lateinit var showCustomNotification :Button
    lateinit var toast: Toast
    lateinit var snackbar: Snackbar
    lateinit var dialog: ExampleDialog
    lateinit var mContentView: RemoteViews
    lateinit var nm: NotificationManager
    lateinit var soundURI :Uri
    lateinit var vibrationPattern :LongArray
    var mNotificationIntent: Intent? = null
    var mContentIntent: PendingIntent? = null
    var counter :Int = 1

    val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        isGranted: Boolean -> handlePermRequestResult(isGranted)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showToastButton = findViewById(R.id.showToastButton)
        showDogeButton = findViewById(R.id.showDogeButton)
        showDialogButton = findViewById(R.id.showDialogButton)
        showSingleNotification = findViewById(R.id.showSingleNotification)
        showMultipleNotifications = findViewById(R.id.showMultipleNotifications)
        showCustomNotification = findViewById(R.id.showCustomNotification)
        mContentView = RemoteViews(this.packageName, R.layout.custom_notification)

        toast = Toast (applicationContext)
        toast.duration = Toast.LENGTH_LONG
        toast.setGravity(Gravity.AXIS_PULL_BEFORE, 10, -10)

        snackbar = Snackbar.make(findViewById(R.id.ConstraintLayoutMain), "Czy jesteś pewien?", Snackbar.LENGTH_LONG)
        snackbar.setActionTextColor(Color.MAGENTA)

        dialog = ExampleDialog(toast, snackbar)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Lab07"
            val descriptionText = "Kanał na potrzeby Lab07"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        soundURI =  Uri.EMPTY
        soundURI =  Uri.parse("android.resource://" + this.packageName + "/" + R.raw.woof);
        vibrationPattern = longArrayOf(0, 200, 200, 300)

        mNotificationIntent = Intent(applicationContext, MainActivity::class.java)

        mContentIntent = PendingIntent.getActivity(applicationContext, 0, mNotificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)


        showToastButton.setOnClickListener {
            showToast()
        }
        showDogeButton.setOnClickListener {
            snackbar.show()
        }
        showDialogButton.setOnClickListener {
            dialog.show(supportFragmentManager, "dialog")
        }
        showSingleNotification.setOnClickListener {
            var singleNotification = NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(android.R.drawable.ic_notification_overlay)
                    .setContentTitle("Pojedyńcze powiadomienie")
                    .setContentText("Tekst pojedyńczego powiadomienia")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            nm.notify(0, singleNotification.build())
        }
        showMultipleNotifications.setOnClickListener {
            var multipleNotification = NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(android.R.drawable.ic_notification_overlay)
                    .setContentTitle("Wielokrotne powiadomienie nr."+counter)
                    .setContentText("Tekst wielokrotnego powiadomienia")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            nm.notify(counter++, multipleNotification.build())
        }
        showCustomNotification.setOnClickListener {

            var res = this.checkSelfPermission(Manifest.permission.VIBRATE)
            if(res == PackageManager.PERMISSION_GRANTED) {
                customNotification()
            } else {
                requestPermissionLauncher.launch(Manifest.permission.VIBRATE)
            }
        }

        snackbar.setAction("Tak") {
            showToast()
        }
    }

    fun showToast() {
        var view = layoutInflater.inflate(R.layout.toast_view, null)
        view.findViewById<TextView>(R.id.ToastTextView).text = "Woof! So customized!"
        toast.view = view
        toast.show()
    }

    fun handlePermRequestResult(isGranted :Boolean) {
        if (isGranted) {
            customNotification()
        }
    }

    fun customNotification() {
        val notificationBuilder: NotificationCompat.Builder = NotificationCompat.Builder(this, CHANNEL_ID)
        notificationBuilder.setSmallIcon(android.R.drawable.stat_sys_warning)
        notificationBuilder.setAutoCancel(true)
        notificationBuilder.setContentIntent(mContentIntent)
        notificationBuilder.setSound(soundURI)
        notificationBuilder.setVibrate(vibrationPattern)
        notificationBuilder.setContent(mContentView)
        nm.notify(counter++, notificationBuilder.build())
    }

}