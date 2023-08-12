package com.example.sse_android.data.repository

import com.example.sse_android.data.models.Message
import com.example.sse_android.data.network.MessageRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MessageRepository @Inject constructor(
    private val messageRemoteDataSource: MessageRemoteDataSource
) {

    fun getMessages(): Flow<List<Message>> {
        return messageRemoteDataSource.getMessages()
    }
}