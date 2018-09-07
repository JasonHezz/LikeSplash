package com.github.jasonhezz.likesplash.ui.profile.likes

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.api.Status
import com.github.jasonhezz.likesplash.data.entities.Photo
import com.github.jasonhezz.likesplash.data.entities.User
import com.github.jasonhezz.likesplash.ui.epoxy.controller.PhotoPagedController
import com.github.jasonhezz.likesplash.ui.profile.ProfileActivity
import com.github.jasonhezz.likesplash.util.recyclerview.SlideInItemAnimator
import kotlinx.android.synthetic.main.fragment_like.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class UserLikeFragment : Fragment() {

    private val user by lazy { arguments?.getParcelable<User>(ARG_PARAM_USER) }
    private val viewModel by viewModel<UserLikeViewModel> { parametersOf(user?.username ?: "") }
    private val controller by inject<PhotoPagedController> {
        parametersOf(
                object : PhotoPagedController.AdapterCallbacks {
                    override fun onAvatarClick(view: View, user: User?) {
                        startActivity(
                                Intent(context, ProfileActivity::class.java).putExtra(
                                        ProfileActivity.ARG_PARAM_USER,
                                        user
                                )
                        )
                    }

                    override fun onPhotoClick(it: Photo) {

                    }
                }
        )
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? =
            inflater.inflate(R.layout.fragment_like, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initController()
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
        const val ARG_PARAM_USER = "userId"
        @JvmStatic
        fun newInstance(user: User?): UserLikeFragment {
            val fragment = UserLikeFragment()
            val args = Bundle()
            args.putParcelable(ARG_PARAM_USER, user)
            fragment.arguments = args
            return fragment
        }
    }
}