package com.example.wrongpass

import android.app.Activity
import android.app.KeyguardManager
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.VideoView

// Окно с видео. Показывается поверх экрана блокировки
class VideoActivity : Activity() {

    private lateinit var videoView: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setShowWhenLocked(true)
        setTurnScreenOn(true)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        // Полный экран
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

        videoView = VideoView(this)
        val params = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT,
            Gravity.CENTER
        )
        val root = FrameLayout(this)
        root.setBackgroundColor(0xFF000000.toInt())
        root.addView(videoView, params)
        setContentView(root)

        videoView.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.video))
        // Видео играет по кругу
        videoView.setOnPreparedListener { it.isLooping = true }
        videoView.start()

        // Тап по экрану закрывает видео
        root.setOnClickListener { finish() }
    }

    override fun onDestroy() {
        videoView.stopPlayback()
        super.onDestroy()
    }
}
