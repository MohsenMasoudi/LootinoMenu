package ir.atriatech.management

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ir.atriatech.lootinomenu.R

class ManagementActivity : AppCompatActivity() {
companion object {
	fun newIntent(context: Context): Intent {
		return Intent(context,ManagementActivity::class.java)
	}
}
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_management)
	}
}
