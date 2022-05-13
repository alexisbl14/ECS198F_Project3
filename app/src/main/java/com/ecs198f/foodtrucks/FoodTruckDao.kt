package com.ecs198f.foodtrucks

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FoodTruckDao {
    @Query("SELECT * FROM foodtruck")
    suspend fun listAllFoodTrucks(): List<FoodTruck>

    @Insert
    suspend fun addFoodTruck(foodTruck: FoodTruck)

    @Insert
    suspend fun addFoodTrucks(foodTrucks: List<FoodTruck>)

    @Delete
    suspend fun removeFoodTruck(foodTruck: FoodTruck)

    @Query("DELETE FROM foodtruck")
    suspend fun removeAllFoodTrucks()

    @Query("DELETE FROM foodtruck WHERE id=:id")
    suspend fun removeFoodTruckOfId(id: String)
}