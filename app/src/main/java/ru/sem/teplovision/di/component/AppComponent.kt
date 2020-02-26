package ru.sem.teplovision.di.component;

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import ru.sem.teplovision.App
import ru.sem.teplovision.di.modules.ActivityModule
import ru.sem.teplovision.di.modules.AppModule
import ru.sem.teplovision.di.modules.FragmentModule
import ru.sem.teplovision.di.modules.ViewModelModule

import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidInjectionModule::class, ViewModelModule::class, FragmentModule::class, ActivityModule::class,
    AppModule::class,  AndroidSupportInjectionModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(cardApplication: App)

    //fun getRetrofit():Retrofit
}