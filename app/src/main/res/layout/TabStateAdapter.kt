package layout

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ecs198f.foodtrucks.FoodItem
import com.ecs198f.foodtrucks.FoodTruckMenuFragment

class TabStateAdapter(fragment: Fragment, foodItems: List<FoodItem>) : FragmentStateAdapter(fragment) {
    private var theseFoodItems = foodItems

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment = FoodTruckMenuFragment(theseFoodItems)

        return fragment
    }
}