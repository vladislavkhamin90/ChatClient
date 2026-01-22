package com.example.homechatclient.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.homechatclient.UserSession
import com.example.homechatclient.model.ChatMessage
import com.example.homechatclient.network.NetworkMessage
import com.example.homechatclient.network.WebSocketClient
import com.example.homechatclient.ui.ChatUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ChatViewModel : ViewModel() {

    private val _state = MutableStateFlow(ChatUiState())
    val state = _state.asStateFlow()

    private val webSocketClient = WebSocketClient(
        userId = UserSession.userId,
        username = UserSession.username ?: "Unknown",
        onConnected = {
            _state.value = _state.value.copy(isConnected = true)
        },
        onMessageReceived = { message ->
            handleIncomingMessage(message)
        }
    )

    fun connect() {
        webSocketClient.connect()
    }

    fun sendMessage(text: String) {
        if (text.isBlank()) return

        val message = NetworkMessage(
            userId = UserSession.userId,
            username = UserSession.username ?: "Unknown",
            text = text,
            timestamp = System.currentTimeMillis()
        )

        webSocketClient.send(message)
    }

    private fun handleIncomingMessage(message: NetworkMessage) {

        Log.d(
            "MyLog: VM",
            "Incoming message userId=${message.userId}, myUserId=${UserSession.userId}"
        )

        val isMine = message.userId == UserSession.userId

        Log.d("MyLog: VM", "isMine = $isMine")

        addMessage(
            ChatMessage(
                userId = message.userId,
                username = message.username,
                text = message.text,
                timestamp = message.timestamp,
                isMine = isMine
            )
        )
    }

    private fun addMessage(message: ChatMessage) {
        _state.value = _state.value.copy(
            messages = _state.value.messages + message
        )
    }

    fun disconnect() {
        webSocketClient.disconnect()
        _state.value = _state.value.copy(isConnected = false)
    }

    override fun onCleared() {
        disconnect()
    }
}

