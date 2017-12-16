package com.github.jasonhezz.likesplash.ui.timeline

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
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
import com.github.jasonhezz.likesplash.ui.controller.PhotoController
import com.github.jasonhezz.likesplash.ui.profile.ProfileActivity
import com.github.jasonhezz.likesplash.util.ProgressTimeLatch
import kotlinx.android.synthetic.main.fragment_timeline.*


class TimelineFragment : Fragment() {

  private lateinit var viewModel: TimelineViewModel
  private lateinit var swipeRefreshLatch: ProgressTimeLatch
  private var controller = PhotoController().apply { setFilterDuplicates(true) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewModel = ViewModelProviders.of(this).get(TimelineViewModel::class.java)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_timeline, container,
      false)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    swipeRefreshLatch = ProgressTimeLatch {
      refreshLayout?.isRefreshing = it
    }
    refreshLayout.setOnRefreshListener(viewModel::fullRefresh)

    rv.apply {
      adapter = controller.adapter
      addOnScrollListener(EndlessRecyclerViewScrollListener(rv.layoutManager, { _, _ ->
        viewModel.onListScrolledToEnd()
      }))
    }

    viewModel.apply {
      messages.observe(this@TimelineFragment, Observer {
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
      photos.observe(this@TimelineFragment, Observer {
        it?.let { controller.photos = it }
      })
    }

    controller.callback = object : PhotoController.AdapterCallbacks {
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
    fun newInstance(): TimelineFragment {
      val fragment = TimelineFragment()
      val args = Bundle()
      fragment.arguments = args
      return fragment
    }
  }
}
