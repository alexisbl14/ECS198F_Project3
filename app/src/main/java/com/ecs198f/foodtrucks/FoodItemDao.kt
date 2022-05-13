package com.ecs198f.foodtrucks

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FoodItemDao {
    @Query("SELECT * FROM fooditem")
    suspend fun listAllFoodItems(): List<FoodItem>

    @Query("SELECT * FROM fooditem WHERE truckId=:truckId")
    suspend fun listFoodItemByTruckId(truckId: String): List<FoodItem>

    @Insert
    suspend fun addFoodItem(foodItem: FoodItem)

    @Insert
    suspend fun addFoodItems(foodItems: List<FoodItem>)

    @Delete
    suspend fun removeFoodItem(foodItem: FoodItem)

    @Query("DELETE FROM fooditem")
    suspend fun removeAllFoodItems()

    @Query("DELETE FROM fooditem WHERE truckId=:truckId")
    suspend fun removeFoodItemsForTruck(truckId: String)
}