package ir.atriatech.lootinomenu.data_base.room

import android.content.Context
import androidx.room.*
import androidx.room.Room.*
import ir.atriatech.lootinomenu.model.Food
import ir.atriatech.lootinomenu.model.SubMenu

@Database(entities = [Food::class, SubMenu::class],version = 1,exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun foodDao():FoodDao
    abstract fun subMenuDao():SubMenuDao

//    companion object {
//        var INSTANCE: AppDataBase? = null
//
//        fun getAppDataBase(context: Context): AppDataBase? {
//            if (INSTANCE == null){
//                synchronized(AppDataBase::class){
//                    INSTANCE = databaseBuilder(context.applicationContext, AppDataBase::class.java, "myDB").allowMainThreadQueries().build()
//                }
//            }
//            return INSTANCE
//        }
//
//        fun destroyDataBase(){
//            INSTANCE = null
//        }
//    }
}