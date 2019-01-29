package ir.atriatech.lootinomenu.management.sub_menu_food_list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.atriatech.lootinomenu.R
import ir.atriatech.lootinomenu.management.ManagementActivity
import ir.atriatech.lootinomenu.management.ManagementActivityCallBack
import ir.atriatech.lootinomenu.management.add_or_edit_food.AddOrEditFoodFragment
import ir.atriatech.lootinomenu.model.Food
import kotlinx.android.synthetic.main.item_sub_menu_food_list.view.*

class SubMenuFoodListAdapter : RecyclerView.Adapter<SubMenuFoodListAdapter.FoodViewHolder>() {
	lateinit var foodList: MutableList<Food>
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
		val layoutInflater = LayoutInflater.from(parent.context)
		val view =
			layoutInflater.inflate(R.layout.item_sub_menu_food_list, parent, false)
		return FoodViewHolder(
			view,
			parent.context
		)
	}

	override fun getItemCount(): Int {
		return foodList.size
	}

	override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
		holder.bindUI(food = foodList[position])
	}

	class FoodViewHolder(itemView: View, val context: Context) : RecyclerView.ViewHolder(itemView) {
//		init {
//			context as ManagementActivity
//
//		}

		fun bindUI(food: Food) {
			val managementActivityCallBack: ManagementActivityCallBack =
				context as ManagementActivity

			itemView.txt_detail_item_sub_menu_food_list.text = food.productDetail
			itemView.txt_price_item_sub_menu_food_list.text = food.price.toString()
			itemView.txt_name_item_sub_menu_food_list.text = food.productName
			itemView.img_btn_edit_item_sub_menu_food_list.setOnClickListener {
				managementActivityCallBack.ManagmentFragmentLoader(
					AddOrEditFoodFragment.newInstance(
						foodId = food.id, subMenuId = food.subMenuId,menuId = food.menuId
					)
				)
			}
		}

	}
}