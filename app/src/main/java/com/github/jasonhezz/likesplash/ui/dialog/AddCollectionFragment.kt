package com.github.jasonhezz.likesplash.ui.dialog

import android.content.Context
import android.os.Bundle
import android.support.transition.Slide
import android.support.v4.app.DialogFragment
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.entities.Collection
import com.github.jasonhezz.likesplash.data.entities.Photo
import com.github.jasonhezz.likesplash.ui.controller.DialogCollectionController
import kotlinx.android.synthetic.main.dialog_add_collection.*

/**
 * Created by JasonHezz on 2018/1/15.

 */
class AddCollectionFragment : DialogFragment() {
    private var callback: Callbacks? = null
    private var data: List<Collection>? = null
    private var photo: Photo? = null
    private val controller = DialogCollectionController(
        object : DialogCollectionController.Companion.AdapterCallbacks {
            override fun onCollectionClick() {
                callback?.onCollectionClick()
            }

            override fun onCreateCollectionClick() {
                callback?.onCreateCollectionClick()
            }
        })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            photo = arguments?.getParcelable(ARG_PARAM_PHOTO)
            data = arguments?.getParcelableArrayList(ARG_PARAM_COLLECTION)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is Callbacks) callback = context
        if (parentFragment is Callbacks) callback = parentFragment as Callbacks
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_add_collection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv.setController(controller)
        data?.let { controller.collections = it }
    }

    companion object {

        private const val ARG_PARAM_COLLECTION = "collection"
        private const val ARG_PARAM_PHOTO = "photo"

        interface Callbacks {
            fun onCollectionClick()
            fun onCreateCollectionClick()
        }

        @JvmStatic
        fun newInstance(photo: Photo, data: ArrayList<Collection>? = null): AddCollectionFragment {
            val fragment = AddCollectionFragment()
            val args = Bundle()
            args.putSerializable(ARG_PARAM_PHOTO, photo)
            args.putParcelableArrayList(ARG_PARAM_COLLECTION, data)
            fragment.arguments = args
            return fragment
        }
    }
}