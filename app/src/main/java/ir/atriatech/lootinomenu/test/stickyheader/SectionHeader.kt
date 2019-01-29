package ir.atriatech.lootinomenu.test.stickyheader

import ir.atriatech.lootinomenu.model.Food
import ir.atriatech.lootinomenu.model.SubMenu

class SectionHeader(private val section: Int,val subMenu: SubMenu) : Section {
	override fun menuList(): Any {
		return subMenu
	}

	override fun type(): Int {
		return Section.HEADER
	}

	override fun sectionPosition(): Int {
		return section
	}
}
