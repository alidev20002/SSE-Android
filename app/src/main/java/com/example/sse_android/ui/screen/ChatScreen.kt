package com.example.sse_android.ui.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.tooling.preview.Preview
import com.example.sse_android.data.models.Message
import com.example.sse_android.ui.theme.SSEAndroidTheme
import com.example.sse_android.ui.viewmodel.MessageViewModel
import kotlinx.coroutines.flow.collect

@Composable
fun ChatScreen(
    messagesViewModel: MessageViewModel
) {

    val messages = messagesViewModel.messages.collectAsState(initial = emptyList()).value
    LazyColumn() {
        items(messages) {message ->
            Text(text = message.text)
        }
    }
}

@Preview
@Composable
private fun ChatScreenPreview() {
    SSEAndroidTheme {

    }
}