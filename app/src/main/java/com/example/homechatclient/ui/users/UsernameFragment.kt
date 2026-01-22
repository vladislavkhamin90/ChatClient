package com.example.homechatclient.ui.users

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.homechatclient.R
import com.example.homechatclient.ui.MainViewModel

class UsernameFragment : Fragment(R.layout.fragment_username) {

    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val input = view.findViewById<EditText>(R.id.usernameInput)
        val button = view.findViewById<Button>(R.id.confirmButton)

        button.setOnClickListener {
            val name = input.text.toString().trim()
            if (name.isNotEmpty()) {
                viewModel.setUsername(name)
            }
        }
    }
}