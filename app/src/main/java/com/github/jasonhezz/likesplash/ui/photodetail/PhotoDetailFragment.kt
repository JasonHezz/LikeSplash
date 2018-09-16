package com.github.jasonhezz.likesplash.ui.photodetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.ui.epoxy.controller.PhotoDetailController
import kotlinx.android.synthetic.main.fragment_photo_detail.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PhotoDetailFragment : Fragment() {

    private val viewModel by viewModel<PhotoDetailViewModel> {
        parametersOf(arguments?.get("id") ?: "")
    }
    private val controller by inject<PhotoDetailController>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_photo_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv.setController(controller)
        viewModel.result.observe(this, Observer {
            controller.data = it
        })
    }

    companion object {
        fun newInstance(photoId: String): PhotoDetailFragment {
            val fragment = PhotoDetailFragment()
            val args = bundleOf("id" to photoId)
            fragment.arguments = args
            return fragment
        }
    }
}
