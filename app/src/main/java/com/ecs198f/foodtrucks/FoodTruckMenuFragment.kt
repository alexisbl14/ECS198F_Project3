package com.ecs198f.foodtrucks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ecs198f.foodtrucks.databinding.FragmentFoodTruckDetailBinding
import com.ecs198f.foodtrucks.databinding.FragmentFoodTruckMenuBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodTruckMenuFragment(private var foodItems: List<FoodItem>) : Fragment() {

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

        recyclerViewAdapter.updateItems(foodItems)

        return binding.root
    }
}