package com.example.betworks.di

import com.example.betworks.TestBettingApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [AndroidSupportInjectionModule::class,
    TestBettingApplicationModule::class,
    TestActivityBuilders::class, TestFragmentBuilders::class, TestAppModule::class])
interface TestDaggerComponent  : AndroidInjector<TestBettingApplication> {
    override fun inject(app: TestBettingApplication)


    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: TestBettingApplication): Builder

        fun build(): TestDaggerComponent

        fun testAppModule(module: TestAppModule): Builder
    }
}