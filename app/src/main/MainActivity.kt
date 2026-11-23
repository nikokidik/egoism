package com.example.wrongpass

import android.app.Activity
import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

// Главный экран: три кнопки
class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(48, 96, 48, 48)

        val text = TextView(this)
        text.textSize = 18f
        text.text = "1. Дай права администратора.\n2. Разреши показ поверх других приложений.\n3. Заблокируй телефон и введи неверный пароль."
        layout.addView(text)

        val b1 = Button(this)
        b1.text = "1. Права администратора"
        b1.setOnClickListener {
            val admin = ComponentName(this, AdminReceiver::class.java)
            val i = Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN)
            i.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, admin)
            startActivity(i)
        }
        layout.addView(b1)

        val b2 = Button(this)
        b2.text = "2. Поверх других приложений"
        b2.setOnClickListener {
            val i = Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:$packageName")
            )
            startActivity(i)
        }
        layout.addView(b2)

        val b3 = Button(this)
        b3.text = "Проверка: показать видео"
        b3.setOnClickListener {
            startActivity(Intent(this, VideoActivity::class.java))
        }
        layout.addView(b3)

        setContentView(layout)
    }
}
