package com.example.betworks.ui.main.fragments

import com.example.betworks.analytics.SampleData
import com.example.betworks.di.*
import com.example.betworks.ui.main.MainActivityModuleStub
import it.cosenonjaviste.daggermock.DaggerMock
import it.cosenonjaviste.daggermock.InjectFromComponent
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


// this test is not working, ignore it.

@RunWith(RobolectricTestRunner::class)
//@Config(application = TestBettingApplication::class)
class TabHostFragmentTest {

    @get:Rule val rule = DaggerMock.rule<TestDaggerComponent>(MainFragmentModuleStub())

    //@InjectFromComponent(MainActivity::class) lateinit var mainFragment: TabHostFragment
    //@InjectFromComponent(MainActivity::class) lateinit var mainFragment: TabHostFragment
    @InjectFromComponent(MainActivityModuleStub::class, TabHostFragmentModule::class) lateinit var sampleData: SampleData

    @Test
    fun test1() {

        assertEquals("Just data", sampleData.getSimpleData())

//         val mainFragment = TabHostFragment()
////         whenever(sampleData.getSimpleData()).thenReturn("Rahul Patel")
//        SupportFragmentTestUtil.startFragment(mainFragment, MainActivity::class.java)
//
//        println(mainFragment.view?.findViewById<TextView>(R.id.textView)?.text)
//        assertEquals(mainFragment.view?.findViewById<TextView>(R.id.textView)?.text, mainFragment.sampleData.getSimpleData())
    }

}