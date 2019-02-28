package com.example.betworks.di

import com.example.betworks.ui.main.fragments.TabHostFragment
import com.example.betworks.ui.main.fragments.tab1.Tab1Fragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TestFragmentBuilders {
    @ContributesAndroidInjector (modules = [TestAppModule::class])
    abstract fun mainFragment(): TabHostFragment

    @ContributesAndroidInjector (modules = [TestAppModule::class])
    abstract fun tab1Fragment(): Tab1Fragment
}