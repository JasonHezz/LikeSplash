package com.github.jasonhezz.likesplash.ui.user

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.User
import com.github.jasonhezz.likesplash.data.api.Status
import com.github.jasonhezz.likesplash.ui.common.EndlessRecyclerViewScrollListener
import com.github.jasonhezz.likesplash.ui.controller.UserController
import com.github.jasonhezz.likesplash.util.ProgressTimeLatch
import kotlinx.android.synthetic.main.fragment_following.*

/**
 * Created by JavaCoder on 2017/12/8.
 */
class FollowingFragment : Fragment() {

  private var user: User? = null

  private lateinit var viewModel: FollowingViewModel
  private lateinit var swipeRefreshLatch: ProgressTimeLatch
  private var controller = UserController().apply { setFilterDuplicates(true) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (arguments != null) {
      user = arguments?.getParcelable(ARG_USER_NAME)
    }
    viewModel = ViewModelProviders.of(this).get(FollowingViewModel::class.java)
    viewModel.fullRefresh(user)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_following, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    swipeRefreshLatch = ProgressTimeLatch { refreshLayout?.isRefreshing = it }
    refreshLayout.setOnRefreshListener { viewModel.fullRefresh(user) }

    rv.apply {
      adapter = controller.adapter
      addOnScrollListener(EndlessRecyclerViewScrollListener(rv.layoutManager, { _, _ ->
        viewModel.onListScrolledToEnd(user)
      }))
    }

    viewModel.apply {
      messages.observe(this@FollowingFragment, Observer {
        when (it?.status) {
          Status.SUCCESS -> {
            swipeRefreshLatch.refreshing = false
            controller.isLoading = false
          }
          Status.ERROR -> {
            swipeRefreshLatch.refreshing = false
            controller.isLoading = false
            Snackbar.make(rv, it.message ?: "UNKNOW ERROR", Snackbar.LENGTH_SHORT).show()
          }
          Status.REFRESHING -> swipeRefreshLatch.refreshing = true
          Status.LOADING_MORE -> controller.isLoading = true
        }
      })

      photos.observe(this@FollowingFragment, Observer {
        it?.let { controller.users = it }
      })
    }
  }

  companion object {
    private val ARG_USER_NAME = "user_name"

    fun newInstance(user: User?): FollowingFragment {
      val fragment = FollowingFragment()
      val args = Bundle()
      args.putParcelable(ARG_USER_NAME, user)
      fragment.arguments = args
      return fragment
    }
  }
}