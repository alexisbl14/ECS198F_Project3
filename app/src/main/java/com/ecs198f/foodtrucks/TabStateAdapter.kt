package layout

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ecs198f.foodtrucks.*

class TabStateAdapter(fragment: Fragment, foodItems: List<FoodItem>, reviews: List<Review>) : FragmentStateAdapter(fragment) {
    private var theseFoodItems = foodItems
    private var theseReviews = reviews

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {

        Log.d("ignore", theseFoodItems.toString())
        Log.d("ignore", theseReviews.toString())

        return if(position == 0) {
            FoodTruckMenuFragment(theseFoodItems)
        } else {
            FoodTruckReviewsFragment(theseReviews)
        }
    }
}