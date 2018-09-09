package com.github.jasonhezz.likesplash.ui.explore

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.ui.epoxy.controller.PopularPhotoController
import com.github.jasonhezz.likesplash.ui.search.SearchActivity
import kotlinx.android.synthetic.main.fragment_popular_photo.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PopularPhotoFragment : Fragment() {

    private val viewModel by viewModel<PopularPhotoViewModel>()
    private val controller by inject<PopularPhotoController>{
        parametersOf(
                object : PopularPhotoController.AdapterCallbacks{
                    override fun onMore(it: String) {
                        startActivity(Intent(context, SearchActivity::class.java).apply {
                            putExtra("key", it)
                        })
                    }
                })
    }

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
