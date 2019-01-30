package ir.atriatech.lootinomenu.management

import AppDH
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.beautyshopapplication.base.BaseActivity
import ir.atriatech.lootinomenu.R
import ir.atriatech.lootinomenu.data_base.room.AppDataBase
import ir.atriatech.lootinomenu.management.management_food_list.ManagementFoodListFragment
import ir.atriatech.lootinomenu.management.management_panel.ManagementPanelAdapter
import ir.atriatech.lootinomenu.management.management_panel.ManagementPanelFragment
import ir.atriatech.lootinomenu.management.sub_menu.SubMenuFragment
import pub.devrel.easypermissions.EasyPermissions
import javax.inject.Inject

class ManagementActivity : BaseActivity(), ManagementPanelAdapter.CallBack,
	ManagementActivityCallBack {
	override fun ManagmentFragmentLoader(fragment: Fragment) {
		loadFragment(fragment)
	}

	protected val component by lazy { AppDH.baseComponent() }

	init {
		component.inject(this)
	}

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
					))
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
		setContentView(R.layout.activity_management)
		val managementPanelFragment =
			ManagementPanelFragment()
		Log.d("tag22", appDataBase.foodDao().countFoods().toString())
		loadFragment(managementPanelFragment)

	}

	private fun loadFragment(fragment: Fragment) {
		supportFragmentManager.beginTransaction()
			.replace(R.id.management_activity_container, fragment)
			.commit()
	}

	override fun onBackPressed() {
		super.onBackPressed()
	}

}

interface ManagementActivityCallBack {
	fun ManagmentFragmentLoader(fragment: Fragment)
}
