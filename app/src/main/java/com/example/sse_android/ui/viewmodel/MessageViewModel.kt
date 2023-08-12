package com.example.sse_android.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.sse_android.data.repository.MessageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MessageViewModel @Inject constructor(
    private val messageRepository: MessageRepository
): ViewModel() {

    val messages = messageRepository.getMessages()

    fun sendMessage(text: String) {
        messageRepository.sendMessage(text)
    }
}