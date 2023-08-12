package com.example.sse_android.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                .weight(1F),
            reverseLayout = true
        ) {
            items(messages.asReversed()) { message ->
                Text(text = message.text)
            }
        }

        Row(
            modifier = Modifier
                .height(56.dp)
        ) {
            TextField(
                value = textFieldValue,
                onValueChange = { textFieldValue = it },
                modifier = Modifier.weight(0.7F)
            )

            Button(
                onClick = {
                    messagesViewModel.sendMessage(textFieldValue)
                    textFieldValue = ""
                },
                modifier = Modifier.weight(0.3F)
            ) {
                Text(text = "Send", fontSize = 12.sp, maxLines = 1)
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