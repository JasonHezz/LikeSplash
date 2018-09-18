package com.github.jasonhezz.likesplash.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.core.os.bundleOf
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.entities.Collection
import com.github.jasonhezz.likesplash.data.entities.Photo
import com.github.jasonhezz.likesplash.util.glide.GlideApp
import kotlinx.android.synthetic.main.dialog_add_cover.*

/**
 * Created by JavaCoder on 2018/1/18.
 */
class CoverFragment : AppCompatDialogFragment(), AddCollectionFragment.Callbacks,
        CreateCollectionFragment.Callbacks, CloseCallback {

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
        return inflater.inflate(R.layout.dialog_add_cover, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return CustomDimDialog(context, resources.getBoolean(R.bool.isCoverDialogFullScreen))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cover?.let {
            GlideApp
                    .with(view)
                    .load(photo?.urls?.regular)
                    .into(it)
        }
        if (savedInstanceState == null) {
            childFragmentManager.beginTransaction()
                    .add(
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
                R.anim.enter_from_right,
                R.anim.exit_to_left,
                R.anim.enter_from_left,
                R.anim.exit_to_right
        ).replace(
                R.id.action_panel,
                CreateCollectionFragment.newInstance()
        ).addToBackStack(null).commit()
    }

    override fun close() {
        dismiss()
    }

    companion object {
        private const val ARG_PARAM_COLLECTION = "collection"
        private const val ARG_PARAM_PHOTO = "photo"

        @JvmStatic
        fun newInstance(photo: Photo, data: ArrayList<Collection>? = null): CoverFragment {
            val fragment = CoverFragment()
            val args = bundleOf(ARG_PARAM_PHOTO to photo, ARG_PARAM_COLLECTION to data)
            fragment.arguments = args
            return fragment
        }
    }
}