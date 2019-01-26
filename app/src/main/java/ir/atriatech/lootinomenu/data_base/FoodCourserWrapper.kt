package ir.atriatech.lootinomenu.data_base

import android.content.Context
import android.database.Cursor
import android.database.CursorWrapper
import ir.atriatech.lootinomenu.model.Food

class FoodCourserWrapper(cursor: Cursor) : CursorWrapper(cursor) {

    fun getFood(): Food {
        val id: Int = getInt(getColumnIndex("productId"))
        val menuId: Int = getInt(getColumnIndex("menuId"))
        val subMenuId: Int = getInt(getColumnIndex("subMenuId"))
        val productName: String = getString(getColumnIndex("productName"))
        val productDetail: String = getString(getColumnIndex("productDetail"))
        val price: Int = getInt(getColumnIndex("price"))
        val foodOrder=getInt(getColumnIndex("foodOrder"))
        val food = Food()
        food.id=id
        food.menuId=menuId
        food.subMenuId=subMenuId
        food.productName=productName
        food.productDetail=productDetail
        food.price=price
        food.foodOrder=foodOrder
        return food
    }
}