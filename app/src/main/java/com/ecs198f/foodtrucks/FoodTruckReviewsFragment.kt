package com.ecs198f.foodtrucks

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ecs198f.foodtrucks.databinding.FragmentFoodTruckReviewsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodTruckReviewsFragment(private var foodTruck: FoodTruck) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentFoodTruckReviewsBinding.inflate(inflater, container, false)
        val recyclerViewAdapter = FoodReviewListRecyclerViewAdapter(listOf())

        binding.reviewRecyclerView.apply {
            adapter = recyclerViewAdapter
            layoutManager = LinearLayoutManager(context)
        }

        (requireActivity() as MainActivity).apply {
            foodTruckService.listFoodReviews(foodTruck.id).enqueue(object : Callback<List<Review>> {
                override fun onResponse(
                    call: Call<List<Review>>,
                    response: Response<List<Review>>
                ) {
                    //Log.d("ignores this", "success review call")
                    recyclerViewAdapter.updateItems(response.body()!!)
                    //Log.d("ignores this", "finish review api call")
                }

                override fun onFailure(call: Call<List<Review>>, t: Throwable) {
                    throw t
                }
            })
        }

        return binding.root
    }
}