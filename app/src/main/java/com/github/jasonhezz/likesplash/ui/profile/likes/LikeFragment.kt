package com.github.jasonhezz.likesplash.ui.profile.likes

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
import com.github.jasonhezz.likesplash.ui.controller.PhotoController
import com.github.jasonhezz.likesplash.util.ProgressTimeLatch
import kotlinx.android.synthetic.main.fragment_like.*

/**
 * Created by JavaCoder on 2017/6/28.
 */
class LikeFragment : Fragment() {

  private lateinit var viewModel: LikeViewModel
  private lateinit var swipeRefreshLatch: ProgressTimeLatch
  private var controller = PhotoController().apply { setFilterDuplicates(true) }

  private var user: User? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (arguments != null) {
      user = arguments?.getParcelable(ARG_PARAM_USER)
    }
    viewModel = ViewModelProviders.of(this).get(LikeViewModel::class.java)
    viewModel.fullRefresh(user)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? =
      inflater.inflate(R.layout.fragment_like, container, false)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    swipeRefreshLatch = ProgressTimeLatch { swipe_refresh?.isRefreshing = it }
    swipe_refresh.setOnRefreshListener { viewModel.fullRefresh(user) }

    rv.apply {
      adapter = controller.adapter
      addOnScrollListener(EndlessRecyclerViewScrollListener(rv.layoutManager, { _, _ ->
        viewModel.onListScrolledToEnd(user)
      }))
    }

    viewModel.apply {
      messages.observe(this@LikeFragment, Observer {
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

      photos.observe(this@LikeFragment, Observer {
        it?.let { controller.photos = it }
      })
    }
  }


  companion object {
    const val ARG_PARAM_USER = "userId"
    @JvmStatic
    fun newInstance(user: User?): LikeFragment {
      val fragment = LikeFragment()
      val args = Bundle()
      args.putParcelable(ARG_PARAM_USER, user)
      fragment.arguments = args
      return fragment
    }
  }
}