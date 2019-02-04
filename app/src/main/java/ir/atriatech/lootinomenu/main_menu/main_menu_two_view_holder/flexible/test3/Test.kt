package ir.atriatech.lootinomenu.main_menu.main_menu_two_view_holder.flexible.test3

import java.util.ArrayList

import eu.davidea.flexibleadapter.items.IFlexible
import ir.atriatech.lootinomenu.main_menu.main_menu_two_view_holder.flexible.model.HeaderItem
import ir.atriatech.lootinomenu.main_menu.main_menu_two_view_holder.flexible.model.MainItem

class Test {
	val databaseList: List<IFlexible<*>>
		get() {
			val list = ArrayList<IFlexible<*>>()
			list.add(HeaderItem())
			list.add(MainItem())
			return list
		}
}
