package com.example.homechatclient.ui.chat

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homechatclient.R
import com.example.homechatclient.model.ChatMessage
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ChatViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val usernameText: TextView? = view.findViewById(R.id.usernameTextView)
    private val userNameText: TextView? = view.findViewById(R.id.userName)
    private val messageText: TextView? = view.findViewById(R.id.messageTextView)
    private val messageTextSimple: TextView? = view.findViewById(R.id.messageText)
    private val timeText: TextView? = view.findViewById(R.id.timeTextView)
    private val timeTextSimple: TextView? = view.findViewById(R.id.time)

    fun bind(message: ChatMessage) {
        if (message.isMine) {
            usernameText?.visibility = View.GONE
            userNameText?.visibility = View.GONE
        } else {
            userNameText?.text = message.username
            userNameText?.visibility = View.VISIBLE
        }

        messageText?.text = message.text
        messageTextSimple?.text = message.text

        val formattedTime = formatTime(message.timestamp)
        timeText?.text = formattedTime
        timeTextSimple?.text = formattedTime
    }

    private fun formatTime(time: Long): String {
        val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())
        return formatter.format(Date(time))
    }
}