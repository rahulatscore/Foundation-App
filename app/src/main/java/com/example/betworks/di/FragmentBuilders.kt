package com.example.betworks.di

import com.example.betworks.ui.main.fragments.TabHostFragmentModule
import com.example.betworks.ui.main.fragments.TabHostFragment
import com.example.betworks.ui.main.fragments.tab1.Tab1Fragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilders {
    @ContributesAndroidInjector(modules = [TabHostFragmentModule::class])
    abstract fun mainFragment(): TabHostFragment // child

    @ContributesAndroidInjector
    abstract fun tab1Fragment(): Tab1Fragment// child
}