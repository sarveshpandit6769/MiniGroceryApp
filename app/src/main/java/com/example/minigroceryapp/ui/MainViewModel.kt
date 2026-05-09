package com.example.minigroceryapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.minigroceryapp.data.AppDatabase
import com.example.minigroceryapp.data.model.Product
import kotlinx.coroutines.launch

/**
 * MainViewModel: Now integrated with Room Database for persistent storage.
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val cartDao = AppDatabase.getDatabase(application).cartDao()
    
    // Room returns LiveData that automatically updates when the database changes
    val cartItems: LiveData<List<Product>> = cartDao.getAllItems()

    /**
     * Logic to add a product to the local database.
     */
    fun addToCart(product: Product) {
        viewModelScope.launch {
            val existingProduct = cartDao.getProductById(product.id)
            if (existingProduct != null) {
                existingProduct.quantity += 1
                cartDao.insertOrUpdate(existingProduct)
            } else {
                product.quantity = 1
                cartDao.insertOrUpdate(product)
            }
        }
    }

    /**
     * Updates quantity in the database.
     */
    fun updateQuantity(productId: Int, delta: Int) {
        viewModelScope.launch {
            val product = cartDao.getProductById(productId) ?: return@launch
            
            product.quantity += delta
            if (product.quantity <= 0) {
                cartDao.delete(product)
            } else {
                cartDao.insertOrUpdate(product)
            }
        }
    }

    /**
     * Clears the persistent cart.
     */
    fun clearCart() {
        viewModelScope.launch {
            cartDao.clearCart()
        }
    }

    /**
     * Calculates total amount from the current list.
     */
    fun getTotalAmount(): Double {
        return cartItems.value?.sumOf { it.price * it.quantity } ?: 0.0
    }
}
