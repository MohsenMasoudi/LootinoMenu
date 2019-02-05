package ir.atriatech.lootinomenu.main_menu.main_menu_two_view_holder.flexible.model

import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import eu.davidea.flexibleadapter.items.IFlexible
import ir.atriatech.lootinomenu.R
import ir.atriatech.lootinomenu.main_menu.main_menu_two_view_holder.flexible.holder.MainItemHolder
import kotlinx.android.synthetic.main.main_menu_food_item.view.*

class MainItem : AbstractFlexibleItem<MainItemHolder>() {
	override fun bindViewHolder(
		adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>?,
		holder: MainItemHolder?,
		position: Int,
		payloads: MutableList<Any>?
	) {
		holder?.bindFoodUI(this)
	}

	override fun equals(other: Any?): Boolean {
		if (other is MainItem) {
			return this@MainItem.id == other.id
		}
		return false
	}

	override fun hashCode(): Int {
		return id.hashCode()
	}

	override fun createViewHolder(
		view: View?,
		adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>?
	): MainItemHolder {
		return MainItemHolder(view!!,adapter = adapter!!)

	}

	override fun getLayoutRes(): Int {
		return R.layout.main_menu_food_item
	}

	var id: Int = 0
	var menuId: Int = 0
	var subMenuId = 0
	var productName: String = ""
	var productDetail: String = ""
	var foodOrder: Long = 0
	var price: Int = 0
	var picPath: Uri? = null
}