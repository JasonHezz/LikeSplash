package com.github.jasonhezz.likesplash.ui.collection

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
import com.github.jasonhezz.likesplash.data.api.Resource
import com.github.jasonhezz.likesplash.data.api.Status
import com.github.jasonhezz.likesplash.repository.RepositoryFactory
import com.github.jasonhezz.likesplash.ui.controller.CollectionPagedController
import com.github.jasonhezz.likesplash.ui.dialog.AddCollectionFragment
import kotlinx.android.synthetic.main.fragment_collection.*
import timber.log.Timber

/**
 * Created by JavaCoder on 2017/12/7.
 */
class CollectionFragment : Fragment() {

  private lateinit var model: CollectionViewModel
  private var controller: CollectionPagedController = CollectionPagedController()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (arguments != null) {

    }
    model = getViewModel()
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_collection, container, false)
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
    list.adapter = controller.adapter
    model.collections.observe(this, Observer {
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

    controller.onPhotoClick = {
      AddCollectionFragment.newInstance(it.cover_photo!!, arrayListOf(it)).show(
          childFragmentManager, null)
    }
  }

  private fun getViewModel(): CollectionViewModel {
    return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
      override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val repo = RepositoryFactory.makeCollectionRepository()
        @Suppress("UNCHECKED_CAST")
        return CollectionViewModel(repo) as T
      }
    })[CollectionViewModel::class.java]
  }

  companion object {

    fun newInstance(): CollectionFragment {
      val fragment = CollectionFragment()
      val args = Bundle()
      fragment.arguments = args
      return fragment
    }
  }
}// Required empty public constructor
