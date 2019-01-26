package ir.atriatech.lootinomenu.login

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.beautyshopapplication.base.BaseActivity
import ir.atriatech.ADMIN_NAME
import ir.atriatech.ADMIN_PASS
import ir.atriatech.lootinomenu.R
import ir.atriatech.management.ManagementActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {
	var name: String = ""
	var pass: String = ""

	companion object {
		fun newIntent(context: Context): Intent {
			return Intent(context, LoginActivity::class.java)
		}
	}


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_login)
		view_base_login_activity.setOnClickListener { hideKeyboard(this) }
		btn_enter_admin.setOnClickListener {
			name = edit_text_name.text.toString()
			pass = edit_text_pass.text.toString()
			if (name == ADMIN_NAME && pass == ADMIN_PASS) {
				startActivity(ManagementActivity.newIntent(this))
				this.finish()
			}else{
				Toast.makeText(this@LoginActivity,getString(R.string.wrong_name_or_pass), Toast.LENGTH_SHORT).show()

			}

		}
	}


}