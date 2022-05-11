package com.ecs198f.foodtrucks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ecs198f.foodtrucks.databinding.FoodItemBinding
import com.ecs198f.foodtrucks.databinding.ReviewItemBinding

class FoodReviewListRecyclerViewAdapter(private var reviews: List<Review>):
    RecyclerView.Adapter<FoodReviewListRecyclerViewAdapter.ViewHolder>(){
    class ViewHolder(val binding: ReviewItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ReviewItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        reviews[position].let {
            holder.binding.apply {
                profileName.text = it.authorName
                Glide.with(root).load(it.authorAvatarUrl).into(profilePic)
                reviewText.text = it.content
            }
        }
    }

    override fun getItemCount() = reviews.size

    fun updateItems(reviews: List<Review>) {
        this.reviews = reviews
        notifyDataSetChanged()
    }
}



