package ir.atriatech.lootinomenu.management

import AppDH
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.beautyshopapplication.base.BaseActivity
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder
import ir.atriatech.lootinomenu.R
import ir.atriatech.lootinomenu.SHOULD_FINISH
import ir.atriatech.lootinomenu.data_base.room.AppDataBase
import ir.atriatech.lootinomenu.main.MainActivity
import ir.atriatech.lootinomenu.management.add_or_edit_food.AddOrEditFoodFragment
import ir.atriatech.lootinomenu.management.management_food_list.ManagementFoodListFragment
import ir.atriatech.lootinomenu.management.management_panel.ManagementPanelAdapter
import ir.atriatech.lootinomenu.management.management_panel.ManagementPanelFragment
import ir.atriatech.lootinomenu.management.sub_menu.SubMenuFragment
import ir.atriatech.lootinomenu.model.Food
import ir.atriatech.lootinomenu.model.SubMenu
import kotlinx.android.synthetic.main.fragment_management_panel.*
import javax.inject.Inject


open class ManagementActivity : BaseActivity(), ManagementPanelAdapter.CallBack,
	ManagementActivityCallBack, ManagementCallBackForDeleteSubMenuWarning,
	ManagementCallBackForDeleteFoodWarning, callBackChoosSubMenu {
	//	protected fun onFinishClick() {
//		Intent intent = new Intent(this, ActivityA.class);
//		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//		intent.putExtra(ActivityA.SHOULD_FINISH, true);
//		startActivity(intent);
//	}
	fun onFinishClick(){
		val intent=Intent(this,MainActivity::class.java)
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
		intent.putExtra(SHOULD_FINISH,true)
		startActivity(intent)
	}
	lateinit var dialogBuilder: NiftyDialogBuilder
	override fun loadLastFragment() {
		val beforeLast = fragmentList.size - 2
		loadFragment(fragmentList[beforeLast])
	}

	var fragmentList: MutableList<Fragment> = mutableListOf()
	override fun deleteWarningCallBack(food: Food) {
//		deleteWarning(food)
		deleteDialog(food)
	}

	override fun deleteWarningCallBack(subMenu: SubMenu) {
		deleteDialog(subMenu)
	}

	override fun ManagmentFragmentLoader(fragment: Fragment) {
		loadFragment(fragment)
	}


	protected val component by lazy { AppDH.baseComponent() }


	@Inject
	lateinit var appDataBase: AppDataBase

	override fun getFragmentToLoad(listId: Int) {
		when (listId) {
			0 -> {
				loadFragment(SubMenuFragment.newInstance(1))

			}
			1 -> {
				loadFragment(SubMenuFragment.newInstance(2))
			}
			2 -> {
				loadFragment(
					ManagementFoodListFragment.newInstance(
						0
					)
				)
			}

		}

	}

	//	lateinit var supportFragmentManager:Support
	companion object {
		fun newIntent(context: Context): Intent {
			return Intent(context, ManagementActivity::class.java)
		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		component.inject(this)

		setContentView(ir.atriatech.lootinomenu.R.layout.activity_management)
		val managementPanelFragment =
			ManagementPanelFragment()
		Log.d("tag22", appDataBase.foodDao().countFoods().toString())
		loadFragment(managementPanelFragment)


	}



	private fun loadFragment(fragment: Fragment) {
		fragmentList.add(fragment)
		supportFragmentManager.beginTransaction()
			.replace(R.id.management_activity_container, fragment)
			.commit()
	}

	override fun onBackPressed() {
		super.onBackPressed()
	}

	override fun onDestroy() {
		super.onDestroy()
		AddOrEditFoodFragment.foodCompanion = Food()
	}

	override fun onStop() {
		super.onStop()
	}

	fun deleteDialog(food: Food) {

		dialogBuilder = NiftyDialogBuilder.getInstance(this)
		dialogBuilder
			.withTitle("اخطار")
			.withTitleColor("#FFFFFF")
			.withDividerColor("#515151")
			.withMessage("می خواهید " + food.productName + " را حذف کنید؟")
			.withMessageColor("#FFFFFFFF")
			.withDialogColor("#3c3c3c")
			.withIcon(R.drawable.ic_error)
			.withDuration(700)
			.withEffect(Effectstype.Fadein)
			.withButton1Text("بله")
			.withButton2Text("خیر")
			.isCancelableOnTouchOutside(true)
//
			.setButton1Click {
				appDataBase.foodDao()
					.delete(food)
				ManagmentFragmentLoader(ManagementFoodListFragment.newInstance(food.subMenuId))
				dialogBuilder.dismiss()

			}

			.setButton2Click {
				dialogBuilder.dismiss()
			}
			.show()
	}

	fun deleteDialog(subMenu: SubMenu) {
		dialogBuilder = NiftyDialogBuilder.getInstance(this)
		dialogBuilder
			.withTitle("اخطار")
			.withTitleColor("#FFFFFF")
			.withDividerColor("#515151")
			.withMessage(" می خواهید بخش " + subMenu.name + " را حذف کنید؟")
			.withMessageColor("#FFFFFFFF")
			.withDialogColor("#3c3c3c")
			.withIcon(R.drawable.ic_error)
			.withDuration(700)
			.withEffect(Effectstype.Fadein)
			.withButton1Text("بله")
			.withButton2Text("خیر")
			.isCancelableOnTouchOutside(true)
			.setButton1Click {
				val listOfFood: MutableList<Food> =
					appDataBase.foodDao()
						.findBySubId(subMenu.subMenuId)
				for (i in listOfFood.indices) {
					listOfFood[i].subMenuId = 0

				}
				appDataBase.foodDao()
					.updateAll(foodList = listOfFood)

				appDataBase.subMenuDao().delete(subMenu)
				getFragmentToLoad(subMenu.menuId - 1)
				dialogBuilder.dismiss()


			}

			.setButton2Click {
				dialogBuilder.dismiss()
			}
			.show()
	}}




interface ManagementActivityCallBack {
	fun ManagmentFragmentLoader(fragment: Fragment)
}

interface ManagementCallBackForDeleteSubMenuWarning {
	fun deleteWarningCallBack(subMenu: SubMenu)
}

interface ManagementCallBackForDeleteFoodWarning {
	fun deleteWarningCallBack(food: Food)
}

interface callBackChoosSubMenu {
	fun loadLastFragment()
}