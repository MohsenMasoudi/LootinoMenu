package ir.atriatech.lootinomenu.main_menu.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.IFlexible
import eu.davidea.viewholders.FlexibleViewHolder
import ir.atriatech.lootinomenu.model.SubMenu
import kotlinx.android.synthetic.main.main_menu_header_item.view.*

class HeaderHolder {

	companion object {
		class HeaderViewHolder(
			itemView: View,
			adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>
		) : FlexibleViewHolder(itemView, adapter, true) {
			fun bindUI(subMenu: SubMenu) {
				itemView.txt_item_header_main_menu_recycler_view
			}
		}
	}

}