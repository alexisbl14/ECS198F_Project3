package com.ecs198f.foodtrucks

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.ecs198f.foodtrucks.databinding.FragmentFoodTruckDetailBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import layout.TabStateAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodTruckDetailFragment : Fragment() {
    private val args: FoodTruckDetailFragmentArgs by navArgs()
    private var foodItems = listOf<FoodItem>()
    private var reviews = listOf<Review>()

    private lateinit var tabStateAdapter: TabStateAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

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
                        Log.d("ignores this", "success item call")
                        foodItems = response.body()!!
                        Log.d("ignore", foodItems[0].name)
                        Log.d("ignores this", "finish item api call")

                    }

                    override fun onFailure(call: Call<List<FoodItem>>, t: Throwable) {
                        throw t
                    }
                })

                foodTruckService.listFoodReviews(it.id).enqueue(object : Callback<List<Review>> {
                    override fun onResponse(
                        call: Call<List<Review>>,
                        response: Response<List<Review>>
                    ) {
                        Log.d("ignores this", "success review call")
                        reviews = response.body()!!
                        Log.d("ignore", reviews[0].authorName)
                        Log.d("ignores this", "finish review api call")
                    }

                    override fun onFailure(call: Call<List<Review>>, t: Throwable) {
                        throw t
                    }
                })
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("ignore", "inOnViewCreated")
        tabStateAdapter = TabStateAdapter(this, foodItems, reviews)
        viewPager = view.findViewById(R.id.viewPager2)
        viewPager.adapter = tabStateAdapter
        tabLayout = view.findViewById(R.id.tabLayout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            if(position == 0) tab.text = "Menu"
            else tab.text = "Review"
        }.attach()
    }
}