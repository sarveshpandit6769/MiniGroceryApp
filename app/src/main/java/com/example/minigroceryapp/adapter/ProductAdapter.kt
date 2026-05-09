package com.example.minigroceryapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.minigroceryapp.data.model.Product
import com.example.minigroceryapp.databinding.ItemProductBinding

/**
 * ProductAdapter: Handles displaying the list of products on the Home Screen.
 */
class ProductAdapter(
    private val products: List<Product>,
    private val onAddClick: (Product) -> Unit // Callback function for the "Add" button
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    // ViewHolder holds the references to the views for a single list item
    class ProductViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        // Inflate the XML layout for each product card
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        
        // Bind data to the views
        holder.binding.tvName.text = product.name
        holder.binding.tvPrice.text = "₹${product.price}"
        
        // Set click listener for the "Add to Cart" button
        holder.binding.btnAdd.setOnClickListener {
            onAddClick(product)
        }
    }

    override fun getItemCount() = products.size
}
