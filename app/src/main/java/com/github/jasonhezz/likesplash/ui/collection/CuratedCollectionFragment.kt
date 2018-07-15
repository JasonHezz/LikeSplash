package com.github.jasonhezz.likesplash.ui.collection

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.Collection
import com.github.jasonhezz.likesplash.data.api.Resource
import com.github.jasonhezz.likesplash.data.api.Status
import com.github.jasonhezz.likesplash.repository.RepositoryFactory
import com.github.jasonhezz.likesplash.ui.controller.CollectionPagedController
import kotlinx.android.synthetic.main.fragment_curated_collection.*
import timber.log.Timber

/**
 * Created by JavaCoder on 2017/12/7.
 */
class CuratedCollectionFragment : DialogFragment() {

    private lateinit var model: CuratedCollectionViewModel
    private var controller: CollectionPagedController = CollectionPagedController(
        object : CollectionPagedController.Companion.AdapterCallbacks {
            override fun onAvatarClick() {
            }

            override fun onCollectionClick(it: Collection) {

                startActivity(Intent(context, CollectionDetailActivity::class.java).apply {
                    putExtra("collection", it)
                })
            }
        })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {

        }
        model = getViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_curated_collection, container, false)
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
        list.adapter = controller.adapter
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

    private fun getViewModel(): CuratedCollectionViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val repo = RepositoryFactory.makeCollectionRepository()
                @Suppress("UNCHECKED_CAST")
                return CuratedCollectionViewModel(repo) as T
            }
        })[CuratedCollectionViewModel::class.java]
    }

    companion object {

        fun newInstance(): CuratedCollectionFragment {
            val fragment = CuratedCollectionFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
