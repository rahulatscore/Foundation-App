package com.example.betworks

import com.example.betworks.di.DaggerBettingApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

open class BettingApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerBettingApplicationComponent.builder().application(this).build()
    }
}