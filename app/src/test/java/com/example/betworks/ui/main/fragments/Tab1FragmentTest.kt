package com.example.betworks.ui.main.fragments

import android.widget.TextView
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import com.example.betworks.R
import com.example.betworks.TestBettingApplication
import com.example.betworks.analytics.SampleData
import com.example.betworks.di.TestDaggerComponent
import com.example.betworks.ui.main.fragments.tab1.Tab1Fragment
import com.nhaarman.mockitokotlin2.whenever
import it.cosenonjaviste.daggermock.DaggerMock
import junit.framework.Assert.assertEquals
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
        whenever(sampleData.getItemCount()).thenReturn(100)

        val fragmentScenario = launchFragmentInContainer<Tab1Fragment>()

        fragmentScenario.onFragment {
            val recyclerView = it.view?.findViewById<RecyclerView>(R.id.recyclerView)
            assertEquals(100, recyclerView?.adapter?.itemCount)

            recyclerView?.measureForTests()

            recyclerView?.testItemAtPosition(0, "Item 0")
            recyclerView?.testItemAtPosition(99, "Item 99")
        }
    }

    @Test
    fun test2() {
        whenever(sampleData.getItemCount()).thenReturn(50)

        val fragmentScenario = launchFragmentInContainer<Tab1Fragment>()

        fragmentScenario.onFragment {
            val recyclerView = it.view?.findViewById<RecyclerView>(R.id.recyclerView)
            assertEquals(50, recyclerView?.adapter?.itemCount)

            recyclerView?.measureForTests()

            recyclerView?.testItemAtPosition(0, "Item 0")
            recyclerView?.testItemAtPosition(49, "Item 49")
        }
    }

    private fun RecyclerView?.testItemAtPosition(position: Int, expectedText: String) {
        val viewHolder = this?.findViewHolderForAdapterPosition(position)
        assertEquals(expectedText, viewHolder?.itemView?.findViewById<TextView>(android.R.id.text1)?.text)
    }

    // This is to get the recyclerview to measure itself so we can access all the viewholders
    // See https://github.com/robolectric/robolectric/issues/3747
    private fun RecyclerView?.measureForTests() {
        this?.measure(0,0)
        this?.layout(0,0,100,10000)
    }
}