package com.example.betworks.di

import com.example.betworks.ui.main.MainActivity
import com.example.betworks.ui.main.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilders {

    @ContributesAndroidInjector (modules = [MainActivityModule::class])
    internal abstract fun bindMainActivity(): MainActivity // parent


    //internal abstract fun bindParent(): ParentClass // ParentModule

}