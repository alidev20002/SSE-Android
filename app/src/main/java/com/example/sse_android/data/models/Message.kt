package com.example.sse_android.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val text: String
)