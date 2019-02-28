package com.example.betworks

import androidx.test.core.app.ApplicationProvider
import com.example.betworks.di.TestAppModule
import com.example.betworks.di.TestDaggerComponent
import it.cosenonjaviste.daggermock.DaggerMock

fun daggerMockRule() = DaggerMock.rule<TestDaggerComponent>(TestAppModule()) {

    val application = ApplicationProvider.getApplicationContext<TestBettingApplication>()

    customizeBuilder<TestDaggerComponent.Builder> { it.application(application) }

    set {
        it.inject(application)
    }
}