package com.ecs198f.foodtrucks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ecs198f.foodtrucks.databinding.FragmentFoodTruckReviewsBinding

class FoodTruckReviewsFragment(private var reviews: List<Review>) : Fragment() {

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

        recyclerViewAdapter.updateItems(reviews)

        return binding.root
    }
}