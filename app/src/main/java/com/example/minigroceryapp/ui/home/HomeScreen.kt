package com.example.minigroceryapp.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.minigroceryapp.R
import com.example.minigroceryapp.adapter.ProductAdapter
import com.example.minigroceryapp.data.model.Product
import com.example.minigroceryapp.databinding.ScreenHomeBinding
import com.example.minigroceryapp.ui.MainViewModel

/**
 * HomeScreen: Displays the list of products with a sticky cart for engagement.
 */
class HomeScreen : Fragment() {

    private var _binding: ScreenHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()
    
    private val allProducts = listOf(
        Product(1, "Fresh Apple", 120.0, "https://images.unsplash.com/photo-1560806887-1e4cd0b6cbd6?w=500", "Fruits"),
        Product(2, "Amul Milk 1L", 66.0, "https://images.unsplash.com/photo-1563636619-e9107da5a1bb?w=500", "Dairy"),
        Product(3, "Brown Bread", 45.0, "https://images.unsplash.com/photo-1509440159596-0249088772ff?w=500", "Bakery"),
        Product(4, "Red Onion 1kg", 32.0, "https://images.unsplash.com/photo-1508747703725-719777637510?w=500", "Vegetables"),
        Product(5, "Potato 1kg", 28.0, "https://images.unsplash.com/photo-1518977676601-b53f82aba655?w=500", "Vegetables"),
        Product(6, "Banana Dozen", 54.0, "https://images.unsplash.com/photo-1528825876-0158895b85e1?w=500", "Fruits"),
        Product(7, "Cucumber 500g", 20.0, "https://images.unsplash.com/photo-1449300079323-02e209d9d3a6?w=500", "Vegetables"),
        Product(8, "Butter 100g", 55.0, "https://images.unsplash.com/photo-1589985270826-4b7bb135bc9d?w=500", "Dairy")
    )
    
    private lateinit var adapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ScreenHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView(allProducts)
        observeCartState()
        
        // Navigation via sticky cart
        binding.cvStickyCart.setOnClickListener {
            findNavController().navigate(R.id.action_home_to_cart)
        }

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterProducts(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    /**
     * Sticky Cart Observation: Shows/Hides the cart bar based on items.
     */
    private fun observeCartState() {
        viewModel.cartItems.observe(viewLifecycleOwner) { items ->
            if (items.isNotEmpty()) {
                binding.cvStickyCart.visibility = View.VISIBLE
                val count = items.sumOf { it.quantity }
                binding.tvCartCount.text = "$count ${if (count == 1) "ITEM" else "ITEMS"}"
                binding.tvCartAmount.text = "₹${viewModel.getTotalAmount()}"
            } else {
                binding.cvStickyCart.visibility = View.GONE
            }
        }
    }

    private fun setupRecyclerView(productList: List<Product>) {
        adapter = ProductAdapter(productList) { product ->
            viewModel.addToCart(product.copy())
            // Engagement: Feedback on add
            Toast.makeText(context, "${product.name} added", Toast.LENGTH_SHORT).show()
        }

        binding.rvProducts.layoutManager = GridLayoutManager(context, 2)
        binding.rvProducts.adapter = adapter
    }

    private fun filterProducts(query: String) {
        val filteredList = allProducts.filter { 
            it.name.contains(query, ignoreCase = true) || it.category.contains(query, ignoreCase = true)
        }
        setupRecyclerView(filteredList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
