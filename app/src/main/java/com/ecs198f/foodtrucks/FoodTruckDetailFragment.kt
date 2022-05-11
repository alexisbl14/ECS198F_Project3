package com.ecs198f.foodtrucks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.ecs198f.foodtrucks.databinding.FragmentFoodTruckDetailBinding
import com.google.android.material.tabs.TabLayoutMediator
import layout.TabStateAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodTruckDetailFragment : Fragment() {
    private val args: FoodTruckDetailFragmentArgs by navArgs()
    private lateinit var foodItems : List<FoodItem>

    private lateinit var tabStateAdapter: TabStateAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFoodTruckDetailBinding.inflate(inflater, container, false)
        val recyclerViewAdapter = FoodItemListRecyclerViewAdapter(listOf())

        args.foodTruck.let {
            binding.apply {
                Glide.with(root).load(it.imageUrl).into(foodTruckDetailImage)
                foodTruckDetailPriceLevel.text = "$".repeat(it.priceLevel)
                foodTruckDetailLocation.text = it.location
                foodTruckDetailTime.text = it.formattedTimeInterval
                //foodItemListRecyclerView.apply {
                //    adapter = recyclerViewAdapter
                //    layoutManager = LinearLayoutManager(context)
                //}
            }

            (requireActivity() as MainActivity).apply {
                title = it.name

                foodTruckService.listFoodItems(it.id).enqueue(object : Callback<List<FoodItem>> {
                    override fun onResponse(
                        call: Call<List<FoodItem>>,
                        response: Response<List<FoodItem>>
                    ) {
                        //recyclerViewAdapter.updateItems(response.body()!!)
                        // pass data to fragment constructor
                        foodItems = response.body()!!
                    }

                    override fun onFailure(call: Call<List<FoodItem>>, t: Throwable) {
                        throw t
                    }
                })
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tabStateAdapter = TabStateAdapter(this, foodItems)
        viewPager = view.findViewById(R.id.viewPager2)
        viewPager.adapter = tabStateAdapter
        val tabLayout = view.findViewById(R.id.tabLayout)
        TabLayoutMediator(tabLayout, viewPager)
    }
}