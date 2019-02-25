package com.example.betworks.ui

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

//import android.support.v4.app.Fragment
//import android.support.v4.app.FragmentActivity
//import android.support.v4.app.FragmentManager
//import android.support.v4.app.FragmentTransaction
//import android.support.v7.app.AppCompatActivity


inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}

fun FragmentActivity.replaceFragment(fragment: Fragment, frameId: Int, tag: String? = null) {
    executeTransaction(supportFragmentManager, fragment, frameId, tag)
}

fun FragmentActivity.replaceChildFragment(fragment: Fragment, frameId: Int, tag: String? = null) {
    supportFragmentManager
        .fragments
        .firstOrNull { it.isVisible && it.isResumed}
        ?.replaceFragment(fragment, frameId, tag)
}

fun Fragment.replaceFragment(fragment: Fragment, frameId: Int, tag: String? = null) {
    executeTransaction(childFragmentManager, fragment, frameId, tag)
}

fun executeTransaction(fragmentManager: FragmentManager, fragment: Fragment, frameId: Int, tag: String? = null) {
    fragmentManager.inTransaction {
        if(tag != null) {
            replace(frameId, fragment).addToBackStack(tag)
        } else {
            replace(frameId, fragment)
        }
    }
}

fun <T: AppCompatActivity> AppCompatActivity.openActivity(activity: Class<T>) {
    startActivity(Intent(this, activity))
}


