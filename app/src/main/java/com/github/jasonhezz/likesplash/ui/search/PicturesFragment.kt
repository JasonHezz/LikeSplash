package com.github.jasonhezz.likesplash.ui.search

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
import com.github.jasonhezz.likesplash.ui.epoxy.controller.PhotoPagedController
import com.github.jasonhezz.likesplash.ui.photodetail.PhotoDetailActivity
import com.github.jasonhezz.likesplash.ui.profile.ProfileActivity
import com.github.jasonhezz.likesplash.util.recyclerview.SlideInItemAnimator
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import timber.log.Timber
import android.arch.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_collections.*

class PicturesFragment : Fragment(){

    private val viewModel by inject<PicturesViewModel>()
    private val query by lazy { arguments?.getString(KEY_PARAM) }
    private val controller by inject<PhotoPagedController> {
        parametersOf(
                object : PhotoPagedController.AdapterCallbacks {
                    override fun onAvatarClick(user: User?) {
                        startActivity(Intent(context, ProfileActivity::class.java)
                                .putExtra(ProfileActivity.ARG_PARAM_USER, user))
                    }

                    override fun onPhotoClick(it: Photo) {
                        startActivity(Intent(context, PhotoDetailActivity::class.java)
                                .putExtra("id", it.id))
                    }
                }
        )
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_pictures, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.querySubmit.value = query

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
        rv.itemAnimator = SlideInItemAnimator()
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
        const val KEY_PARAM = "key"

        fun newInstance(query : String) : PicturesFragment{
            val fragment = PicturesFragment()
            val args = Bundle()
            args.putString(KEY_PARAM , query)
            fragment.arguments = args
            return fragment
        }
    }
}