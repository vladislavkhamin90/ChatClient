package com.example.homechatclient

import java.util.UUID

object UserSession {
    val userId: String = UUID.randomUUID().toString()
    var username: String? = null
}
