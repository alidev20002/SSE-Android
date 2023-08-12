package com.example.sse_android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.sse_android.data.models.Message
import com.example.sse_android.data.network.MessageRemoteDataSource
import com.example.sse_android.data.network.SSEService
import com.example.sse_android.data.repository.MessageRepository
import com.example.sse_android.ui.screen.ChatScreen
import com.example.sse_android.ui.theme.SSEAndroidTheme
import com.example.sse_android.ui.viewmodel.MessageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        lifecycleScope.launch {
//            while (true) {
//                sseService.sendMessage()
//                delay(3000)
//                Log.i("alitest", "onCreate: ${sseService.sseMessagesFlow.value}")
//            }
//        }

        setContent {
            SSEAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ChatScreen(listOf(
                        Message("hi"),
                        Message("Hello"),
                        Message("Good Morning")
                    ))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SSEAndroidTheme {
        ChatScreen(emptyList())
    }
}