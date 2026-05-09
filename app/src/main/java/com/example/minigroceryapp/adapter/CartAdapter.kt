package com.example.minigroceryapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.minigroceryapp.data.model.Product
import com.example.minigroceryapp.databinding.ItemCartBinding

class CartAdapter(
    private var cartItems: List<Product>,
    private val onUpdateQty: (Int, Int) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(val binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val product = cartItems[position]
        holder.binding.tvName.text = product.name
        holder.binding.tvPrice.text = "₹${product.price * product.quantity}"
        holder.binding.tvQty.text = product.quantity.toString()

        holder.binding.btnPlus.setOnClickListener {
            onUpdateQty(product.id, 1)
        }

        holder.binding.btnMinus.setOnClickListener {
            onUpdateQty(product.id, -1)
        }
    }

    override fun getItemCount() = cartItems.size

    fun updateData(newList: List<Product>) {
        cartItems = newList
        notifyDataSetChanged()
    }
}
