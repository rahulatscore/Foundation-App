package com.example.betworks.di

import com.example.betworks.TestBettingApplication
import com.example.betworks.ui.main.MainActivity
import com.example.betworks.ui.main.fragments.MainFragmentModuleStub
import com.example.betworks.ui.main.fragments.TabHostFragment
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [AndroidSupportInjectionModule::class,
    TestBettingApplicationModule::class,
    TestActivityBuilders::class, TestFragmentBuilders::class, MainFragmentModuleStub::class])
interface TestDaggerComponent  : AndroidInjector<TestBettingApplication> {
    override fun inject(app: TestBettingApplication)
    fun inject(tabHostFragment: TabHostFragment)
    fun inject(mainActivity: MainActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: TestBettingApplication): Builder

        fun build(): TestDaggerComponent

        fun mainFragmentModuleStub(module: MainFragmentModuleStub): Builder
    }
}