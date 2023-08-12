package com.example.sse_android.ui.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.sse_android.data.models.Message
import com.example.sse_android.ui.theme.SSEAndroidTheme

@Composable
fun ChatScreen(
    messages: List<Message>
) {
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
        ChatScreen(emptyList())
    }
}