package ir.atriatech.lootinomenu.data_base

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import ir.atriatech.lootinomenu.model.Food
import ir.atriatech.lootinomenu.model.SubMenu
import java.util.ArrayList

class FoodDataBaseAccess(context: Context) {
    var openHelper: SQLiteOpenHelper = FoodDataBase(context)
    lateinit var dataBase: SQLiteDatabase

    companion object {
        var instance: FoodDataBaseAccess? = null
        fun  getInstance(context: Context): FoodDataBaseAccess {
            if (instance == null) {
                instance = FoodDataBaseAccess(context)
            }
            return instance as FoodDataBaseAccess
        }
    }



    fun open() {
        this.dataBase = openHelper.readableDatabase
    }


    fun close() {
        if (::dataBase.isInitialized) {
            this.dataBase.close()
        }
    }

    fun getFoodList(): MutableList<Food> {
        var cursor = dataBase.query("food_table", null, null, null, null, null, null, null)
        cursor.moveToFirst()
        var foodCursorWrapper = FoodCourserWrapper(cursor)
        var foods = ArrayList<Food>()
//        if (foodCursorWrapper.getFood() == null)
//            return foods
        foodCursorWrapper.moveToFirst()
        try {
            while (!foodCursorWrapper.isAfterLast) {
                val food = foodCursorWrapper.getFood()
                foods.add(food)
                foodCursorWrapper.moveToNext()
            }
        } finally {
            foodCursorWrapper.close()
            cursor.close()
        }
        Log.d("TAG10", "getFlowerList: " + foods.size)
        return foods
    }

    fun getSubMenuList(): MutableList<SubMenu> {
        val cursor = dataBase.query("sub_menu_table", null, null, null, null, null, null, null)
        cursor.moveToFirst()
        val subMenuCursorWrapper = SubMenuCourserWrapper(cursor)
        val subMenus = ArrayList<SubMenu>()
        subMenuCursorWrapper.moveToFirst()
        try {
            while (!subMenuCursorWrapper.isAfterLast) {
                val subMenu = subMenuCursorWrapper.getSubMenu()
                subMenus.add(subMenu)
                subMenuCursorWrapper.moveToNext()
            }
        } finally {
            subMenuCursorWrapper.close()
            cursor.close()
        }
        Log.d("TAG10", "getFlowerList: " + subMenus.size)
        return subMenus
    }
}