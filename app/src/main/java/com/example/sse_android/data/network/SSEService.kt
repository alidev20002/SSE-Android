package com.example.sse_android.data.network

import android.util.Log
import com.example.sse_android.data.models.Message
import kotlinx.coroutines.flow.MutableStateFlow
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.sse.EventSource
import okhttp3.sse.EventSourceListener
import okhttp3.sse.EventSources
import java.util.concurrent.TimeUnit

class SSEService {

    private val sseClient = OkHttpClient.Builder()
        .connectTimeout(6, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.MINUTES)
        .writeTimeout(10, TimeUnit.MINUTES)
        .build()

    private val sseRequest = Request.Builder()
        .url(GET_MESSAGES_URL)
        .header("Accept", "application/json")
        .addHeader("Accept", "text/event-stream")
        .build()

    val sseMessagesFlow = MutableStateFlow<List<Message>>(emptyList())

    private val sseEventSourceListener = object : EventSourceListener() {

        override fun onEvent(eventSource: EventSource, id: String?, type: String?, data: String) {
            super.onEvent(eventSource, id, type, data)
            Log.i("alitest", "onEvent: data -> $data, type -> $type")
        }

    }

    init {
        initEventSource()
    }

    private fun initEventSource() {
        EventSources.createFactory(sseClient)
            .newEventSource(request = sseRequest, listener = sseEventSourceListener)
    }

    companion object {
        private const val GET_MESSAGES_URL = "http://192.168.0.21:3000/messages"
    }
}