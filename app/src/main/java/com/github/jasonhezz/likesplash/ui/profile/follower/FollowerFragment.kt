package com.github.jasonhezz.likesplash.ui.profile.follower

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
class FollowerFragment : Fragment() {

    private val user: User? by lazy { arguments?.getParcelable<User>(ARG_USER_NAME) }

    private val viewModel by viewModel<FollowerViewModel> { parametersOf(user?.username!!) }
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
        viewModel.refreshState.observe(this, Observer {
            swipe_refresh.isRefreshing = it == Resource.INITIAL
        })
        swipe_refresh.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    private fun initController() {
        rv.setController(controller)
        viewModel.followers.observe(this, Observer {
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
        private val ARG_USER_NAME = "user_name"

        fun newInstance(user: User?): FollowerFragment {
            val fragment = FollowerFragment()
            val args = Bundle()
            args.putParcelable(
                    ARG_USER_NAME, user
            )
            fragment.arguments = args
            return fragment
        }
    }
}