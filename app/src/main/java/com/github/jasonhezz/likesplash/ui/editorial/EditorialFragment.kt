package com.github.jasonhezz.likesplash.ui.editorial

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
import com.github.jasonhezz.likesplash.data.entities.Photo
import com.github.jasonhezz.likesplash.data.entities.User
import com.github.jasonhezz.likesplash.ui.controller.PhotoPagedController
import com.github.jasonhezz.likesplash.ui.photodetail.PhotoDetailActivity
import com.github.jasonhezz.likesplash.ui.profile.ProfileActivity
import com.github.jasonhezz.likesplash.util.recyclerview.SlideInItemAnimator
import kotlinx.android.synthetic.main.fragment_timeline.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class EditorialFragment : Fragment() {

    private val model: EditorialViewModel by viewModel()
    private val controller = PhotoPagedController(
        object : PhotoPagedController.AdapterCallbacks {
            override fun onAvatarClick(user: User?) {
                startActivity(
                    Intent(context, ProfileActivity::class.java).putExtra(ProfileActivity.ARG_PARAM_USER, user)
                )
            }

            override fun onPhotoClick(it: Photo) {
                startActivity(
                    Intent(context, PhotoDetailActivity::class.java).putExtra("id", it.id)
                )
            }
        }).apply { setFilterDuplicates(true) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(
        R.layout.fragment_timeline, container,
        false
    )

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
        rv.itemAnimator = SlideInItemAnimator()
        rv.setController(controller)
        model.photos.observe(this, Observer {
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
        fun newInstance(): EditorialFragment {
            val fragment = EditorialFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
