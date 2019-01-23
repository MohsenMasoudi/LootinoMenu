package com.example.kotlintest.base.di


import com.example.kotlintest.base.di.base_app.BaseAppComponent
import dagger.Component
import ir.atriatech.lootinomenu.main.MainActivity
import ir.atriatech.lootinomenu.main.MainModel

@BaseScope
@Component(dependencies = [BaseAppComponent::class])
interface BaseComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainModel: MainModel)
}