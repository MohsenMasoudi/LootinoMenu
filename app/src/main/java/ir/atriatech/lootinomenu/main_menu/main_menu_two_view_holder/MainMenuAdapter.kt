package ir.atriatech.lootinomenu.main_menu.main_menu_two_view_holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kodmap.library.kmrecyclerviewstickyheader.KmStickyListener
import ir.atriatech.lootinomenu.R
import ir.atriatech.lootinomenu.TYPE_FOOD
import ir.atriatech.lootinomenu.TYPE_SUB_MENU
import ir.atriatech.lootinomenu.model.Food
import ir.atriatech.lootinomenu.model.SubMenu
import kotlinx.android.synthetic.main.main_menu_header_item.view.*


class MainMenuAdapter : RecyclerView.Adapter< RecyclerView.ViewHolder>()
	 {


	lateinit var twoViewList: MutableList<Any>
	override fun getItemViewType(position: Int): Int {
		if (twoViewList[position] is Food) {
			return TYPE_FOOD
		} else if (twoViewList[position] is SubMenu) {
			return TYPE_SUB_MENU
		}
		return super.getItemViewType(position)
	}


	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		val layoutInflater = LayoutInflater.from(parent.context)
		if (viewType == TYPE_FOOD) {
			val view = layoutInflater.inflate(R.layout.main_menu_food_item, parent, false)
			return FoodViewHolder(view)
		} else if (viewType == TYPE_SUB_MENU) {
			val view = layoutInflater.inflate(R.layout.main_menu_header_item, parent, false)
			return SubMenuViewHolder(view)

		}
		TODO()
	}

	override fun getItemCount(): Int {
		return twoViewList.size
	}

	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
		if (holder is FoodViewHolder) {

			holder.bindFoodUI(food = twoViewList[position] as Food)
		} else if (holder is SubMenuViewHolder) {

			holder.bindHeaderUI(subMenu = twoViewList[position] as SubMenu)
		}
	}





}