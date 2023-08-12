package com.example.sse_android.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.sse_android.data.repository.MessageRepository

class MessageViewModel(
    private val messageRepository: MessageRepository
): ViewModel() {
    val messages = messageRepository.getMessages()
}