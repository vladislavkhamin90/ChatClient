package com.example.homechatclient.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.homechatclient.R
import com.example.homechatclient.UserSession
import com.example.homechatclient.databinding.FragmentAuthBinding

class AuthFragment : Fragment(R.layout.fragment_auth) {

    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentAuthBinding.bind(view)

        binding.confirmButton.setOnClickListener {
            val username = binding.usernameInput.text.toString().trim()

            if (username.isNotEmpty()) {
                UserSession.username = username
                Log.d("MyLog: AF", "AuthFragment opened")
                Log.d("MyLog: AF", "Username entered = $username")

                findNavController()
                    .navigate(R.id.action_auth_to_chat)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
