package com.example.sse_android.data.repository

import com.example.sse_android.data.models.Message
import com.example.sse_android.data.network.MessageRemoteDataSource
import kotlinx.coroutines.flow.Flow

class MessageRepository(
    private val messageRemoteDataSource: MessageRemoteDataSource
) {

    fun getMessages(): Flow<List<Message>> {
        return messageRemoteDataSource.getMessages()
    }
}