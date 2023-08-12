package com.example.sse_android.data.network

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class SSEService {

    private val sseClient = OkHttpClient.Builder()
        .connectTimeout(6, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.MINUTES)
        .writeTimeout(10, TimeUnit.MINUTES)
        .build()

    companion object {
        private const val GET_MESSAGES_URL = "http://192.168.0.21:3000/messages"
    }
}