package ir.atriatech.lootinomenu.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Context
import android.util.Log
import ir.atriatech.lootinomenu.R
import ir.atriatech.lootinomenu.data_base.FoodDataBaseAccess
import ir.atriatech.lootinomenu.data_base.room.AppDataBase
import ir.atriatech.lootinomenu.model.Food
import ir.atriatech.lootinomenu.model.SubMenu


class MainActivity : AppCompatActivity() {
    private lateinit var mainModel: MainModel
    private lateinit var mainView: MainView

    private var firstTime: Boolean? = null
//    private lateinit var dataBase: AppDataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainModel = MainModel(this)
        mainView= MainView(this)
        lifecycle.addObserver(MainPresenter(mainModel,mainView))

    }

}
