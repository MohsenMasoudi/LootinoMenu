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
        val foodOrder=getLong(getColumnIndex("foodOrder"))
        val food = Food()
        food.id=id
        food.menuId=menuId
        food.subMenuId=subMenuId
        food.productName=productName
        food.productDetail=""
        if (id==1||id==2||id==3) {
        food.productDetail="لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است."
        }
        food.price=price
        food.foodOrder=foodOrder
        return food
    }
}