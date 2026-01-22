package com.example.homechatclient.ui

import androidx.lifecycle.ViewModel
import com.example.homechatclient.UserSession
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {

    private val _username = MutableStateFlow<String?>(null)
    val username = _username.asStateFlow()

    fun setUsername(name: String) {
        UserSession.username = name
        _username.value = name
    }

    fun isAuthorized(): Boolean {
        return UserSession.username != null
    }
}
