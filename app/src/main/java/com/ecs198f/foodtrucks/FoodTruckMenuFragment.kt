package com.ecs198f.foodtrucks

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ecs198f.foodtrucks.databinding.FragmentFoodTruckDetailBinding
import com.ecs198f.foodtrucks.databinding.FragmentFoodTruckMenuBinding
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodTruckMenuFragment(private var foodTruck: FoodTruck) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentFoodTruckMenuBinding.inflate(inflater, container, false)
        val recyclerViewAdapter = FoodItemListRecyclerViewAdapter(listOf())

            binding.foodItemListRecyclerView.apply {
                adapter = recyclerViewAdapter
                layoutManager = LinearLayoutManager(context)
            }

        (requireActivity() as MainActivity).apply {
            foodTruckService.listFoodItems(foodTruck.id).enqueue(object : Callback<List<FoodItem>> {
                override fun onResponse(
                    call: Call<List<FoodItem>>,
                    response: Response<List<FoodItem>>
                ) {
                    lifecycleScope.launch {
                        // internet accessible, remove current items of current food truck from dao and add new items
                        foodItemDao.removeFoodItemsForTruck(foodTruck.id)
                        foodItemDao.addFoodItems(response.body()!!)
                        recyclerViewAdapter.updateItems(foodItemDao.listFoodItemByTruckId(foodTruck.id))
                    }

                }

                override fun onFailure(call: Call<List<FoodItem>>, t: Throwable) {
                    lifecycleScope.launch {
                        // internet not accessible, populate recycler view with current database
                        recyclerViewAdapter.updateItems(foodItemDao.listFoodItemByTruckId(foodTruck.id))
                    }
                }
            })
        }

        return binding.root
    }
}