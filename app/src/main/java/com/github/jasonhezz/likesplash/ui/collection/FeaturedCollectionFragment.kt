package com.github.jasonhezz.likesplash.ui.collection

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.api.Resource
import com.github.jasonhezz.likesplash.data.api.Status
import com.github.jasonhezz.likesplash.data.entities.Collection
import com.github.jasonhezz.likesplash.ui.controller.PreviewCollectionPagedController
import kotlinx.android.synthetic.main.fragment_featured_collection.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

/**
 * Created by JavaCoder on 2017/12/7.
 */
class FeaturedCollectionFragment : Fragment() {

    private val model: FeaturedCollectionViewModel by viewModel()
    private val controller =
        PreviewCollectionPagedController(object : PreviewCollectionPagedController.AdapterCallbacks {
            override fun onCollectionClick(it: Collection) {
                startActivity(Intent(context, CollectionDetailActivity::class.java).apply {
                    putExtra("collection", it)
                })
            }
        }).apply { setFilterDuplicates(true) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_featured_collection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSwipeToRefresh()
        initController()
    }

    private fun initSwipeToRefresh() {
        model.refreshState.observe(this, Observer {
            swipe_refresh.isRefreshing = it == Resource.INITIAL
        })
        swipe_refresh.setOnRefreshListener {
            model.refresh()
        }
    }

    private fun initController() {
        list.setController(controller)
        model.collections.observe(this, Observer {
            controller.setList(it)
        })
        model.networkState.observe(this, Observer {
            when (it?.status) {
                Status.LOADING_MORE -> {
                    controller.isLoading = true
                }
                Status.SUCCESS -> {
                    controller.isLoading = false
                }
                Status.ERROR -> {
                    Timber.e(it.message)
                }
                else -> {
                }
            }
        })
    }

    companion object {

        fun newInstance(): FeaturedCollectionFragment {
            val fragment = FeaturedCollectionFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
