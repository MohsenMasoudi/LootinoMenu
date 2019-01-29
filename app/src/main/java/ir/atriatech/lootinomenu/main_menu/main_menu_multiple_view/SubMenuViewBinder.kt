package ir.atriatech.lootinomenu.main_menu.main_menu_multiple_view


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.atriatech.lootinomenu.R
import ir.atriatech.lootinomenu.model.SubMenu
import kotlinx.android.synthetic.main.main_menu_header_item.view.*
import me.drakeet.multitype.ItemViewBinder

class SubMenuViewBinder : ItemViewBinder<SubMenu, SubMenuViewBinder.ViewHolder>() {

	override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup): ViewHolder {
		val root = inflater.inflate(R.layout.main_menu_header_item, parent, false)
		return ViewHolder(root)
	}

	override fun onBindViewHolder(holder: ViewHolder, subMenu: SubMenu) {
		holder.bindHeaderUI(subMenu)

	}

	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		fun bindHeaderUI(subMenu: SubMenu) {
			itemView.txt_item_header_main_menu_recycler_view.text = subMenu.name
		}
	}
}
