package ir.atriatech.lootinomenu.test.stickyheader

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shuhart.stickyheader.StickyAdapter
import ir.atriatech.lootinomenu.R
import ir.atriatech.lootinomenu.model.Food
import ir.atriatech.lootinomenu.model.SubMenu
import kotlinx.android.synthetic.main.main_menu_food_item.view.*
import kotlinx.android.synthetic.main.main_menu_header_item.view.*
import java.util.*

open class SectionAdapter : StickyAdapter<RecyclerView.ViewHolder, RecyclerView.ViewHolder>() {
	internal var items: List<Section> = ArrayList()

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		return if (viewType == Section.HEADER || viewType == Section.CUSTOM_HEADER) {
			HeaderViewholder(inflater.inflate(R.layout.main_menu_header_item, parent, false))
		} else ItemViewHolder(
			inflater.inflate(
				R.layout.main_menu_header_item,
				parent,
				false
			)
		)
	}

	@SuppressLint("SetTextI18n")
	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
		val type = items[position].type()
		val section = items[position].sectionPosition()
		if (type == Section.HEADER) {
			(holder as HeaderViewholder).binHeaderUI(items[position].menuList() as SubMenu)
		} else {
			(holder as ItemViewHolder).binItemUI(items[position].menuList() as Food)
		}
	}

	override fun getItemViewType(position: Int): Int {
		return items[position].type()
	}

	override fun getItemCount(): Int {
		return items.size
	}

	override fun getHeaderPositionForItem(itemPosition: Int): Int {
		return items[itemPosition].sectionPosition()
	}

	@SuppressLint("SetTextI18n")
	override fun onBindHeaderViewHolder(holder: RecyclerView.ViewHolder, headerPosition: Int) {
//		(holder as HeaderViewholder).binHeaderUI(items[headerPosition].menuList() as SubMenu)
//		if (items[headerPosition].menuList() is SubMenu) {
//			(holder as HeaderViewholder).binHeaderUI(items[headerPosition].menuList() as SubMenu)
//		}
	}

	override fun onCreateHeaderViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
		return createViewHolder(parent, Section.HEADER)
	}

	class HeaderViewholder internal constructor(itemView: View) :
		RecyclerView.ViewHolder(itemView) {
//				internal var textView: TextView
//
//		init {
//			textView = itemView.findViewById(R.id.txt_item_header_main_menu_recycler_view)
//		}
		fun binHeaderUI(subMenu: ir.atriatech.lootinomenu.model.SubMenu) {
			itemView.txt_item_header_main_menu_recycler_view.text = subMenu.name

		}


	}

	class ItemViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
		fun binItemUI(food: Food) {
			itemView.txt_item_name_main_recycler_view.text = food.productName
			itemView.txt_item_price_main_recycler_view.text = food.price.toString()
			itemView.txt_item_detail_main_recycler_view.text = food.productDetail

		}
	}
}
