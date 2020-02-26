package ru.sem.teplovision.di.modules;

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.sem.teplovision.ui.main.MainActivity
import ru.sem.teplovision.ui.photo.PhotoViewActivity


@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeMainActivity(): MainActivity?

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributePhotoActivity(): PhotoViewActivity?

}