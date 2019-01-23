package ir.atriatech.core.di

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import dagger.Component
import ir.atriatech.core.networking.Scheduler
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, StorageModule::class, ImageModule::class])
interface CoreComponent {
	fun context(): Context

	fun retrofit(): Retrofit

	fun sharedPreferences(): SharedPreferences

	fun scheduler(): Scheduler

	fun gson(): Gson
}