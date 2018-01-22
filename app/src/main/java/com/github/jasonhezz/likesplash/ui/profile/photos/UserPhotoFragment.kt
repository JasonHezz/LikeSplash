package com.github.jasonhezz.likesplash.ui.profile.photos

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
import com.github.jasonhezz.likesplash.data.Photo
import com.github.jasonhezz.likesplash.data.User
import com.github.jasonhezz.likesplash.data.api.Resource
import com.github.jasonhezz.likesplash.data.api.Status
import com.github.jasonhezz.likesplash.repository.RepositoryFactory
import com.github.jasonhezz.likesplash.ui.controller.PhotoPagedController
import kotlinx.android.synthetic.main.fragment_like.*
import timber.log.Timber

class UserPhotoFragment : Fragment() {

  private lateinit var model: UserPhotoViewModel
  private var controller = PhotoPagedController(
      object : PhotoPagedController.Companion.AdapterCallbacks {
        override fun onAvatarClick(user: User?) {

        }

        override fun onPhotoClick(it: Photo) {

        }
      }).apply { setFilterDuplicates(true) }

  private var user: User? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (arguments != null) {
      user = arguments?.getParcelable(ARG_PARAM_USER)
    }
    model = getViewModel()
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
    model.refreshState.observe(this, Observer<Resource?> {
      swipe_refresh.isRefreshing = it == Resource.INITIAL
    })
    swipe_refresh.setOnRefreshListener {
      model.refresh()
    }
  }

  private fun initController() {
    rv.adapter = controller.adapter
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

  private fun getViewModel(): UserPhotoViewModel {
    return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
      override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val repo = RepositoryFactory.makeUserRepository()
        @Suppress("UNCHECKED_CAST")
        return UserPhotoViewModel(user?.username ?: "", repo) as T
      }
    })[UserPhotoViewModel::class.java]
  }

  companion object {
    const val ARG_PARAM_USER = "userId"
    @JvmStatic
    fun newInstance(user: User?): UserPhotoFragment {
      val fragment = UserPhotoFragment()
      val args = Bundle()
      args.putParcelable(ARG_PARAM_USER, user)
      fragment.arguments = args
      return fragment
    }
  }
}