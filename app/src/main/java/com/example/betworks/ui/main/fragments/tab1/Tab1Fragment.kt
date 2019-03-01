package com.example.betworks.ui.main.fragments.tab1


import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
//import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.betworks.R
import com.example.betworks.analytics.SampleData
import com.example.betworks.ui.replaceChildFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_tab1.*
import java.util.*
import javax.inject.Inject

class Tab1Fragment : Fragment() {


    @Inject
    lateinit var sampleData: SampleData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let {
            if (ContextCompat.checkSelfPermission(it, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    AlertDialog.Builder(it)
                        .setMessage("We require your location to allow you to use the app")
                        .setOnDismissListener {
                            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
                        }
                        .show()
                } else {
                    requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
                }
            } else {
                Toast.makeText(it, "Thanks for granting location permissions", Toast.LENGTH_SHORT).show()
            }
        }

//        savedInstanceState?.let { extras ->
//            extras.getString(KEY)?.let {
//                editText.setText(it.toCharArray(), 0, it.length)
//            }
//        }
//        textView.text = arguments?.getString(KEY_NAME, "")
//
//        textView.setOnClickListener {
//            //replaceFragment()
//            val tag = "Random ${Random().nextInt()}"
//            activity?.replaceChildFragment(Tab1Fragment.getIntance(tag),
//                R.id.container_main, tag)
////            Navigator.navigateTo(activity,
////                Tab1Fragment.getIntance("Random ${Random().nextInt()}"))
//        }

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = ArrayAdapter()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode) {
            LOCATION_PERMISSION_REQUEST_CODE -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(context, "Location permission granted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Location permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    companion object {
        private const val KEY = "key_tab1"
        private const val KEY_NAME = "tab_name"

        internal const val LOCATION_PERMISSION_REQUEST_CODE = 12345

        @JvmStatic
        fun getIntance(name: String): Tab1Fragment {
            val fragment = Tab1Fragment()
            fragment.arguments = Bundle().apply {
                putString(KEY_NAME, name)
            }
            return fragment
        }
    }

    internal inner class ArrayAdapter : RecyclerView.Adapter<ViewHolder>() {

        // Gets the number of animals in the list
        override fun getItemCount(): Int {
            return sampleData.getItemCount()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false))
        }

        // Binds each animal in the ArrayList to a view
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.tvAnimalType.text = "Item $position"
        }
    }

    internal class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView that will add each animal to
        val tvAnimalType = view.findViewById<TextView>(android.R.id.text1)
    }
}


