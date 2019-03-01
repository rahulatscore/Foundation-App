package com.example.betworks.ui.main.fragments

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.widget.TextView
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import com.example.betworks.R
import com.example.betworks.TestBettingApplication
import com.example.betworks.analytics.SampleData
import com.example.betworks.daggerMockRule
import com.example.betworks.ui.main.fragments.tab1.Tab1Fragment
import com.example.betworks.ui.main.fragments.tab1.Tab1Fragment.Companion.LOCATION_PERMISSION_REQUEST_CODE
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowAlertDialog
import org.robolectric.shadows.ShadowToast

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

    @Test
    fun `if location permissions are already granted, check that a toast is shown`() {
        val applicationContext = ApplicationProvider.getApplicationContext<TestBettingApplication>()
        val shadowApplication = Shadows.shadowOf(applicationContext)
        shadowApplication.grantPermissions(Manifest.permission.ACCESS_FINE_LOCATION)

        val fragmentScenario = launchFragmentInContainer<Tab1Fragment>()

        fragmentScenario.onFragment {
            assertEquals("Thanks for granting location permissions", ShadowToast.getTextOfLatestToast())
        }
    }

    @Test
    fun `if location permissions are not granted, and we need to show rationale, show a dialog and then request permissions`() {
        val applicationContext = ApplicationProvider.getApplicationContext<TestBettingApplication>()

        val packageManager = applicationContext.packageManager
        val shadowPackageManager = shadowOf(packageManager)

        shadowPackageManager.setShouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION, true)

        val fragmentScenario = launchFragmentInContainer<Tab1Fragment>()

        fragmentScenario.onFragment { fragment ->
            val alertDialog = ShadowAlertDialog.getLatestDialog() as? AlertDialog
            val shadowDialog = shadowOf(alertDialog)

            assertEquals("We require your location to allow you to use the app", shadowDialog.message)

            alertDialog?.dismiss()

            assertEquals(Manifest.permission.ACCESS_FINE_LOCATION, shadowOf(fragment.activity).lastRequestedPermission.requestedPermissions[0])
        }
    }

    @Test
    fun `if location permissions are not granted, and we don't need rationale, request permissions`() {
        val applicationContext = ApplicationProvider.getApplicationContext<TestBettingApplication>()

        val packageManager = applicationContext.packageManager
        val shadowPackageManager = shadowOf(packageManager)

        shadowPackageManager.setShouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION, false)

        val fragmentScenario = launchFragmentInContainer<Tab1Fragment>()

        fragmentScenario.onFragment { fragment ->
            assertEquals(Manifest.permission.ACCESS_FINE_LOCATION, shadowOf(fragment.activity).lastRequestedPermission.requestedPermissions[0])
        }
    }

    @Test
    fun `if location permissions denied, show toast`() {
        val fragmentScenario = launchFragmentInContainer<Tab1Fragment>()

        fragmentScenario.onFragment { fragment ->
            fragment.onRequestPermissionsResult(LOCATION_PERMISSION_REQUEST_CODE, emptyArray(), intArrayOf(PackageManager.PERMISSION_DENIED))
            assertEquals("Location permission denied", ShadowToast.getTextOfLatestToast())
        }
    }

    @Test
    fun `if location permissions accepted, show toast`() {
        val fragmentScenario = launchFragmentInContainer<Tab1Fragment>()

        fragmentScenario.onFragment { fragment ->
            fragment.onRequestPermissionsResult(LOCATION_PERMISSION_REQUEST_CODE, emptyArray(), intArrayOf(PackageManager.PERMISSION_GRANTED))
            assertEquals("Location permission granted", ShadowToast.getTextOfLatestToast())
        }
    }

    private fun RecyclerView?.assertItemAtPosition(position: Int, expectedText: String) {
        this?.scrollToPosition(position)
        val viewHolder = this?.findViewHolderForAdapterPosition(position)
        assertEquals(expectedText, viewHolder?.itemView?.findViewById<TextView>(android.R.id.text1)?.text)
    }
}