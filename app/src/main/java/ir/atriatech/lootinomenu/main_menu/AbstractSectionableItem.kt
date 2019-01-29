package ir.atriatech.lootinomenu.main_menu

import androidx.recyclerview.widget.RecyclerView
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import eu.davidea.flexibleadapter.items.IHeader
import eu.davidea.flexibleadapter.items.ISectionable
import eu.davidea.viewholders.FlexibleViewHolder


abstract class AbstractSectionableItem<VH : RecyclerView.ViewHolder, T : IHeader<FlexibleViewHolder>>
constructor(header: T) : AbstractFlexibleItem<VH>(), ISectionable<VH, T> {
	lateinit var mHeader: T

	init {
		mHeader = header
	}

	override fun getHeader(): T {
		return mHeader
	}

	override fun setHeader(header: T) {
		this.mHeader = header
	}
}