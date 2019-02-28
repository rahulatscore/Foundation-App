package com.example.betworks.di

import com.example.betworks.analytics.SampleData
import com.nhaarman.mockitokotlin2.mock
import dagger.Module
import dagger.Provides

@Module
open class TestAppModule {

    @Provides
    open fun provideSampleData() = mock<SampleData>()

}