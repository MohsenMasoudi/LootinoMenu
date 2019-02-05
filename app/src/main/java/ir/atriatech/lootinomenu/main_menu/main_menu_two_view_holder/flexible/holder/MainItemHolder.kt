package ir.atriatech.lootinomenu.main_menu.main_menu_two_view_holder.flexible.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.IFlexible
import eu.davidea.viewholders.FlexibleViewHolder
import ir.atriatech.lootinomenu.IMAGE_ASSETS_ADDRESS_SMALL
import ir.atriatech.lootinomenu.R
import ir.atriatech.lootinomenu.food_detail.FoodDetailActivity
import ir.atriatech.lootinomenu.main_menu.main_menu_two_view_holder.flexible.model.MainItem
import ir.atriatech.lootinomenu.model.Food
import kotlinx.android.synthetic.main.main_menu_food_item.view.*
import java.text.NumberFormat
import java.util.*

class MainItemHolder(itemView: View,adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>
                      ):FlexibleViewHolder(itemView, adapter,false) {

	fun bindFoodUI(mainItem: MainItem) {
		itemView.txt_item_name_main_recycler_view.text = mainItem.productName
		val format = NumberFormat.getNumberInstance(Locale.US).format(mainItem.price)
		itemView.txt_item_price_main_recycler_view.text = "$format تومان"
		itemView.txt_item_detail_main_recycler_view.text = mainItem.productDetail
		itemView.setOnClickListener {
			itemView.context.startActivity(
				FoodDetailActivity.newIntent(
					itemView.context,
					mainItem.id
				)
			)
		}
		if (mainItem.picPath == null) {
			val frescoFilpath = IMAGE_ASSETS_ADDRESS_SMALL + mainItem.id + ".jpg"
			itemView.img_view_main_menu_food_item.setImageURI(frescoFilpath)
		} else {
			itemView.img_view_main_menu_food_item.setImageURI(mainItem.picPath)

		}
		itemView.img_view_main_menu_food_item.hierarchy.setPlaceholderImage(R.drawable.default_image)


	}
}