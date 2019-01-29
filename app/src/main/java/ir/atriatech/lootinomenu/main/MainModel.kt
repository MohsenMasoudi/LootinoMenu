package ir.atriatech.lootinomenu.main

import AppDH
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import ir.atriatech.lootinomenu.data_base.FoodDataBaseAccess
import ir.atriatech.lootinomenu.data_base.room.AppDataBase
import ir.atriatech.lootinomenu.model.Food
import ir.atriatech.lootinomenu.model.SubMenu
import javax.inject.Inject

class MainModel constructor(val context: Context) : ViewModel(), MainContracter.Model {
	protected val component by lazy { AppDH.baseComponent() }
	@Inject
	lateinit var dataBase: AppDataBase
	private var firstTime: Boolean? = null

//    override fun getRoomDataBase(): AppDataBase {
//        dataBase = AppDataBase.getAppDataBase(context.applicationContext)!!
//        return dataBase
//    }

	override fun isThisFirstTimeToRunApp(): Boolean {
		if (firstTime == null) {
			val mPreferences = context.getSharedPreferences("first_time", Context.MODE_PRIVATE)
			firstTime = mPreferences.getBoolean("firstTime", true)
			if (firstTime as Boolean) {
				val editor = mPreferences.edit()
				editor.putBoolean("firstTime", false)
				editor.apply()
				Log.d("tag22", "aaaaa")
				dataBase.foodDao().insertAll(getAllInnerDbFoods())
				dataBase.subMenuDao().insertAll(getAllInnerDbSubMenu())
			}
		}
		Log.d(
			"tag22",
			"dataBase.subMenuDao().countSubMenu()" + dataBase.subMenuDao().countSubMenu()
		)
		Log.d("tag22", "dataBase.foodDao().countFoods()" + dataBase.foodDao().countFoods())
		return firstTime as Boolean
	}

	override fun getAllInnerDbFoods(): MutableList<Food> {
		val foodDatabaseAccess = FoodDataBaseAccess.getInstance(context.applicationContext)
		foodDatabaseAccess.open()
		return foodDatabaseAccess.getFoodList()
	}

	override fun getAllInnerDbSubMenu(): MutableList<SubMenu> {
		val foodDatabaseAccess = FoodDataBaseAccess.getInstance(context.applicationContext)
		foodDatabaseAccess.open()
		return foodDatabaseAccess.getSubMenuList()
	}

//    @Inject
//    lateinit var sharedPreferences: SharedPreferences
//    protected val component by lazy { AppDH.baseComponent() }

	init {
		component.inject(this)
//        getRoomDataBase()
	}
}