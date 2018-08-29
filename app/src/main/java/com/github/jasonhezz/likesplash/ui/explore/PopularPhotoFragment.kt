package com.github.jasonhezz.likesplash.ui.explore

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.ui.epoxy.controller.PopularPhotoController
import kotlinx.android.synthetic.main.fragment_popular_photo.*
import org.koin.android.viewmodel.ext.android.viewModel

class PopularPhotoFragment : Fragment() {

    private val model: PopularPhotoViewModel by viewModel()
    private val controller by lazy { PopularPhotoController(context!!) }

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
        model.businessPhoto.observe(this, Observer {
            it?.let { controller.businessPhoto = it }
        })
        model.girlPhoto.observe(this, Observer {
            it?.let { controller.girlPhoto = it }
        })
        model.naturePhoto.observe(this, Observer {
            it?.let { controller.naturePhoto = it }
        })
        model.technologyPhoto.observe(this, Observer {
            it?.let { controller.technologyPhoto = it }
        })
        model.foodPhoto.observe(this, Observer {
            it?.let { controller.foodPhoto = it }
        })
        model.travelPhoto.observe(this, Observer {
            it?.let { controller.travelPhoto = it }
        })
        model.happyPhoto.observe(this, Observer {
            it?.let { controller.happyPhoto = it }
        })
        model.coolPhoto.observe(this, Observer {
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
