package com.alancamargo.hearthstone.core.extensions

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

inline fun <reified A : AppCompatActivity> Context.createIntent(): Intent {
    return Intent(this, A::class.java)
}
