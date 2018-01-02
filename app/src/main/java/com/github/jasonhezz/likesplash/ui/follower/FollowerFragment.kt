package com.github.jasonhezz.likesplash.ui.follower

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.User
import com.github.jasonhezz.likesplash.data.api.Resource
import com.github.jasonhezz.likesplash.data.api.Status
import com.github.jasonhezz.likesplash.repository.RepositoryFactory
import com.github.jasonhezz.likesplash.ui.controller.UserPagedController
import kotlinx.android.synthetic.main.fragment_following.*
import timber.log.Timber

/**
 * Created by JavaCoder on 2017/12/8.
 */
class FollowerFragment : Fragment() {

  private var user: User? = null

  private lateinit var model: FollowerViewModel
  private var controller = UserPagedController().apply { setFilterDuplicates(true) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (arguments != null) {
      user = arguments?.getParcelable(
          ARG_USER_NAME)
    }
    model = getViewModel()
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
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
    rv.adapter = controller.adapter
    model.follwers.observe(this, Observer {
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

  private fun getViewModel(): FollowerViewModel {
    return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
      override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val repo = RepositoryFactory.makeUserRepository()
        @Suppress("UNCHECKED_CAST")
        return FollowerViewModel(user?.username ?: "", repo) as T
      }
    })[FollowerViewModel::class.java]
  }

  companion object {
    private val ARG_USER_NAME = "user_name"

    fun newInstance(user: User?): FollowerFragment {
      val fragment = FollowerFragment()
      val args = Bundle()
      args.putParcelable(
          ARG_USER_NAME, user)
      fragment.arguments = args
      return fragment
    }
  }
}