package com.example.sse_android.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sse_android.data.network.MessageRemoteDataSource
import com.example.sse_android.data.network.SSEService
import com.example.sse_android.data.repository.MessageRepository
import com.example.sse_android.ui.theme.SSEAndroidTheme
import com.example.sse_android.ui.viewmodel.MessageViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    messagesViewModel: MessageViewModel
) {

    val messages = messagesViewModel.messages.collectAsState(initial = emptyList()).value
    var textFieldValue by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(0.8F)
        ) {
            items(messages) { message ->
                Text(text = message.text)
            }
        }

        Row(
            modifier = Modifier
                .weight(0.2F)
        ) {
            TextField(
                value = textFieldValue,
                onValueChange = { textFieldValue = it }
            )

            Button(onClick = { /*TODO*/ }) {
                Text(text = "Send Message")
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun ChatScreenPreview() {
    SSEAndroidTheme {
        ChatScreen(
            messagesViewModel = MessageViewModel(
                MessageRepository(
                    MessageRemoteDataSource(
                        SSEService()
                    )
                )
            )
        )
    }
}