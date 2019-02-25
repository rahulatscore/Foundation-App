package com.example.betworks.di

import com.example.betworks.ui.main.MainActivity
import com.example.betworks.ui.main.MainActivityModuleStub
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class TestActivityBuilders {

    @ContributesAndroidInjector (modules = [MainActivityModuleStub::class])
    abstract fun mainActivity(): MainActivity

}