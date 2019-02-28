package com.example.betworks.ui.main.fragments

import android.widget.TextView
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import com.example.betworks.R
import com.example.betworks.TestBettingApplication
import com.example.betworks.analytics.SampleData
import com.example.betworks.daggerMockRule
import com.example.betworks.ui.main.fragments.tab1.Tab1Fragment
import com.nhaarman.mockitokotlin2.whenever
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

    @get:Rule val rule = daggerMockRule()

    @Mock
    lateinit var sampleData: SampleData

    @Test
    fun test1() {
        whenever(sampleData.getItemCount()).thenReturn(100)

        val fragmentScenario = launchFragmentInContainer<Tab1Fragment>()

        fragmentScenario.onFragment { fragment ->
            val recyclerView = fragment.view?.findViewById<RecyclerView>(R.id.recyclerView)
            assertEquals(100, recyclerView?.adapter?.itemCount)

            recyclerView?.assertItemAtPosition(0, "Item 0")
            recyclerView?.assertItemAtPosition(99, "Item 99")
        }
    }

    @Test
    fun test2() {
        whenever(sampleData.getItemCount()).thenReturn(50)

        val fragmentScenario = launchFragmentInContainer<Tab1Fragment>()

        fragmentScenario.onFragment {
            val recyclerView = it.view?.findViewById<RecyclerView>(R.id.recyclerView)
            assertEquals(50, recyclerView?.adapter?.itemCount)

            recyclerView?.assertItemAtPosition(0, "Item 0")
            recyclerView?.assertItemAtPosition(49, "Item 49")
        }
    }

    private fun RecyclerView?.assertItemAtPosition(position: Int, expectedText: String) {
        this?.scrollToPosition(position)
        val viewHolder = this?.findViewHolderForAdapterPosition(position)
        assertEquals(expectedText, viewHolder?.itemView?.findViewById<TextView>(android.R.id.text1)?.text)
    }
}