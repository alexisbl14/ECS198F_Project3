package layout

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ecs198f.foodtrucks.*

class TabStateAdapter(fragment: Fragment, foodTruck: FoodTruck) : FragmentStateAdapter(fragment) {
    private var thisFoodTruck = foodTruck

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {

        Log.d("ignore", thisFoodTruck.toString())

        return if(position == 0) {
            FoodTruckMenuFragment(thisFoodTruck)
        } else {
            FoodTruckReviewsFragment(thisFoodTruck)
        }
    }
}