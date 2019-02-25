package com.example.betworks.di

import com.example.betworks.BettingApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [AndroidSupportInjectionModule::class,
    BettingApplicationModule::class,
    ActivityBuilders::class, FragmentBuilders::class])
interface BettingApplicationComponent : AndroidInjector<BettingApplication> {
    override fun inject(app: BettingApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: BettingApplication): Builder

        fun build(): BettingApplicationComponent
    }
}