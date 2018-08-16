package com.github.jasonhezz.likesplash.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.entities.Collection
import com.github.jasonhezz.likesplash.data.entities.Photo
import com.github.jasonhezz.likesplash.util.glide.GlideApp
import kotlinx.android.synthetic.main.dialog_add_cover.*

/**
 * Created by JavaCoder on 2018/1/18.
 */
class CoverFragment : DialogFragment(), AddCollectionFragment.Companion.Callbacks,
    CreateCollectionFragment.Companion.Callbacks {

    private var photo: Photo? = null
    private var data: List<Collection>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            photo = arguments?.getParcelable(ARG_PARAM_PHOTO)
            data = arguments?.getParcelableArrayList(ARG_PARAM_COLLECTION)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return inflater.inflate(R.layout.dialog_add_cover, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GlideApp.with(view.context).load(photo?.urls?.regular)
            .into(cover)
        if (savedInstanceState == null) {
            childFragmentManager.beginTransaction().add(
                R.id.action_panel,
                AddCollectionFragment.newInstance(photo!!, data as ArrayList<Collection>)
            ).commit()
        }
    }

    override fun onCollectionClick() {
    }

    override fun onCreateButtonClick() {
    }

    override fun onCancelButtonClick() {
        childFragmentManager.popBackStack()
    }

    override fun onCreateCollectionClick() {
        childFragmentManager.beginTransaction().setCustomAnimations(
            R.anim.slide_in_right,
            0, R.anim.slide_in_left, 0
        ).replace(
            R.id.action_panel,
            CreateCollectionFragment.newInstance()
        ).addToBackStack(null).commit()
    }

    companion object {

        private const val ARG_PARAM_COLLECTION = "collection"
        private const val ARG_PARAM_PHOTO = "photo"

        @JvmStatic
        fun newInstance(photo: Photo, data: ArrayList<Collection>? = null): CoverFragment {
            val fragment = CoverFragment()
            val args = Bundle()
            args.putSerializable(ARG_PARAM_PHOTO, photo)
            args.putParcelableArrayList(ARG_PARAM_COLLECTION, data)
            fragment.arguments = args
            return fragment
        }
    }
}