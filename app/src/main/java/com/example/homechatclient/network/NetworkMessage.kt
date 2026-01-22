package com.example.homechatclient.network

data class NetworkMessage(
    val userId: String,
    val username: String,
    val text: String,
    val timestamp: Long
)

