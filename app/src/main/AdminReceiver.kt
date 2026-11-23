package com.example.wrongpass

import android.app.admin.DeviceAdminReceiver
import android.content.Context
import android.content.Intent

// Сюда Android присылает сигнал, когда на экране блокировки ввели неверный пароль
class AdminReceiver : DeviceAdminReceiver() {

    override fun onPasswordFailed(context: Context, intent: Intent) {
        super.onPasswordFailed(context, intent)
        val i = Intent(context, VideoActivity::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(i)
    }
}
