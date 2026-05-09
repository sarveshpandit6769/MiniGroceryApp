package com.example.minigroceryapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.minigroceryapp.data.model.Product

class MainViewModel : ViewModel() {

    private val _cartItems = MutableLiveData<MutableList<Product>>(mutableListOf())
    val cartItems: LiveData<MutableList<Product>> = _cartItems

    fun addToCart(product: Product) {
        val currentList = _cartItems.value ?: mutableListOf()
        val existingProduct = currentList.find { it.id == product.id }
        
        if (existingProduct != null) {
            existingProduct.quantity += 1
        } else {
            product.quantity = 1
            currentList.add(product)
        }
        _cartItems.value = currentList
    }

    fun updateQuantity(productId: Int, delta: Int) {
        val currentList = _cartItems.value ?: return
        val product = currentList.find { it.id == productId } ?: return
        
        product.quantity += delta
        if (product.quantity <= 0) {
            currentList.remove(product)
        }
        _cartItems.value = currentList
    }

    fun clearCart() {
        _cartItems.value = mutableListOf()
    }

    fun getTotalAmount(): Double {
        return _cartItems.value?.sumOf { it.price * it.quantity } ?: 0.0
    }
}
