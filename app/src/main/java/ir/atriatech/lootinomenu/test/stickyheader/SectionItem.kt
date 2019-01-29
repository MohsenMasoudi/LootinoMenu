package ir.atriatech.lootinomenu.test.stickyheader

import ir.atriatech.lootinomenu.model.Food

class SectionItem(private val section: Int,val food: Food) : Section {
	override fun menuList(): Any {
		return food
	}

	override fun type(): Int {
		return Section.ITEM
	}

	override fun sectionPosition(): Int {
		return section
	}
}
