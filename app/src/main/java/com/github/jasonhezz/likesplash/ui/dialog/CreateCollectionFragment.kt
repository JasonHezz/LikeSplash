package com.github.jasonhezz.likesplash.ui.dialog

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.jasonhezz.likesplash.R
import kotlinx.android.synthetic.main.dialog_create_collection.*

/**
 * Created by JasonHezz on 2018/1/15.

 */
class CreateCollectionFragment : Fragment() {

    var call: Callbacks? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is Callbacks) call = context
        if (parentFragment is Callbacks) call = parentFragment as Callbacks
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_create_collection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        create_btn.setOnClickListener { call?.onCreateButtonClick() }
        cancel_btn.setOnClickListener { call?.onCancelButtonClick() }
    }

    companion object {

        interface Callbacks {
            fun onCreateButtonClick()
            fun onCancelButtonClick()
        }

        fun newInstance(): CreateCollectionFragment {
            val fragment = CreateCollectionFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}