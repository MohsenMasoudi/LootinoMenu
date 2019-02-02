package ir.atriatech.lootinomenu.main_menu.main_menu_two_view_holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ir.atriatech.lootinomenu.model.SubMenu
import kotlinx.android.synthetic.main.main_menu_header_item.view.*

class SubMenuViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
	fun bindHeaderUI(subMenu: SubMenu) {
		itemView.txt_item_header_main_menu_recycler_view.text = subMenu.name
	}
}