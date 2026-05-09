package com.example.minigroceryapp.ui.checkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.minigroceryapp.R
import com.example.minigroceryapp.databinding.ScreenOrderSuccessBinding
import kotlin.random.Random

/**
 * OrderSuccessScreen: Shows confirmation after order placement.
 */
class OrderSuccessScreen : Fragment() {

    private var _binding: ScreenOrderSuccessBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ScreenOrderSuccessBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val orderId = "ORD" + Random.nextInt(100000, 999999)
        binding.tvOrderId.text = "Order ID: $orderId"
        
        binding.btnHome.setOnClickListener {
            findNavController().navigate(R.id.action_orderSuccess_to_home)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
