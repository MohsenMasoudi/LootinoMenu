package ir.atriatech.lootinomenu.data_base.room

import androidx.room.*
import ir.atriatech.lootinomenu.model.SubMenu
import androidx.room.OnConflictStrategy



@Dao
interface SubMenuDao {
	@Query("SELECT * FROM sub_menu")
	fun getAll(): MutableList<SubMenu>

	@Query("SELECT * FROM sub_menu where subMenuId LIKE :id")
	fun findById(id: Int): SubMenu

	@Query("SELECT * FROM sub_menu where menuId LIKE :menuId Order BY `order` ASC")
	fun getAllWithMenuId(menuId: Int): MutableList<SubMenu>

	@Update
	fun updateAll(listSubMenu: MutableList<SubMenu>)


	@Query("SELECT COUNT(*) from sub_menu")
	fun countSubMenu(): Int

	@Insert
	fun insertAll(subMenuList: List<SubMenu>)

	@Delete
	fun delete(subMenu: SubMenu)

	@Insert(onConflict = OnConflictStrategy.FAIL)
	fun insert(entity: SubMenu)

	@Update(onConflict = OnConflictStrategy.FAIL)
	fun update(entity: SubMenu)
}