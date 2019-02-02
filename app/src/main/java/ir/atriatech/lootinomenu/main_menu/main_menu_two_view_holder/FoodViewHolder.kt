package ir.atriatech.lootinomenu.main_menu.main_menu_two_view_holder

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ir.atriatech.lootinomenu.IMAGE_ASSETS_ADDRESS_SMALL
import ir.atriatech.lootinomenu.R
import ir.atriatech.lootinomenu.food_detail.FoodDetailActivity
import ir.atriatech.lootinomenu.model.Food
import kotlinx.android.synthetic.main.main_menu_food_item.view.*
import java.text.NumberFormat
import java.util.*

class FoodViewHolder(itemView: View, val picasso: Picasso) : RecyclerView.ViewHolder(itemView) {
	fun bindFoodUI(food: Food) {
		itemView.txt_item_name_main_recycler_view.text = food.productName
		val format = NumberFormat.getNumberInstance(Locale.US).format(food.price)
		itemView.txt_item_price_main_recycler_view.text = "$format تومان"
		itemView.txt_item_detail_main_recycler_view.text = food.productDetail
		itemView.setOnClickListener {
			itemView.context.startActivity(
				FoodDetailActivity.newIntent(
					itemView.context,
					food.id
				)
			)
		}
		if (food.picPath == null) {
			val frescoFilpath = IMAGE_ASSETS_ADDRESS_SMALL + food.id + ".jpg"
			itemView.img_view_main_menu_food_item.setImageURI(frescoFilpath)
		} else {
			itemView.img_view_main_menu_food_item.setImageURI(food.picPath)

		}
		itemView.img_view_main_menu_food_item.hierarchy.setPlaceholderImage(R.drawable.default_image)


	}

}