package ir.atriatech.lootinomenu

import android.app.Application
import androidx.room.Room
import com.example.kotlintest.base.di.BaseComponent
import com.example.kotlintest.base.di.DaggerBaseComponent
import com.example.kotlintest.base.di.base_app.BaseAppComponent
import com.example.kotlintest.base.di.base_app.BaseAppModule
import com.example.kotlintest.base.di.base_app.DaggerBaseAppComponent
import ir.atriatech.core.application.CoreApp
import ir.atriatech.core.di.CoreComponent
import ir.atriatech.lootinomenu.data_base.room.AppDataBase

class MyApp : CoreApp(){
//    companion object {
//        private var component: BaseComponent? = null
//
//        fun getComponent(): BaseComponent? {
//            return component
//        }
//    }

    override fun onCreate() {
        super.onCreate()
//        component = DaggerBaseComponent
//            .builder()
//            .build()
//
    }
}
