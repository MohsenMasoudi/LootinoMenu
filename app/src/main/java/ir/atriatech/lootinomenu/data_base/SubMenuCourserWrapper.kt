package ir.atriatech.lootinomenu.data_base

import android.database.Cursor
import android.database.CursorWrapper
import ir.atriatech.lootinomenu.model.SubMenu

class SubMenuCourserWrapper(cursor: Cursor) : CursorWrapper(cursor) {
    fun getSubMenu(): SubMenu {
        val id: Int = getInt(getColumnIndex("id"))
        val name: String = getString(getColumnIndex("name"))
        val order: Int = getInt(getColumnIndex("order"))
        val subMenu = SubMenu()
        subMenu.name = name
        subMenu.subMenuId = id
        subMenu.order = order
        return subMenu

    }
}