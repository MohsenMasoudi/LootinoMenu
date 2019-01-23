package ir.atriatech.lootinomenu.main

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MainPresenter(val model: MainModel, val view: MainView) : LifecycleObserver,
    MainContracter.Presenter {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        model.isThisFirstTimeToRunApp()


    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
    }
    
}