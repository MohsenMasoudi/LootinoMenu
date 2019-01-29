package ir.atriatech.lootinomenu.food_detail

import AppDH
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.beautyshopapplication.base.BaseActivity
import ir.atriatech.lootinomenu.EXTERA_FOOD_DETAIL_ACTIVITY
import ir.atriatech.lootinomenu.R
import ir.atriatech.lootinomenu.data_base.room.AppDataBase
import ir.atriatech.lootinomenu.login.LoginActivity
import ir.atriatech.lootinomenu.main.MainActivity
import ir.atriatech.lootinomenu.main_menu.MainMenuActivity
import ir.atriatech.lootinomenu.model.Food
import kotlinx.android.synthetic.main.activity_food_detail.*
import javax.inject.Inject

class FoodDetailActivity : BaseActivity() {
	protected val component by lazy { AppDH.baseComponent() }
	var foodId: Int = 0
	lateinit var food: Food


	init {
		component.inject(this)
	}

	@Inject
	lateinit var appDataBase: AppDataBase

	companion object {
		fun newIntent(context: Context, foodId: Int): Intent {
			val intent = Intent(context, FoodDetailActivity::class.java)
			intent.putExtra(EXTERA_FOOD_DETAIL_ACTIVITY, foodId)
			return intent

		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		component.inject(this)
		setContentView(R.layout.activity_food_detail)
		try {
			foodId = intent.extras.getInt(EXTERA_FOOD_DETAIL_ACTIVITY)
		} catch (e: Exception) {
			foodId = 1
		}
		food = appDataBase.foodDao().findById(foodId)
		txt_name.text = food.productName
		txt_price.text = food.price.toString() + " تومان"
		txt_detail.text = food.productDetail
		btn_back_to_main_menu.setOnClickListener { this@FoodDetailActivity.finish() }
		img_btn_back_btn.setOnClickListener {
//			val launch=MainActivity.newIntent(this@FoodDetailActivity)
//			launch.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//			startActivity(launch)
			this@FoodDetailActivity.finish()
		}

	}
//	interface CallBack{
//		fun finishActivity()
//	}
}
