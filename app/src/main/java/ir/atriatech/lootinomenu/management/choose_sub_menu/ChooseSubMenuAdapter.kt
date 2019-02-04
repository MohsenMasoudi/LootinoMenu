package ir.atriatech.lootinomenu.management.choose_sub_menu

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.atriatech.lootinomenu.R
import ir.atriatech.lootinomenu.model.SubMenu
import kotlinx.android.synthetic.main.item_choose_sub_menu_fragment.view.*

class ChooseSubMenuAdapter(val list: MutableList<SubMenu>, val fragment: ChooseSubMenuFragment) :
	RecyclerView.Adapter<ChooseSubMenuAdapter.ChooseSubMenuViewHolder>() {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseSubMenuViewHolder {
		val layoutInflater = LayoutInflater.from(parent.context)
		val view =
			layoutInflater.inflate(R.layout.item_choose_sub_menu_fragment, parent, false)
		return ChooseSubMenuAdapter.ChooseSubMenuViewHolder(
			view, fragment = fragment
		)
	}

	override fun getItemCount(): Int {
		return list.size
	}

	override fun onBindViewHolder(holder: ChooseSubMenuViewHolder, position: Int) {
		holder.bindUI(list[position])
	}

	class ChooseSubMenuViewHolder(itemView: View, val fragment: ChooseSubMenuFragment) :

		RecyclerView.ViewHolder(itemView) {
		var menu_name: String = ""
		val callBack: callBackChooseSubMenuFragment = fragment
		fun bindUI(subMenu: SubMenu) {
			when {
				subMenu.menuId == 0 -> {
					menu_name = "دسته بندی نشده"
					itemView.txt_name.text = subMenu.name
		//				itemView.txt_name.setTextColor(Color.RED)

				}
				subMenu.menuId == 1 -> {
					menu_name = "کافی شاپ"
					itemView.txt_name.text = subMenu.name + " در منوی " + menu_name
				}
				subMenu.menuId == 2 -> {
					menu_name = "رستوران"
					itemView.txt_name.text = subMenu.name + " در منوی " + menu_name

				}
			}
			itemView.setOnClickListener {
				callBack.setSubMenu(subMenuId = subMenu.subMenuId, menuId = subMenu.menuId)
			}

		}

	}
}