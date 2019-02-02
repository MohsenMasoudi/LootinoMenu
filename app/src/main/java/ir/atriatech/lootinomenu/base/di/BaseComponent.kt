package com.example.kotlintest.base.di


import com.example.kotlintest.base.di.base_app.BaseAppComponent
import dagger.Component
import ir.atriatech.lootinomenu.food_detail.FoodDetailActivity
import ir.atriatech.lootinomenu.main.MainActivity
import ir.atriatech.lootinomenu.main.MainModel
import ir.atriatech.lootinomenu.main_menu.MainMenuActivity
import ir.atriatech.lootinomenu.main_menu.MainMenuFragment
import ir.atriatech.lootinomenu.main_menu.main_menu_two_view_holder.MainMenuAdapter
import ir.atriatech.lootinomenu.management.ManagementActivity
import ir.atriatech.lootinomenu.management.sub_menu.SubMenuAdapter
import ir.atriatech.lootinomenu.management.sub_menu.SubMenuFragment

@BaseScope
@Component(dependencies = [BaseAppComponent::class])
interface BaseComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainModel: MainModel)
    fun inject(managementActivity: ManagementActivity)
    fun inject(subMenuFragment: SubMenuFragment)
	fun inject(mainMenuActivity: MainMenuActivity)
	fun inject(foodDetailActivity: FoodDetailActivity)
	fun inject(subMenuAdapter: SubMenuAdapter)
	fun inject(mainMenuAdapter: MainMenuAdapter)
}