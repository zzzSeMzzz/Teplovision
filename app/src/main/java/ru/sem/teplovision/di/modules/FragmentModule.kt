package ru.sem.teplovision.di.modules;

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.sem.teplovision.ui.main.MainFragment


@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment


}