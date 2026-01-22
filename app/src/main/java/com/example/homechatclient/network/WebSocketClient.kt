package com.example.homechatclient.network

import android.util.Log
import com.google.gson.Gson
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor

class WebSocketClient(
    private val userId: String,
    private val username: String,
    private val onConnected: () -> Unit,
    private val onMessageReceived: (NetworkMessage) -> Unit
) {

    private val gson = Gson()
    private var webSocket: WebSocket? = null

    private val loggingInterceptor = HttpLoggingInterceptor {
        Log.d("MyLog: HTTP", it)
    }.apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    fun connect() {
        if (webSocket != null) {
            Log.d("MyLog: WS", "connect() ignored — already connected")
            return
        }

        val url =
            "wss://192.168.0.161:8443/chat?userId=$userId&username=$username"

        Log.d("MyLog: WS", "connect() called")
        Log.d("MyLog: WS", "CONNECT URL = $url")

        val request = Request.Builder()
            .url(url)
            .build()

        webSocket = client.newWebSocket(request, listener)
    }

    fun send(message: NetworkMessage) {
        val json = gson.toJson(message)
        Log.d("MyLog: WS", "SEND -> $json")
        webSocket?.send(json)
    }

    fun disconnect() {
        Log.d("MyLog: WS", "disconnect()")
        webSocket?.close(1000, "Client closed")
        webSocket = null
    }

    private val listener = object : WebSocketListener() {

        override fun onOpen(webSocket: WebSocket, response: Response) {
            Log.d("MyLog: WS", "🟢 onOpen CALLED")
            Log.d("MyLog: WS", "Response = $response")
            onConnected()
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            Log.d("MyLog: WS", "📩 onMessage RAW = $text")

            try {
                val message =
                    gson.fromJson(text, NetworkMessage::class.java)
                onMessageReceived(message)
            } catch (e: Exception) {
                Log.e("MyLog: WS", "❌ JSON parse error", e)
            }
        }

        override fun onFailure(
            webSocket: WebSocket,
            t: Throwable,
            response: Response?
        ) {
            Log.e("MyLog: WS", "❌ onFailure", t)
            Log.e("MyLog: WS", "Response = $response")
        }

        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
            Log.d("MyLog: WS", "🟡 onClosing $code $reason")
        }

        override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
            Log.d("MyLog: WS", "🔴 onClosed $code $reason")
        }
    }
}
