package ir.atriatech.lootinomenu.main_menu

import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import eu.davidea.flexibleadapter.items.IHeader
import eu.davidea.viewholders.FlexibleViewHolder

abstract class AbstractHeaderItem<VH : FlexibleViewHolder> : AbstractFlexibleItem<VH>()
	, IHeader<VH> {
	init {
		isHidden = true
		isSelectable = false
	}
}