package ir.atriatech.lootinomenu.data_base.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ir.atriatech.lootinomenu.model.SubMenu
@Dao
interface SubMenuDao {
    @Query("SELECT * FROM sub_menu")
    fun getAll(): MutableList<SubMenu>

    @Query("SELECT * FROM sub_menu where subMenuId LIKE :id")
    fun findById(id:Int): SubMenu

    @Query("SELECT COUNT(*) from sub_menu")
    fun countSubMenu(): Int

    @Insert
    fun insertAll( subMenuList: List<SubMenu>)

    @Delete
    fun delete(subMenu: SubMenu)
}