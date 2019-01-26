package ir.atriatech.lootinomenu

import android.app.Application
import androidx.room.Room
import ir.atriatech.core.application.CoreApp
import ir.atriatech.lootinomenu.data_base.room.AppDataBase

class MyApp : CoreApp(){
    override fun onCreate() {
        super.onCreate()
//        AppDataBase. =  Room.databaseBuilder(this, AppDataBase::class.java, "MyDatabase").allowMainThreadQueries().build()

    }
}
