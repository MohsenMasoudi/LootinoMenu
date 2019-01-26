package ir.atriatech.lootinomenu.data_base.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
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

//	@Query("SELECT COUNT(subMenuId) from food")
//	fun countMenuSubItems(): Int

	@Insert
	fun insertAll(foodList: List<Food>)

	@Insert
	fun insert(food: Food)

	@Delete
	fun delete(food: Food)
}