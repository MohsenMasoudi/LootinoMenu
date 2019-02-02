package com.example.kotlintest.base.di.base_app

import android.content.Context
import android.content.SharedPreferences
import com.example.kotlintest.util.MyStringFormat
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import ir.atriatech.core.di.CoreComponent
import ir.atriatech.core.networking.Scheduler
import ir.atriatech.lootinomenu.common.remote.RequestService
import ir.atriatech.lootinomenu.data_base.room.AppDataBase
import retrofit2.Retrofit

@BaseAppScope
@Component(dependencies = [CoreComponent::class], modules = [BaseAppModule::class])
interface BaseAppComponent {
	fun context(): Context

	fun retrofit(): Retrofit

	fun sharedPreferences(): SharedPreferences

	fun scheduler(): Scheduler

	fun gson(): Gson

	fun compositeDisposable(): CompositeDisposable

	fun requestService(): RequestService


	fun myStringFormat(): MyStringFormat
	fun appDataBase(): AppDataBase
	fun picasso():Picasso
}