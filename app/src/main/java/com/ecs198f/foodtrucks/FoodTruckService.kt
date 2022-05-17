package com.ecs198f.foodtrucks

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FoodTruckService {
    @GET("food-trucks")
    fun listFoodTrucks(): Call<List<FoodTruck>>

    @GET("food-trucks/{id}/items")
    fun listFoodItems(@Path("id") truckId: String): Call<List<FoodItem>>

    @GET("food-trucks/{id}/reviews")
    fun listFoodReviews(@Path("id") truckId: String): Call<List<Review>>

    @POST("/food-trucks/{id}/reviews")
    fun postFoodReviews(@Path("id") truckId: String, @Body review: Review): Call<Unit>
}