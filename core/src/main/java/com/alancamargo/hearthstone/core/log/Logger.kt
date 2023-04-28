package com.alancamargo.hearthstone.core.log

interface Logger {

    fun debug(message: String)

    fun error(exception: Throwable)
}
