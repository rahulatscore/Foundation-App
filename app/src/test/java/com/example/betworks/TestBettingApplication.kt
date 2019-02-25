package com.example.betworks

import com.example.betworks.di.DaggerTestDaggerComponent
import dagger.android.AndroidInjector

class TestBettingApplication: BettingApplication() {

//    override fun initDaggerComponent() {
//        DaggerTestDaggerComponent.create().inject(this)
//    }

    override fun applicationInjector(): AndroidInjector<out TestBettingApplication> {
        return DaggerTestDaggerComponent.builder().application(this).build()
    }

}