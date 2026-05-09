package com.example.minigroceryapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class Product(
    @PrimaryKey val id: Int,
    val name: String,
    val price: Double,
    val image: String,
    val category: String,
    var quantity: Int = 0
)
