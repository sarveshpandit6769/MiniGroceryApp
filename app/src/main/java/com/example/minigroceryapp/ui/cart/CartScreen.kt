package com.example.minigroceryapp.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.minigroceryapp.R
import com.example.minigroceryapp.adapter.CartAdapter
import com.example.minigroceryapp.databinding.ScreenCartBinding
import com.example.minigroceryapp.ui.MainViewModel

/**
 * CartScreen: Enhanced with Bill Details and professional checkout bar.
 */
class CartScreen : Fragment() {

    private var _binding: ScreenCartBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ScreenCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CartAdapter(emptyList()) { productId, delta ->
            viewModel.updateQuantity(productId, delta)
        }

        binding.rvCartItems.layoutManager = LinearLayoutManager(context)
        binding.rvCartItems.adapter = adapter

        // Observer for dynamic bill updates
        viewModel.cartItems.observe(viewLifecycleOwner) { items ->
            adapter.updateData(items)
            val total = viewModel.getTotalAmount()
            
            binding.tvItemTotal.text = "₹$total"
            binding.tvTotalAmount.text = "₹$total"
            binding.tvPayAmount.text = "₹$total"
            
            // If cart is empty, optionally navigate back or show empty state
            if (items.isEmpty()) {
                binding.btnCheckout.isEnabled = false
                binding.btnCheckout.alpha = 0.5f
            } else {
                binding.btnCheckout.isEnabled = true
                binding.btnCheckout.alpha = 1.0f
            }
        }

        binding.btnCheckout.setOnClickListener {
            findNavController().navigate(R.id.action_cart_to_checkout)
        }
        
        binding.toolbar.setNavigationIcon(android.R.drawable.ic_menu_revert)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
