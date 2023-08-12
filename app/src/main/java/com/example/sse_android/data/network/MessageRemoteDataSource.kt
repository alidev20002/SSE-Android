package com.example.sse_android.data.network

import com.example.sse_android.data.models.Message
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MessageRemoteDataSource @Inject constructor(
    private val sseService: SSEService
) {

    fun getMessages(): Flow<List<Message>> {
        return sseService.sseMessagesFlow
    }

    fun sendMessage(text: String) {
        sseService.sendMessage(text)
    }
}