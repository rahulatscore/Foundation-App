package com.example.betworks.ui.main.fragments

import com.example.betworks.analytics.SampleData
import com.nhaarman.mockitokotlin2.whenever
import dagger.Module
import dagger.Provides
import org.mockito.Mockito

@Module
open class MainFragmentModuleStub {

    @Provides
    open fun sampleData(): SampleData {
        val sampleData = Mockito.mock(SampleData::class.java)
        whenever(sampleData.getSimpleData()).thenReturn("Just mock")
        return sampleData
    }

}