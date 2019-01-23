package com.example.kotlintest.base.di.base_app

import com.example.kotlintest.util.MyStringFormat
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import ir.atriatech.lootinomenu.common.remote.RequestService
import retrofit2.Retrofit

@Module
class BaseAppModule {

    //Composite disposable
    @Provides
    @BaseAppScope
    fun compositeDisposable(): CompositeDisposable = CompositeDisposable()


    //Request service
    @Provides
    @BaseAppScope
    fun publicRequestService(retrofit: Retrofit)
            : RequestService = retrofit.create(RequestService::class.java)



    //My string format
    @Provides
    @BaseAppScope
    fun providesMyStringFormat()
            : MyStringFormat = MyStringFormat()
}