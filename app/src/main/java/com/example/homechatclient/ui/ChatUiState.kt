package com.example.homechatclient.ui

import com.example.homechatclient.model.ChatMessage

data class ChatUiState(
    val messages: List<ChatMessage> = emptyList(),
    val isConnected: Boolean = false
)
