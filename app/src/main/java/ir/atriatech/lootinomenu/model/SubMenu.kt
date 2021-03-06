package ir.atriatech.lootinomenu.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import eu.davidea.flexibleadapter.items.IHeader
import eu.davidea.viewholders.FlexibleViewHolder
import ir.atriatech.lootinomenu.main_menu.main_menu_two_view_holder.SubMenuViewHolder


@Entity(tableName = "sub_menu")
class SubMenu  {
	@PrimaryKey(autoGenerate = true)
	var subMenuId: Int = 0
	var name: String = ""
	var order: Long = 0
	var menuId: Int = 0
	var secondOrder:Long=0

}