package com.example.betworks.di

import com.example.betworks.ui.main.fragments.TabHostFragment
import com.example.betworks.ui.main.fragments.MainFragmentModuleStub
import com.example.betworks.ui.main.fragments.tab1.Tab1Fragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TestFragmentBuilders {
    @ContributesAndroidInjector (modules = [MainFragmentModuleStub::class])
    abstract fun mainFragment(): TabHostFragment

    @ContributesAndroidInjector (modules = [MainFragmentModuleStub::class])
    abstract fun tab1Fragment(): Tab1Fragment
}