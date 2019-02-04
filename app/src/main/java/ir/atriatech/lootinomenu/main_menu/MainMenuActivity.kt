package ir.atriatech.lootinomenu.main_menu

import AppDH
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import com.example.beautyshopapplication.base.BaseActivity
import ir.atriatech.lootinomenu.EXTERA_MAIN_MENU_ACTIVITY
import ir.atriatech.lootinomenu.R
import ir.atriatech.lootinomenu.data_base.room.AppDataBase
import ir.atriatech.lootinomenu.food_detail.FoodDetailActivity
import kotlinx.android.synthetic.main.activity_main_menu.*
import javax.inject.Inject
import eu.davidea.flexibleadapter.items.IFlexible



class MainMenuActivity : BaseActivity() {


	lateinit var adapter: Adapter
	protected val component by lazy { AppDH.baseComponent() }

	init {
		component.inject(this)
	}

	@Inject
	lateinit var appDataBase: AppDataBase

	companion object {
		fun newIntent(context: Context, currentPosition: Int): Intent {
			val intent = Intent(context, MainMenuActivity::class.java)
			intent.putExtra(EXTERA_MAIN_MENU_ACTIVITY, currentPosition)
			return intent
		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main_menu)
		val fragmentAdapter = MainMenuViewPagerAdapter(supportFragmentManager)
		main_menu_activity_view_pager.adapter = fragmentAdapter
		main_menu_tab_layout.setupWithViewPager(main_menu_activity_view_pager)
		val position = intent.extras.getInt(EXTERA_MAIN_MENU_ACTIVITY)
		main_menu_activity_view_pager.currentItem = position
		img_btn_back_btn.setOnClickListener { this@MainMenuActivity.finish() }
	}
}
