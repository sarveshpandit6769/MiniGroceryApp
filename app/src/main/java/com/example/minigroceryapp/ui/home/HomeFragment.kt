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
import com.example.minigroceryapp.databinding.FragmentHomeBinding
import com.example.minigroceryapp.ui.MainViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by activityViewModels()
    
    private val allProducts = listOf(
        Product(1, "Fresh Apple", 120.0, "", "Fruits"),
        Product(2, "Milk 1L", 60.0, "", "Dairy"),
        Product(3, "Bread", 40.0, "", "Bakery"),
        Product(4, "Onion 1kg", 30.0, "", "Vegetables"),
        Product(5, "Potato 1kg", 25.0, "", "Vegetables"),
        Product(6, "Banana Dozen", 50.0, "", "Fruits")
    )
    
    private lateinit var adapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView(allProducts)
        
        binding.btnCart.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_cartFragment)
        }

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterProducts(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun setupRecyclerView(productList: List<Product>) {
        adapter = ProductAdapter(productList) { product ->
            viewModel.addToCart(product.copy())
            Toast.makeText(context, "${product.name} added to cart", Toast.LENGTH_SHORT).show()
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
