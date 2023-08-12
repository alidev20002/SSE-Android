package com.example.sse_android.ui.viewmodel

import com.example.sse_android.data.repository.MessageRepository

class MessageViewModel(
    private val messageRepository: MessageRepository
) {
    val messages = messageRepository.getMessages()
}