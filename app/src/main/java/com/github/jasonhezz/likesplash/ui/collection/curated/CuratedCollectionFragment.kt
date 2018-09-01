package com.github.jasonhezz.likesplash.ui.collection.curated

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.api.Resource
import com.github.jasonhezz.likesplash.data.api.Status
import com.github.jasonhezz.likesplash.data.entities.Collection
import com.github.jasonhezz.likesplash.ui.dialog.CoverFragment
import com.github.jasonhezz.likesplash.ui.epoxy.controller.CollectionPagedController
import com.github.jasonhezz.likesplash.util.recyclerview.SlideInItemAnimator
import kotlinx.android.synthetic.main.fragment_curated_collection.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

/**
 * Created by JavaCoder on 2017/12/7.
 */
class CuratedCollectionFragment : DialogFragment() {

    private val viewModel by viewModel<CuratedCollectionViewModel>()
    private val controller by inject<CollectionPagedController> {
        parametersOf(
                object : CollectionPagedController.AdapterCallbacks {
                    override fun onAvatarClick() {

                    }

                    override fun onCollectionClick(it: Collection) {
                        CoverFragment.newInstance(it.coverPhoto!!, arrayListOf(it)).show(childFragmentManager, "dialog")
                    }
                }
        )
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
        return inflater.inflate(R.layout.fragment_curated_collection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSwipeToRefresh()
        initController()
    }

    private fun initSwipeToRefresh() {
        viewModel.refreshState.observe(this, Observer {
            swipe_refresh.isRefreshing = it == Resource.INITIAL
        })
        swipe_refresh.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    private fun initController() {
        list.itemAnimator = SlideInItemAnimator()
        list.setController(controller)
        viewModel.collections.observe(this, Observer {
            controller.setList(it)
        })
        viewModel.networkState.observe(this, Observer {
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

        fun newInstance(): CuratedCollectionFragment {
            val fragment = CuratedCollectionFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
