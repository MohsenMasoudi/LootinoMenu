package ir.atriatech.lootinomenu.management.management_food_list

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.atriatech.lootinomenu.IMAGE_ASSETS_ADDRESS_SMALL
import ir.atriatech.lootinomenu.R
import ir.atriatech.lootinomenu.management.ManagementActivity
import ir.atriatech.lootinomenu.management.ManagementActivityCallBack
import ir.atriatech.lootinomenu.management.ManagementCallBackForDeleteFoodWarning
import ir.atriatech.lootinomenu.management.add_or_edit_food.AddOrEditFoodFragment
import ir.atriatech.lootinomenu.model.Food
import kotlinx.android.synthetic.main.item_management_food_list.view.*
import java.text.NumberFormat
import java.util.*

class ManagementFoodListAdapter
	: RecyclerView.Adapter<ManagementFoodListAdapter.FoodViewHolder>(),
	ManagementFoodListAdapterCallBack {
	override fun notifyDataSetChange() {
		notifyDataSetChanged()
	}

	lateinit var foodList: MutableList<Food>
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
		val layoutInflater = LayoutInflater.from(parent.context)
		val view =
			layoutInflater.inflate(R.layout.item_management_food_list, parent, false)
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



		@SuppressLint("SetTextI18n")
		fun bindUI(food: Food) {


			itemView.txt_detail_item_management_food_list.text = food.productDetail
			val format = NumberFormat.getNumberInstance(Locale.US).format(food.price)
			itemView.txt_price_item_management_food_list.text = "$format تومان "
			itemView.txt_name_item_management_food_list.text = food.productName
			itemView.img_btn_edit_item_management_food_list.setOnClickListener {
				val managementActivityCallBack: ManagementActivityCallBack =
					context as ManagementActivity
				managementActivityCallBack.ManagmentFragmentLoader(
					AddOrEditFoodFragment.newInstance(
						foodId = food.id, subMenuId = food.subMenuId, menuId = food.menuId
					)
				)
			}
			itemView.img_btn_delete_item_management_food_list.setOnClickListener {
				val callBack: ManagementCallBackForDeleteFoodWarning =
					context as ManagementCallBackForDeleteFoodWarning

				callBack.deleteWarningCallBack(food)


			}
			if (food.picPath==null){
				val frescoFilPath = IMAGE_ASSETS_ADDRESS_SMALL + food.id + ".jpg"
				itemView.simple_draw_food_menu_item.setImageURI(frescoFilPath)
			}else{
				itemView.simple_draw_food_menu_item.setImageURI(food.picPath)
			}
			itemView.simple_draw_food_menu_item.hierarchy.setPlaceholderImage(R.drawable.default_image)
			if (food.subMenuId == 0) {
//			itemView.img_btn_edit_item_management_food_list.visibility=View.INVISIBLE

			}
		}


	}

}

interface ManagementFoodListAdapterCallBack {
	fun notifyDataSetChange()
}