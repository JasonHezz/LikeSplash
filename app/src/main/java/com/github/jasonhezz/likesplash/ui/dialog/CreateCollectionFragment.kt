package com.github.jasonhezz.likesplash.ui.dialog

import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.jasonhezz.likesplash.R
import kotlinx.android.synthetic.main.dialog_create_collection.*

/**
 * Created by JasonHezz on 2018/1/15.

 */
class CreateCollectionFragment : DialogFragment() {

    private var callback: Callbacks? = null
    private var closeCallback: CloseCallback? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is Callbacks) callback = context
        if (context is CloseCallback) closeCallback = context
        if (parentFragment is Callbacks) callback = parentFragment as Callbacks
        if (parentFragment is CloseCallback) closeCallback = parentFragment as CloseCallback
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_create_collection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setNavigationOnClickListener { closeCallback?.close() }
        create_btn.setOnClickListener { callback?.onCreateButtonClick() }
        cancel_btn.setOnClickListener { callback?.onCancelButtonClick() }
    }

    interface Callbacks {
        fun onCreateButtonClick()
        fun onCancelButtonClick()
    }

    companion object {
        fun newInstance(): CreateCollectionFragment {
            val fragment = CreateCollectionFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}