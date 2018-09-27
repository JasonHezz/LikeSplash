package com.github.jasonhezz.likesplash.ui.profile.following

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.api.Resource
import com.github.jasonhezz.likesplash.data.api.Status
import com.github.jasonhezz.likesplash.data.entities.User
import com.github.jasonhezz.likesplash.ui.epoxy.controller.UserPagedController
import kotlinx.android.synthetic.main.fragment_following.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber


/**
 * Created by JavaCoder on 2017/12/8.
 */
class FollowingFragment : androidx.fragment.app.Fragment() {

    private val user: User? by lazy { arguments?.getParcelable<User>(ARG_USER_NAME) }

    private val model by viewModel<FollowingViewModel> { parametersOf(user?.username ?: "") }
    private val controller = UserPagedController().apply { setFilterDuplicates(true) }


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_following, container, false)
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
        rv.setController(controller)
        model.follwers.observe(this, Observer {
            controller.submitList(it)
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
        private val ARG_USER_NAME = "user_name"

        fun newInstance(user: User?): FollowingFragment {
            val fragment = FollowingFragment()
            val args = Bundle()
            args.putParcelable(
                    ARG_USER_NAME, user
            )
            fragment.arguments = args
            return fragment
        }
    }
}