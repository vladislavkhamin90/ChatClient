package com.example.homechatclient.ui.chat

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homechatclient.R
import com.example.homechatclient.databinding.FragmentChatBinding
import com.example.homechatclient.viewmodel.ChatViewModel
import kotlinx.coroutines.launch

class ChatFragment : Fragment(R.layout.fragment_chat) {

    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ChatViewModel by activityViewModels()

    private val adapter = ChatAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("MyLog: UI", "ChatFragment opened")

        _binding = FragmentChatBinding.bind(view)

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext()).apply {
                stackFromEnd = true
            }
        binding.recyclerView.adapter = adapter

        viewModel.connect()

        lifecycleScope.launch {
            viewModel.state.collect { state ->
                adapter.submitList(state.messages)
                binding.recyclerView.post {
                    if (state.messages.isNotEmpty()) {
                        binding.recyclerView.smoothScrollToPosition(state.messages.size - 1)
                    }
                }
            }
        }

        binding.sendButton.setOnClickListener {
            Log.d("MyLog: UI", "Send button clicked")
            viewModel.sendMessage(
                binding.messageEditText.text.toString()
            )
            binding.messageEditText.text.clear()
        }

        binding.toolbar.setNavigationOnClickListener {
            Log.d("MyLog: UI", "Exiting chat...")
            viewModel.disconnect()
            findNavController().navigate(R.id.action_chat_to_auth)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("MyLog: UI", "ChatFragment destroyed")
        _binding = null
    }
}