package com.example.betworks.ui.main.fragments


import android.content.Context
import android.os.Bundle

import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.betworks.R
import com.example.betworks.analytics.SampleData
import com.example.betworks.ui.main.fragments.tab1.Tab1Fragment
import com.example.betworks.ui.replaceChildFragment
import com.example.betworks.ui.replaceFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 *
 */
class TabHostFragment @Inject constructor() : Fragment() {

    @Inject lateinit var sampleData: SampleData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_host, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        childFragmentManager
            .addOnBackStackChangedListener(backStackChangeListener)

        if(childFragmentManager.backStackEntryCount == 0) {
            val fragment = Tab1Fragment.getIntance(sampleData.getSimpleData())
            replaceFragment(fragment, R.id.container_main, fragment.javaClass.simpleName)
        }
    }

    override fun onResume() {
        super.onResume()
        updateTopBackArrow()
    }

    private val backStackChangeListener = { updateTopBackArrow() }

    override fun onDestroyView() {
        super.onDestroyView()
        childFragmentManager
            .removeOnBackStackChangedListener(backStackChangeListener)
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        //inflater?.inflate(R.menu.main_fragment_option_items, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.itemSettings -> {
                val title = (item.title?.toString()?:"")
                    .plus(" ")
                    .plus(childFragmentManager.backStackEntryCount + 1)

                activity?.replaceChildFragment(
                    Tab1Fragment.getIntance(title),
                    R.id.container_main,
                    title)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateTopBackArrow() {
        with(
            (activity as AppCompatActivity)
                .supportActionBar
        ) {
            this?.setDisplayHomeAsUpEnabled(childFragmentManager.backStackEntryCount > 1)
        }
    }


    companion object {

        fun newInstance(savedState: SavedState? = null): TabHostFragment {
            val fragment = TabHostFragment()
            fragment.setInitialSavedState(savedState)
            return fragment
        }

    }

}
