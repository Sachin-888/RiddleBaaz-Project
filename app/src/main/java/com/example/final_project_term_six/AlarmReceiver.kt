package com.example.final_project_term_six

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.util.Log
import android.widget.Toast

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        val mp = MediaPlayer.create(context, R.raw.ringtone)
        Log.d("Hello", "repeating alarm")
        mp.start()

        Toast.makeText(context, "Alarm triggered!", Toast.LENGTH_SHORT).show()
        // Add any additional logic you want to execute when the alarm is triggered
    }
}