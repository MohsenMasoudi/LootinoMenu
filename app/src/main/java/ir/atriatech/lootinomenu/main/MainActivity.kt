package ir.atriatech.lootinomenu.main

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.beautyshopapplication.base.BaseActivity
import es.dmoral.toasty.Toasty
import ir.atriatech.lootinomenu.R
import ir.atriatech.lootinomenu.SHOULD_FINISH
import ir.atriatech.lootinomenu.login.LoginActivity
import ir.atriatech.lootinomenu.main_menu.MainMenuActivity
import ir.atriatech.lootinomenu.management.ManagementActivity
import kotlinx.android.synthetic.main.activity_main.*
import pub.devrel.easypermissions.EasyPermissions




class MainActivity : BaseActivity(), EasyPermissions.PermissionCallbacks {

	override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
		finish()
	}

	override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
	}

	override fun onRequestPermissionsResult(
		requestCode: Int,
		permissions: Array<String>,
		grantResults: IntArray
	) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults)

		// Forward results to EasyPermissions
		EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
	}

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
		setContentView(ir.atriatech.lootinomenu.R.layout.activity_main)

		try {
			if (intent.getBooleanExtra(SHOULD_FINISH, false)) {
	//			finishAffinity()
				packageManager.clearPackagePreferredActivities(packageName)
//				finish()


			}
		} catch (e: Exception) {
			Toasty.error(this,"امکان تغییر لانچر در اینجا وجود ندارد",700,true).show()

		}
		model = MainModel(this)
		view = MainView(this)
		lifecycle.addObserver(MainPresenter(model, view))
		btn_coffee.setOnClickListener {
			startActivity(MainMenuActivity.newIntent(this, 0))
		}
		btn_restaurant.setOnClickListener {
			startActivity(MainMenuActivity.newIntent(this, 1))
		}
		EasyPermissions.requestPermissions(
			this,
			"این برنامه نیاز به دسترسی به دوربین و دیتای گوشی دارد",
			250,
			Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
		)

	}

	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        view.onCreateOptionMenu(menu)
		menuInflater.inflate(R.menu.setting_menu, menu)
		return super.onCreateOptionsMenu(menu)
	}

	override fun onOptionsItemSelected(item: MenuItem?): Boolean {
		when (item?.itemId) {
			R.id.setting_item -> {
				val intent = LoginActivity.newIntent(this)
				startActivity(intent)
			}
		}
		return super.onOptionsItemSelected(item)

	}

	override fun onBackPressed() {
//        super.onBackPressed()
	}
fun chooseDefult(){
	val startMain = Intent(Intent.ACTION_MAIN)
	startMain.addCategory(Intent.CATEGORY_HOME)
	startMain.flags = Intent.FLAG_ACTIVITY_NEW_TASK
	startActivity(startMain)

}
}

