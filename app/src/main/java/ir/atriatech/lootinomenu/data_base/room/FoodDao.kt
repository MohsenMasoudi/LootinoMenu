package ir.atriatech.lootinomenu.data_base.room

import androidx.room.*
import ir.atriatech.lootinomenu.model.Food

@Dao
interface FoodDao {
	@Query("SELECT * FROM food")
	fun getAll(): MutableList<Food>

	@Query("SELECT * FROM food where id LIKE :id")
	fun findById(id: Int): Food

	@Query("SELECT * FROM food where subMenuId LIKE :subId ORDER BY foodOrder ASC ")
	fun findBySubId(subId: Int): MutableList<Food>

	@Query("SELECT COUNT(*) from food")
	fun countFoods(): Int

	@Query("SELECT COUNT(*) from food where subMenuId LIKE :subId")
	fun countFoodsOfSubMenu(subId: Int): Int

	//	@Query("SELECT COUNT(subMenuId) from food")
//	fun countMenuSubItems(): Int
	@Update
	fun updateAll(foodList: MutableList<Food>)

	@Update(onConflict = OnConflictStrategy.FAIL)
	fun update(food: Food)

	@Insert
	fun insertAll(foodList: List<Food>)

	@Insert(onConflict = OnConflictStrategy.FAIL)

	fun insert(food: Food)

	@Delete
	fun delete(food: Food)


}