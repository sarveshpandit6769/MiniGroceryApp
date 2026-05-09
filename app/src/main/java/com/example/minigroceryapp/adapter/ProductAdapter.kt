package com.example.minigroceryapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.minigroceryapp.R
import com.example.minigroceryapp.data.model.Product
import com.example.minigroceryapp.databinding.ItemProductBinding

/**
 * ProductAdapter: Optimized for professional look and feel.
 */
class ProductAdapter(
    private val products: List<Product>,
    private val onAddClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        
        holder.binding.tvName.text = product.name
        holder.binding.tvCategory.text = product.category
        holder.binding.tvPrice.text = "₹${product.price}"

        // Load image using Glide
        Glide.with(holder.itemView.context)
            .load(product.image)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.ic_menu_report_image)
            .into(holder.binding.ivProduct)
        
        holder.binding.btnAdd.setOnClickListener {
            onAddClick(product)
        }
    }

    override fun getItemCount() = products.size
}
