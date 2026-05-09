package com.example.minigroceryapp.ui.checkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.minigroceryapp.R
import com.example.minigroceryapp.databinding.ScreenCheckoutBinding
import com.example.minigroceryapp.ui.MainViewModel

/**
 * CheckoutScreen: Final step before placing order.
 */
class CheckoutScreen : Fragment() {

    private var _binding: ScreenCheckoutBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ScreenCheckoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnPlaceOrder.setOnClickListener {
            val address = binding.etAddress.text.toString()
            if (address.isNotEmpty()) {
                viewModel.clearCart()
                findNavController().navigate(R.id.action_checkout_to_orderSuccess)
            } else {
                Toast.makeText(context, "Please enter delivery address", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
