package com.example.betworks.ui.main

import com.example.betworks.R
import com.example.betworks.TestBettingApplication
import com.example.betworks.ui.inTransaction
import com.example.betworks.ui.main.fragments.TabHostFragment
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@RunWith(RobolectricTestRunner::class)
@Config(application = TestBettingApplication::class)
class MainActivityTest {


    @Test
    fun firstTest() {
//        val activity = Robolectric.setupActivity(MainActivity::class.java)
//        val fragment = TabHostFragment()
//        activity.supportFragmentManager.inTransaction {
//            replace(R.id.container, fragment)
//        }
            assertEquals(3, 1 + 2)
//        FragmentTestUtil.startFragment(fragment, MainActivity::class.java)
//        SupportFragmentTestUtil.startFragment(fragment, MainActivity::class.java)
//        assertEquals("Just mock",fragment.sampleData.getSimpleData())
        //val fragment = Robolectric.buildFragment(TabHostFragment::class.java, MainActivity::class.java)
        //val mainActivity = Robolectric.setupActivity(MainActivity::class.java)
        //assertEquals("Just mock", mainActivity.findViewById<TextView>(R.id.textView).text)
    }

}