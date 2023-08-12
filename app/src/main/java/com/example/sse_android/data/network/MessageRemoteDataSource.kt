package com.example.sse_android.data.network

import com.example.sse_android.data.models.Message
import kotlinx.coroutines.flow.Flow

class MessageRemoteDataSource(
    private val sseService: SSEService
) {

    fun getMessages(): Flow<List<Message>> {
        return sseService.sseMessagesFlow
    }
}