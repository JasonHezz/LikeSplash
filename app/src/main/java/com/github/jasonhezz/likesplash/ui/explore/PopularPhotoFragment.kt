package com.github.jasonhezz.likesplash.ui.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.ui.epoxy.controller.PopularPhotoController
import kotlinx.android.synthetic.main.fragment_popular_photo.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class PopularPhotoFragment : androidx.fragment.app.Fragment() {

    private val viewModel by viewModel<PopularPhotoViewModel>()
    private val controller by inject<PopularPhotoController>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {

        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_popular_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv.setControllerAndBuildModels(controller)
        viewModel.businessPhoto.observe(this, Observer {
            it?.let { controller.businessPhoto = it }
        })
        viewModel.girlPhoto.observe(this, Observer {
            it?.let { controller.girlPhoto = it }
        })
        viewModel.naturePhoto.observe(this, Observer {
            it?.let { controller.naturePhoto = it }
        })
        viewModel.technologyPhoto.observe(this, Observer {
            it?.let { controller.technologyPhoto = it }
        })
        viewModel.foodPhoto.observe(this, Observer {
            it?.let { controller.foodPhoto = it }
        })
        viewModel.travelPhoto.observe(this, Observer {
            it?.let { controller.travelPhoto = it }
        })
        viewModel.happyPhoto.observe(this, Observer {
            it?.let { controller.happyPhoto = it }
        })
        viewModel.coolPhoto.observe(this, Observer {
            it?.let { controller.coolPhoto = it }
        })
    }

    companion object {
        fun newInstance(): PopularPhotoFragment {
            val fragment = PopularPhotoFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
