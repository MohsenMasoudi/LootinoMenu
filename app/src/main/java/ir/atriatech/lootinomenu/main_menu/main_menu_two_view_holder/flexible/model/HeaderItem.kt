package ir.atriatech.lootinomenu.main_menu.main_menu_two_view_holder.flexible.model

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractHeaderItem
import eu.davidea.flexibleadapter.items.IFlexible
import eu.davidea.viewholders.FlexibleViewHolder
import ir.atriatech.lootinomenu.R
import kotlinx.android.synthetic.main.main_menu_header_item.view.*

class HeaderItem : AbstractHeaderItem<HeaderItem.HeaderItemHolder>() {
	override fun bindViewHolder(
		adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>?,
		holder: HeaderItemHolder?,
		position: Int,
		payloads: MutableList<Any>?
	) {

		holder?.bindHeaderUI(((payloads!![position] )as HeaderItem))
	}

	override fun hashCode(): Int {
		return subMenuId.hashCode()
	}

	override fun equals(other: Any?): Boolean {
		if (other is HeaderItem){
			return this@HeaderItem.subMenuId==(other).subMenuId
		}
		return false
	}

	override fun createViewHolder(
		view: View?,
		adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>?
	): HeaderItemHolder {
		return HeaderItemHolder(view!!, adapter = adapter)
	}

	override fun getLayoutRes(): Int {
		return R.layout.main_menu_header_item
	}

	var subMenuId: Int = 0
	var name: String = ""
	var order: Long = 0
	var menuId: Int = 0
	var secondOrder:Long=0

	class HeaderItemHolder (
		itemView: View, adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>?
	): FlexibleViewHolder(itemView, adapter,true) {
		fun bindHeaderUI(headerItem: HeaderItem) {
			contentView.txt_item_header_main_menu_recycler_view.text = headerItem.name
		}
//	setDisplayHeadersAtStartUp()
	}
}