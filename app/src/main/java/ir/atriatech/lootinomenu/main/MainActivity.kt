package ir.atriatech.lootinomenu.main

import android.os.Bundle
import android.view.Menu
import com.example.beautyshopapplication.base.BaseActivity
import ir.atriatech.lootinomenu.R


class MainActivity : BaseActivity() {
    private lateinit var model: MainModel
    private lateinit var view: MainView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        model = MainModel(this)
        view= MainView(this)
        lifecycle.addObserver(MainPresenter(model,view))

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        view.onCreateOptionMenu(menu)
        menuInflater.inflate(R.menu.setting_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

}
