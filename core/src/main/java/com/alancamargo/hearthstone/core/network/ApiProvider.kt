package com.alancamargo.hearthstone.core.network

interface ApiProvider {

    fun <T> provideService(clazz: Class<T>): T
}
