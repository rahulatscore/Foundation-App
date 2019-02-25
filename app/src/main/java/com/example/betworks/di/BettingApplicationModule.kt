package com.example.betworks.di

import com.example.betworks.analytics.SampleData
import dagger.Module
import dagger.Provides


@Module
open class BettingApplicationModule {
    @Provides fun sampleData() = SampleData()
}



