package com.github.jasonhezz.likesplash.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.ui.Adapter
import com.github.jasonhezz.likesplash.ui.ListFragment

/**
 * Created by JasonHezz on 2018/1/15.

 */
class AddCollectionFragment : DialogFragment() {
    val adpater = Adapter(ListFragment.generateItemModelList())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
//        rv.adapter = adpater
    }


    companion object {
        @JvmStatic
        fun newInstance(): AddCollectionFragment {
            val fragment = AddCollectionFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}