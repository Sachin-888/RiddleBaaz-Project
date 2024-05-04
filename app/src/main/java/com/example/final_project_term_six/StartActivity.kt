package com.example.final_project_term_six

import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContentProviderCompat.requireContext
import java.util.logging.Handler

class StartActivity : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        val window: Window = getWindow()
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.parseColor("#2962FF")
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        mediaPlayer = MediaPlayer.create(this, R.raw.background_music1)

        // Set looping to true

        mediaPlayer.isLooping = true

        android.os.Handler().postDelayed({
            finish()
            startActivity(Intent(this@StartActivity, MainActivity::class.java))
        }, 3000)


    }
    override fun onStart() {
        super.onStart()

        // Start playback
        mediaPlayer.setVolume(0.4f, 0.4f)
        mediaPlayer.start()
    }
    override fun onDestroy() {
        super.onDestroy()


        // Start playback
        mediaPlayer.setVolume(1.0f, 1.0f)
    }

}