package com.example.minigroceryapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.minigroceryapp.data.model.Product

/**
 * CartDao: Data Access Object interface.
 * This component abstracts the underlying SQLite operations into high-level Kotlin functions.
 */
@Dao
interface CartDao {

    /**
     * Retrieves all cart items as a LiveData stream.
     * Room automatically triggers updates whenever the table data changes.
     */
    @Query("SELECT * FROM cart_items")
    fun getAllItems(): LiveData<List<Product>>

    /**
     * Upsert operation: Inserts a new product or replaces an existing one (Conflict resolution).
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(product: Product)

    /**
     * Deletes a specific product entry from the database.
     */
    @Delete
    suspend fun delete(product: Product)

    /**
     * Truncates the cart_items table to clear the user's cart.
     */
    @Query("DELETE FROM cart_items")
    suspend fun clearCart()

    /**
     * Predicate-based query to find a specific product by its ID.
     */
    @Query("SELECT * FROM cart_items WHERE id = :productId LIMIT 1")
    suspend fun getProductById(productId: Int): Product?
}
