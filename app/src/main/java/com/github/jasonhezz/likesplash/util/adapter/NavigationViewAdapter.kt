package com.github.jasonhezz.likesplash.util.adapter

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.MenuItem

/**
 * Created by JavaCoder on 2017/6/29.
 */
abstract class NavigationViewAdapter(
    val fm: FragmentManager,
    defaultMenuId: Int, @IdRes val containerId: Int, savedInstanceState: Bundle?
) {

    private var currentlyAttachedId = defaultMenuId
    private var isAttached = false
    private var listener: NavigationView.OnNavigationItemSelectedListener? = null

    init {
        savedInstanceState?.let {
            if (!savedInstanceState.containsKey(KEY_CURRENT_ID)) {
                throw IllegalStateException(
                    "You must call NavigationViewFragmentAdapter.onSaveInstanceState() in your Activity onSaveInstanceState()"
                )
            } else {
                currentlyAttachedId = savedInstanceState.getInt(KEY_CURRENT_ID)
            }
        }
    }

    fun setNavigationItemSelectedListener(
        listener: NavigationView.OnNavigationItemSelectedListener?
    ) {
        this.listener = listener
    }

    fun onSaveInstanceState(outState: Bundle) = outState.putInt(KEY_CURRENT_ID, currentlyAttachedId)

    abstract fun getFragment(@IdRes menuItemId: Int): Fragment

    fun getTag(@IdRes menuItemId: Int) = "itemId:$menuItemId"

    open fun shouldHandleMenuItem(@IdRes menuItemId: Int) = true

    fun attachTo(view: NavigationView) {
        if (isAttached) throw IllegalStateException("The adapter can only be attached once.")
        else {
            view.setCheckedItem(currentlyAttachedId)
            view.setNavigationItemSelectedListener(FragmentAdapterItemSelectedListener())
            view.menu.performIdentifierAction(currentlyAttachedId, 0)
            isAttached = true
        }
    }

    inner class FragmentAdapterItemSelectedListener : NavigationView.OnNavigationItemSelectedListener {
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            if (shouldHandleMenuItem((item.itemId))) {
                val attachTag = getTag(item.itemId)
                var attachFragment = fm.findFragmentByTag(attachTag)
                if (attachFragment == null || !attachFragment.isAdded) {
                    val detachFragment = fm.findFragmentByTag(getTag(currentlyAttachedId))
                    val fragmentTransaction = fm.beginTransaction()
                    if (detachFragment != null && detachFragment != attachFragment) {
                        fragmentTransaction.detach(detachFragment)
                    }
                    if (attachFragment == null) {
                        attachFragment = getFragment(item.itemId)
                        fragmentTransaction.add(containerId, attachFragment, attachTag)
                    } else {
                        fragmentTransaction.attach(attachFragment)
                    }
                    fragmentTransaction.commitNow()
                }
                currentlyAttachedId = item.itemId
            }
            listener?.onNavigationItemSelected(item)
            return true
        }
    }

    companion object {
        private const val KEY_CURRENT_ID = "currentId"
    }
}