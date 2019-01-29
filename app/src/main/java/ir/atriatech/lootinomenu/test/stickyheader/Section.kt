package ir.atriatech.lootinomenu.test.stickyheader

interface Section {

	fun type(): Int
	fun menuList(): Any
	fun sectionPosition(): Int

	companion object {
		val HEADER = 0
		val ITEM = 1
		val CUSTOM_HEADER = 2
	}
}
