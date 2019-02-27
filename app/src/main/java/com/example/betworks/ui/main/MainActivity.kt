package com.example.betworks.ui.main

//import android.support.v4.app.Fragment
//import android.support.v7.app.AppCompatActivity
import android.os.AsyncTask
import android.os.Bundle
import android.util.SparseArray
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.ScaleAnimation
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.crashlytics.android.Crashlytics
import com.example.betworks.R
import com.example.betworks.dummies.LogGenerator
import com.example.betworks.ui.detail.DetailActivity
import com.example.betworks.ui.main.fragments.TabHostFragment
import com.example.betworks.ui.openActivity
import com.example.betworks.ui.replaceFragment
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var savedStateSparseArray = SparseArray<Fragment.SavedState>()
    private var currentSelectItemId = R.id.item2

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState?.let {
            savedStateSparseArray = it.getSparseParcelableArray(SAVED_STATE_CONTAINER_KEY) ?: SparseArray()
            currentSelectItemId = it.getInt(SAVED_STATE_CURRENT_TAB_KEY, R.id.item2)
        }

        bottomNavigation
            .setOnNavigationItemSelectedListener {
                swapFragments(it.itemId, it.title?.toString() ?: "${it.itemId}")
                true
            }
        bottomNavigation.selectedItemId = currentSelectItemId
    }

    override fun onDestroy() {
        super.onDestroy()
        bottomNavigation.setOnNavigationItemSelectedListener(null)
    }

    fun onAccountButtonClicked(view: View) {
        Toast.makeText(this, "Account Clicked", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_option_items, menu)
        menu?.findItem(R.id.itemAccount)?.apply {
            setActionView(R.layout.custom_account_menu_layout)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
            R.id.itemCrashMe -> TODO()
            R.id.itemDetail -> openActivity(DetailActivity::class.java)
            R.id.itemLogs -> {
                AsyncTask.SERIAL_EXECUTOR.execute {
                    val log = LogGenerator.generateLog(1024 * 1024)
                    println("Sample Log Size = ${log.length}")
                    Crashlytics.log(log)
                    runOnUiThread {
                        Toast.makeText(applicationContext, "Log Generated", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            R.id.itemAccount -> Toast.makeText(this, "just clicked", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onBackPressed() {
        if (isOnBackPressedHandled()) {
            super.onBackPressed()
        }
    }

    private fun isOnBackPressedHandled(): Boolean {
        supportFragmentManager.fragments.forEach { fragment ->
            if (fragment != null && fragment.isVisible) {
                with(fragment.childFragmentManager) {
                    if (backStackEntryCount > 1) {
                        popBackStack()
                        return false
                    }
                }
            }
        }
        return true
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSparseParcelableArray(SAVED_STATE_CONTAINER_KEY, savedStateSparseArray)
        outState.putInt(SAVED_STATE_CURRENT_TAB_KEY, currentSelectItemId)
    }

    private companion object {
        const val SAVED_STATE_CONTAINER_KEY = "key_saved_state_container"
        const val SAVED_STATE_CURRENT_TAB_KEY = "key_saved_state_current_tab"
    }

    private fun swapFragments(actionId: Int, key: String) {
        with(supportFragmentManager) {
            if (findFragmentByTag(key) == null) {
                findFragmentById(R.id.container)?.let {
                    savedStateSparseArray.put(
                        currentSelectItemId,
                        saveFragmentInstanceState(it)
                    )
                }
                currentSelectItemId = actionId
                replaceFragment(TabHostFragment.newInstance(savedStateSparseArray[actionId]), R.id.container)
            }
        }
    }
}
