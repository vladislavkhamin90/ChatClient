package com.example.homechatclient.model

data class ChatMessage(
    val userId: String,
    val username: String,
    val text: String,
    val timestamp: Long,
    val isMine: Boolean
)
