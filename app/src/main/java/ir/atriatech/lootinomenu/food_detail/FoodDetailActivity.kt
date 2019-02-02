package ir.atriatech.lootinomenu.food_detail

import AppDH
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.beautyshopapplication.base.BaseActivity
import ir.atriatech.lootinomenu.EXTERA_FOOD_DETAIL_ACTIVITY
import ir.atriatech.lootinomenu.IMAGE_ASSETS_ADDRESS_BIG
import ir.atriatech.lootinomenu.R
import ir.atriatech.lootinomenu.data_base.room.AppDataBase
import ir.atriatech.lootinomenu.model.Food
import kotlinx.android.synthetic.main.activity_food_detail.*
import java.text.NumberFormat
import java.util.*
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

	@SuppressLint("SetTextI18n")
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
		val format = NumberFormat.getNumberInstance(Locale.US).format(food.price)


		txt_price.text = "$format تومان "
		txt_detail.text = food.productDetail
		btn_back_to_main_menu.setOnClickListener { this@FoodDetailActivity.finish() }
		img_btn_back_btn.setOnClickListener {
			this@FoodDetailActivity.finish()
		}
		val frescoFilPath = IMAGE_ASSETS_ADDRESS_BIG + food.id + ".jpg"
		if (food.picPath==null) {
			img_view_activity_food_detail.setImageURI(frescoFilPath)

		}else{
			img_view_activity_food_detail.setImageURI(food.picPath,applicationContext)

		}
		img_view_activity_food_detail.hierarchy.setPlaceholderImage(R.drawable.default_image)


	}

}
