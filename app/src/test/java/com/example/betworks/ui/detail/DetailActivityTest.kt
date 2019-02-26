package com.example.betworks.ui.detail

import android.widget.TextView
import androidx.test.core.app.ActivityScenario.launch
import com.example.betworks.R
import org.junit.Assert.assertEquals
import org.junit.Test

class DetailActivityTest {

    @Test
    fun detailActivityTest() {

        val scenario = launch(DetailActivity::class.java)
        scenario.onActivity {
            assertEquals("Hi", it.findViewById<TextView>(R.id.itemDetail).text)
        }
    }


}