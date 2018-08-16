package com.github.jasonhezz.likesplash.ui.trending

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.entities.Photo
import com.github.jasonhezz.likesplash.data.entities.User
import com.github.jasonhezz.likesplash.data.api.Resource
import com.github.jasonhezz.likesplash.data.api.Status
import com.github.jasonhezz.likesplash.ui.controller.PhotoPagedController
import com.github.jasonhezz.likesplash.ui.profile.ProfileActivity
import kotlinx.android.synthetic.main.fragment_trending.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class TrendingFragment : Fragment() {

    private val viewModel: TrendingViewModel by viewModel()
    private val controller = PhotoPagedController(
        object : PhotoPagedController.Companion.AdapterCallbacks {
            override fun onAvatarClick(user: User?) {
                startActivity(
                    Intent(context, ProfileActivity::class.java).putExtra(
                        ProfileActivity.ARG_PARAM_USER,
                        user
                    )
                )
            }

            override fun onPhotoClick(it: Photo) {
            }
        }).apply { setFilterDuplicates(true) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(
        R.layout.fragment_trending, container,
        false
    )

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
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
        rv.setController(controller)

        viewModel.photos.observe(this, Observer {
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
        fun newInstance(): TrendingFragment {
            val fragment = TrendingFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
