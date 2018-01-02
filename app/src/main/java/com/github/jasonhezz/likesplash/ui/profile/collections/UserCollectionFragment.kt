package com.github.jasonhezz.likesplash.ui.profile.collections

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
import com.github.jasonhezz.likesplash.ui.controller.CollectionPagedController
import kotlinx.android.synthetic.main.fragment_like.*
import timber.log.Timber

/**
 * Created by JavaCoder on 2017/6/28.
 */
class UserCollectionFragment : Fragment() {

  private lateinit var mModelUser: UserCollectionViewModel
  private var controller = CollectionPagedController().apply { setFilterDuplicates(true) }

  private var user: User? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (arguments != null) {
      user = arguments?.getParcelable(ARG_PARAM_USER)
    }
    mModelUser = getViewModel()
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? =
      inflater.inflate(R.layout.fragment_like, container, false)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initSwipeToRefresh()
    initController()
  }

  private fun initSwipeToRefresh() {
    mModelUser.refreshState.observe(this, Observer {
      swipe_refresh.isRefreshing = it == Resource.INITIAL
    })
    swipe_refresh.setOnRefreshListener {
      mModelUser.refresh()
    }
  }

  private fun initController() {
    rv.adapter = controller.adapter
    mModelUser.collections.observe(this, Observer {
      controller.setList(it)
    })
    mModelUser.networkState.observe(this, Observer {
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

  private fun getViewModel(): UserCollectionViewModel {
    return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
      override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val repo = RepositoryFactory.makeUserRepository()
        @Suppress("UNCHECKED_CAST")
        return UserCollectionViewModel(user?.name ?: "", repo) as T
      }
    })[UserCollectionViewModel::class.java]
  }

  companion object {
    const val ARG_PARAM_USER = "userId"
    @JvmStatic
    fun newInstance(user: User?): UserCollectionFragment {
      val fragment = UserCollectionFragment()
      val args = Bundle()
      args.putParcelable(ARG_PARAM_USER, user)
      fragment.arguments = args
      return fragment
    }
  }
}