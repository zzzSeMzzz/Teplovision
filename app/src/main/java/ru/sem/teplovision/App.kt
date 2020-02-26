package ru.sem.teplovision

import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import ru.sem.teplovision.di.component.AppComponent
import ru.sem.teplovision.di.component.DaggerAppComponent
import javax.inject.Inject


class App : Application(), HasAndroidInjector {

    companion object {
        val REQ_PHOTO = 3
        val EXTRA_IMG = "img"
        val EXTRA_MORE = "more"
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    lateinit var appComponent: AppComponent

    override fun androidInjector() = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .application(this)
                .build()
        appComponent.inject(this)
    }
}