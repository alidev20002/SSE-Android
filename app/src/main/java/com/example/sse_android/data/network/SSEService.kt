package com.example.sse_android.data.network

import android.util.Log
import com.example.sse_android.data.models.Message
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okhttp3.sse.EventSource
import okhttp3.sse.EventSourceListener
import okhttp3.sse.EventSources
import java.io.IOException
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
    var messages = emptyList<Message>()

    private var sseEventSourceListener = object : EventSourceListener() {

        override fun onEvent(eventSource: EventSource, id: String?, type: String?, data: String) {
            super.onEvent(eventSource, id, type, data)
            Log.i("alitest", "onEvent: $data")
            if (data.isNotEmpty()) {
                if (data.startsWith("[") and data.endsWith("]")) {
                    val msgData: List<Message> = Json.decodeFromString(data)
                    val newMessages = messages.toMutableList()
                    newMessages.addAll(msgData)
                    messages = newMessages
                    sseMessagesFlow.tryEmit(messages)
                } else if (data.startsWith("{") and data.endsWith("}")) {
                    val msgData: Message = Json.decodeFromString(data)
                    val newMessages = messages.toMutableList()
                    newMessages.add(msgData)
                    messages = newMessages
                    sseMessagesFlow.tryEmit(messages)
                }
            }
        }

        override fun onFailure(eventSource: EventSource, t: Throwable?, response: Response?) {
            super.onFailure(eventSource, t, response)
            Log.i("alitest", "onFailure: $t")
        }
    }

    init {
        initEventSource()
    }

    private fun initEventSource() {
        EventSources.createFactory(sseClient)
            .newEventSource(request = sseRequest, listener = sseEventSourceListener)
    }

    fun sendMessage(text: String) {
        val message = Json.encodeToString(Message(text))
        val postMessageRequest = Request.Builder()
            .method("POST", message.toRequestBody("application/json; charset=utf-8".toMediaType()))
            .url(POST_MESSAGE_URL)
            .build()
        sseClient.newCall(postMessageRequest).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Handle this
            }

            override fun onResponse(call: Call, response: Response) {
                // Handle this
            }
        })
    }

    companion object {
        private const val GET_MESSAGES_URL = "http://192.168.0.21:3000/messages"
        private const val POST_MESSAGE_URL = "http://192.168.0.21:3000/messages"
    }
}