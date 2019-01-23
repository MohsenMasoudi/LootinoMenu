
import com.example.kotlintest.base.di.BaseComponent
import com.example.kotlintest.base.di.DaggerBaseComponent
import com.example.kotlintest.base.di.base_app.BaseAppComponent
import com.example.kotlintest.base.di.base_app.DaggerBaseAppComponent
import ir.atriatech.core.application.CoreApp
import javax.inject.Singleton

@Singleton
object AppDH {
	/*========================================App=======================================*/
	private var baseApp: BaseAppComponent? = null

	fun baseAppComponent(): BaseAppComponent {

		if (baseApp == null)
			baseApp = DaggerBaseAppComponent.builder().coreComponent(CoreApp.coreComponent).build()

		return baseApp as BaseAppComponent
	}

	private var baseComponent: BaseComponent? = null

	fun baseComponent(): BaseComponent {
		if (baseComponent == null)
			baseComponent = DaggerBaseComponent.builder().baseAppComponent(baseAppComponent()).build()

		return baseComponent as BaseComponent
	}

	fun destroyBaseComponent() {
		baseComponent = null
	}

}