package com.github.jasonhezz.likesplash.ui.trending

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.User
import com.github.jasonhezz.likesplash.data.api.Resource
import com.github.jasonhezz.likesplash.data.api.Status
import com.github.jasonhezz.likesplash.repository.RepostioryFactory
import com.github.jasonhezz.likesplash.ui.controller.PhotoPagedController
import com.github.jasonhezz.likesplash.ui.profile.ProfileActivity
import kotlinx.android.synthetic.main.fragment_trending.*
import timber.log.Timber


class TrendingFragment : Fragment() {

  private lateinit var viewModel: TrendingViewModel
  private var controller = PhotoPagedController().apply { setFilterDuplicates(true) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewModel = getViewModel()
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_trending, container,
      false)

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

  private fun getViewModel(): TrendingViewModel {
    return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
      override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val repo = RepostioryFactory.makeTrendingRepository()
        @Suppress("UNCHECKED_CAST")
        return TrendingViewModel(repo) as T
      }
    })[TrendingViewModel::class.java]
  }

  private fun initController() {
    rv.adapter = controller.adapter

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

    controller.callback = object : PhotoPagedController.AdapterCallbacks {
      override fun onAvatarClick(id: User?) {
        startActivity(
            Intent(context, ProfileActivity::class.java).putExtra(ProfileActivity.ARG_PARAM_USER,
                id))
      }

      override fun onPhotoClick() {

      }
    }
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
