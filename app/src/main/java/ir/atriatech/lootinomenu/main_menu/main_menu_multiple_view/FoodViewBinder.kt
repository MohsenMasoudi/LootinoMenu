package ir.atriatech.lootinomenu.main_menu.main_menu_multiple_view


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.atriatech.lootinomenu.R
import ir.atriatech.lootinomenu.food_detail.FoodDetailActivity
import ir.atriatech.lootinomenu.model.Food
import kotlinx.android.synthetic.main.main_menu_food_item.view.*
import me.drakeet.multitype.ItemViewBinder

class FoodViewBinder : ItemViewBinder<Food, FoodViewBinder.ViewHolder>() {

	override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
		val root = inflater.inflate(R.layout.main_menu_food_item, parent, false)
		return ViewHolder(root)
	}

	override fun onBindViewHolder(holder: ViewHolder, food: Food) {
		holder.bindFoodUI(food)

	}

	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
		init {

		}
		fun bindFoodUI(food: Food) {
			itemView.txt_item_name_main_recycler_view.text = food.productName
			itemView.txt_item_price_main_recycler_view.text = food.price.toString()+" تومان"
			itemView.txt_item_detail_main_recycler_view.text = food.productDetail
			itemView.setOnClickListener {
				itemView.context.startActivity(FoodDetailActivity.newIntent(itemView.context,food.id))
				 }
		}

	}
}
