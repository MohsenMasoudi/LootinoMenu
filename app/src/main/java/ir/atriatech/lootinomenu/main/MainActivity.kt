package ir.atriatech.lootinomenu.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.beautyshopapplication.base.BaseActivity
import ir.atriatech.lootinomenu.EXTERA_FOOD_DETAIL_ACTIVITY
import ir.atriatech.lootinomenu.R
import ir.atriatech.lootinomenu.food_detail.FoodDetailActivity
import ir.atriatech.lootinomenu.login.LoginActivity
import ir.atriatech.lootinomenu.main_menu.MainMenuActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {
    private lateinit var model: MainModel
    private lateinit var view: MainView
    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, MainActivity::class.java)
            return intent

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        model = MainModel(this)
        view= MainView(this)
        lifecycle.addObserver(MainPresenter(model,view))
        btn_coffee.setOnClickListener {
            startActivity(MainMenuActivity.newIntent(this,0))
        }
        btn_restaurant.setOnClickListener {
            startActivity(MainMenuActivity.newIntent(this,1))
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        view.onCreateOptionMenu(menu)
        menuInflater.inflate(R.menu.setting_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.setting_item->{
                val intent=LoginActivity.newIntent(this)
                startActivity(intent)}
        }
        return super.onOptionsItemSelected(item)

    }

}
