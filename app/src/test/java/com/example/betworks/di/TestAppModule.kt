package com.example.betworks.di

import com.example.betworks.analytics.SampleData
import com.nhaarman.mockitokotlin2.mock
import dagger.Module
import dagger.Provides

@Module
class TestAppModule {

    @Provides
    fun provideSampleData() = mock<SampleData>()

}