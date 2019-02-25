package com.example.betworks.ui.main.fragments.tab1


import android.os.Bundle
//import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.betworks.R
import com.example.betworks.ui.replaceChildFragment
import kotlinx.android.synthetic.main.fragment_tab1.*
import java.util.*

class Tab1Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//    }
//
//    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
//        super.onViewCreated(view!!, savedInstanceState)
        savedInstanceState?.let { extras ->
            extras.getString(KEY)?.let {
                editText.setText(it.toCharArray(), 0, it.length)
            }
        }
        textView.text = arguments?.getString(KEY_NAME, "")

        textView.setOnClickListener {
            //replaceFragment()
            val tag = "Random ${Random().nextInt()}"
            activity?.replaceChildFragment(Tab1Fragment.getIntance(tag),
                R.id.container_main, tag)
//            Navigator.navigateTo(activity,
//                Tab1Fragment.getIntance("Random ${Random().nextInt()}"))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }


    companion object {
        private const val KEY = "key_tab1"
        private const val KEY_NAME = "tab_name"

        @JvmStatic
        fun getIntance(name: String): Tab1Fragment {
            val fragment = Tab1Fragment()
            fragment.arguments = Bundle().apply {
                putString(KEY_NAME, name)
            }
            return fragment
        }
    }
}
