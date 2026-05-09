package com.example.minigroceryapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.minigroceryapp.data.model.Product

/**
 * AppDatabase: The SQLite database entry point.
 * We use the Singleton pattern to prevent multiple instances of the database from opening,
 * which is resource-heavy and could lead to data corruption.
 */
@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cartDao(): CartDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         * Thread-safe Singleton implementation.
         */
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "grocery_database"
                )
                // In a production app, we would handle migrations here.
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
