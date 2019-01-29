package ir.atriatech.lootinomenu.main

import android.view.Menu
import ir.atriatech.lootinomenu.data_base.room.AppDataBase
import ir.atriatech.lootinomenu.model.Food
import ir.atriatech.lootinomenu.model.SubMenu

interface MainContracter {
	interface View {
		fun onCreateOptionMenu(menu: Menu?)
	}

	interface Model {
		fun getAllInnerDbFoods(): MutableList<Food>
		fun getAllInnerDbSubMenu(): MutableList<SubMenu>
		fun isThisFirstTimeToRunApp(): Boolean
//		fun getRoomDataBase(): AppDataBase

	}

	interface Presenter {

	}
}