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
import com.example.minigroceryapp.databinding.FragmentCartBinding
import com.example.minigroceryapp.ui.MainViewModel

/**
 * CartFragment: Shows added items and handles quantity updates.
 */
class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    
    // Shared ViewModel to access the same cart data
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize adapter with an empty list and a callback for quantity changes
        val adapter = CartAdapter(emptyList()) { productId, delta ->
            viewModel.updateQuantity(productId, delta)
        }

        binding.rvCartItems.layoutManager = LinearLayoutManager(context)
        binding.rvCartItems.adapter = adapter

        // OBSERVE changes in the cart. Every time an item is added or changed, this block runs.
        viewModel.cartItems.observe(viewLifecycleOwner) { items ->
            adapter.updateData(items) // Refresh the list
            binding.tvTotalAmount.text = "₹${viewModel.getTotalAmount()}" // Refresh the total bill
        }

        // Proceed to Checkout
        binding.btnCheckout.setOnClickListener {
            if ((viewModel.cartItems.value?.size ?: 0) > 0) {
                findNavController().navigate(R.id.action_cartFragment_to_checkoutFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
