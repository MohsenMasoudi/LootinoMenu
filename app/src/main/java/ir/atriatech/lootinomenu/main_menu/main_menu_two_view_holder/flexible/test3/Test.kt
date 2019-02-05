package ir.atriatech.lootinomenu.main_menu.main_menu_two_view_holder.flexible.test3

import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import eu.davidea.flexibleadapter.items.IExpandable
import java.util.ArrayList

import eu.davidea.flexibleadapter.items.IFlexible
import eu.davidea.flexibleadapter.items.IHeader
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

//	inner class ExpandableHeaderItem :
//		AbstractFlexibleItem<ExpandableHeaderItem.ExpandableHeaderViewHolder>(),
//		IExpandable<ExpandableHeaderItem.ExpandableHeaderViewHolder, SubItem>,
//		IHeader<ExpandableHeaderItem.ExpandableHeaderViewHolder> {
//		init {
//			// Configure the section status: shown and/or expanded
//			setHidden(false)
//			setExpanded(true)
//			// NOT selectable, otherwise (if you have) the ActionMode will be activated on long click!
//			setSelectable(false)
//		}
//	}
}
