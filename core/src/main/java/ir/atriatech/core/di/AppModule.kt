package ir.atriatech.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ir.atriatech.core.networking.AppScheduler
import ir.atriatech.core.networking.Scheduler
import javax.inject.Singleton

@Module(includes = [StorageModule::class])
class AppModule(val context: Context) {
	@Provides
	@Singleton
	fun providesContext(): Context {
		return context
	}

	@Provides
	@Singleton
	fun scheduler(): Scheduler {
		return AppScheduler()
	}
}