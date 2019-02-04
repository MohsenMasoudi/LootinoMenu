package ir.atriatech.lootinomenu.main_menu.test

import android.annotation.SuppressLint

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.kodmap.library.kmrecyclerviewstickyheader.KmStickyListener
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ir.atriatech.lootinomenu.R
import ir.atriatech.lootinomenu.main_menu.main_menu_two_view_holder.FoodViewHolder
import ir.atriatech.lootinomenu.main_menu.main_menu_two_view_holder.SubMenuViewHolder
import ir.atriatech.lootinomenu.model.Food
import ir.atriatech.lootinomenu.model.SubMenu

import ir.atriatech.lootinomenu.TYPE_FOOD
import ir.atriatech.lootinomenu.TYPE_SUB_MENU

class RecyclerViewAdapter : ListAdapter<Any, RecyclerView.ViewHolder>(ModelDiffUtilCallback),
	KmStickyListener {

	override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		val itemView: View
		if (viewType == TYPE_SUB_MENU) {
			itemView = LayoutInflater.from(viewGroup.context)
				.inflate(R.layout.main_menu_food_item, viewGroup, false)
			return SubMenuViewHolder(itemView)
		} else {
			itemView = LayoutInflater.from(viewGroup.context)
				.inflate(R.layout.main_menu_header_item, viewGroup, false)
			return FoodViewHolder(itemView)
		}
	}

	override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, i: Int) {
		if (getItemViewType(i) == TYPE_SUB_MENU) {
			(viewHolder as SubMenuViewHolder).bindHeaderUI(getItem(i) as SubMenu)
		} else {
			(viewHolder as FoodViewHolder).bindFoodUI(getItem(i) as Food)
		}
	}


	override fun getItemViewType(position: Int): Int {
		return if (getItem(position) is SubMenu) {
			TYPE_SUB_MENU

		} else {
			TYPE_FOOD
		}
	}

	override fun getHeaderPositionForItem(itemPosition: Int?): Int? {
		var headerPosition: Int? = 0
		if (itemPosition != null) {
			for (i in itemPosition downTo 1) {
				if (isHeader(i)!!) {
					headerPosition = i
					return headerPosition
				}
			}
		}
		return headerPosition
	}

	override fun getHeaderLayout(headerPosition: Int?): Int? {
		return R.layout.main_menu_header_item
	}

	override fun bindHeaderData(header: View, headerPosition: Int?) {
		val tv = header.findViewById<TextView>(R.id.txt_item_header_main_menu_recycler_view)
		tv.text = (getItem(headerPosition!!) as SubMenu).name
	}

	override fun isHeader(itemPosition: Int?): Boolean? {

		return getItem(itemPosition!!) is SubMenu
	}

	companion object {

		val ModelDiffUtilCallback: DiffUtil.ItemCallback<Any> =
			object : DiffUtil.ItemCallback<Any>() {
				override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
					return if (oldItem is SubMenu && newItem is SubMenu) {
						true

					} else if (oldItem is Food && newItem is Food) {
						true
					} else {
						false
					}
				}

				override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
					return if (oldItem is SubMenu && newItem is SubMenu && oldItem
							.subMenuId == newItem.subMenuId
					) {
						true

					} else if (oldItem is Food && newItem is Food && oldItem.id == newItem.id) {
						true
					} else {
						false
					}
				}
			}
	}
}
