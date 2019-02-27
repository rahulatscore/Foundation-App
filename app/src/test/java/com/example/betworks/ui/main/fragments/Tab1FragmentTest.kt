package com.example.betworks.ui.main.fragments

import android.widget.TextView
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.ApplicationProvider
import com.example.betworks.R
import com.example.betworks.TestBettingApplication
import com.example.betworks.analytics.SampleData
import com.example.betworks.di.TestDaggerComponent
import com.example.betworks.ui.main.fragments.tab1.Tab1Fragment
import com.nhaarman.mockitokotlin2.whenever
import it.cosenonjaviste.daggermock.DaggerMock
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = TestBettingApplication::class)
class Tab1FragmentTest {

    @get:Rule val rule = DaggerMock.rule<TestDaggerComponent>(MainFragmentModuleStub()) {
        val application = ApplicationProvider.getApplicationContext<TestBettingApplication>()
        customizeBuilder<TestDaggerComponent.Builder> { it.application(application) }
        set {
            it.inject(application)
        }
    }

    @Mock
    lateinit var sampleData: SampleData

    @Test
    fun test1() {
        whenever(sampleData.getSimpleData()).thenReturn("Just mock 2")

        val fragmentScenario = launchFragmentInContainer<Tab1Fragment>()

        fragmentScenario.onFragment {
            Assert.assertEquals("Just mock 2", it.view?.findViewById<TextView>(R.id.textView)?.text)
        }
    }

    @Test
    fun test2() {
        whenever(sampleData.getSimpleData()).thenReturn("Just mock 1")

        val fragmentScenario = launchFragmentInContainer<Tab1Fragment>()

        fragmentScenario.onFragment {
            Assert.assertEquals("Just mock 1", it.sampleData.getSimpleData())
            Assert.assertEquals("Just mock 1", it.view?.findViewById<TextView>(R.id.textView)?.text)
        }
    }
}