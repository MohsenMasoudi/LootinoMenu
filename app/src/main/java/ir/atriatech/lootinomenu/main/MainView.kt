package ir.atriatech.lootinomenu.main

import android.content.Context
import android.view.Menu
import android.view.MenuInflater
import android.widget.FrameLayout
import ir.atriatech.lootinomenu.R

class MainView constructor(context: Context) : FrameLayout(context), MainContracter.View {
	override fun onCreateOptionMenu(menu: Menu?) {
		val menuInflater:MenuInflater = MenuInflater(context)
		menuInflater.inflate(R.menu.setting_menu, menu)

	}


}