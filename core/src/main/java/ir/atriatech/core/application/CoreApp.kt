package ir.atriatech.core.application

import android.app.Application
import android.content.Context
import android.os.Build
import android.os.LocaleList
import androidx.multidex.MultiDex
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.stetho.Stetho
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import ir.atriatech.core.BuildConfig
import ir.atriatech.core.R
import ir.atriatech.core.di.AppModule
import ir.atriatech.core.di.CoreComponent
import ir.atriatech.core.di.DaggerCoreComponent
import java.util.*

open class CoreApp : Application() {

	companion object {
		lateinit var coreComponent: CoreComponent
	}

	override fun attachBaseContext(base: Context?) {
		super.attachBaseContext(base)
		MultiDex.install(this)
	}

	 override fun onCreate() {
		super.onCreate()
		initLocale()
		initFont()
		initDI()
		initStetho()
		initFresco()
	}

	private fun initLocale() {
		val res = getResources()
		val configuration = res.getConfiguration()
		val locale = Locale("fa")
		Locale.setDefault(locale)
		configuration.setLocale(locale)
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			val localList = LocaleList(locale)
			LocaleList.setDefault(localList)
			configuration.setLocales(localList)

		} else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
			configuration.setLocale(locale)
		}
		createConfigurationContext(configuration)
	}

	private fun initFont() {
		ViewPump.init(ViewPump.builder()
				.addInterceptor(CalligraphyInterceptor(
						CalligraphyConfig.Builder()
								.setDefaultFontPath(getString(R.string.IranSans))
								.setFontAttrId(R.attr.fontPath)
								.build()
				)).build()
		)
	}

	private fun initStetho() {
		if (BuildConfig.DEBUG)
			Stetho.initializeWithDefaults(this)
	}

	private fun initDI() {
		coreComponent = DaggerCoreComponent.builder().appModule(AppModule(this)).build()
	}

	private fun initFresco() {
		Fresco.initialize(this)
	}
}