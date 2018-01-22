package com.github.jasonhezz.likesplash.ui.explore

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
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.Photo
import com.github.jasonhezz.likesplash.data.User
import com.github.jasonhezz.likesplash.data.api.Status
import com.github.jasonhezz.likesplash.repository.RepositoryFactory
import com.github.jasonhezz.likesplash.ui.MainActivity
import com.github.jasonhezz.likesplash.ui.controller.PhotoPagedController
import com.github.jasonhezz.likesplash.ui.profile.ProfileActivity
import kotlinx.android.synthetic.main.fragment_explore.*
import timber.log.Timber

class ExploreFragment : Fragment() {

  private val items = listOf("Business", "Girl", "Nature", "Technology", "Food", "Travel", "Happy",
      "Cool")
  private lateinit var model: ExploreViewModel
  private var controller = PhotoPagedController(
      object : PhotoPagedController.Companion.AdapterCallbacks {
        override fun onAvatarClick(user: User?) {
          startActivity(
              Intent(context, ProfileActivity::class.java).putExtra(ProfileActivity.ARG_PARAM_USER,
                  user))
        }

        override fun onPhotoClick(it: Photo) {

        }
      }).apply { setFilterDuplicates(true) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (arguments != null) {

    }
    model = getViewModel()
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_explore, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initToolbar()
    initController()
  }

  private fun initToolbar() {
    toolbar.setNavigationOnClickListener { if (activity is MainActivity) (activity as MainActivity).openDrawer() }
    spinner_nav.apply {
      adapter = ArrayAdapter(context, R.layout.toolbar_title, items).apply {
        setDropDownViewResource(android.R.layout.simple_list_item_1)
      }
      onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(p0: AdapterView<*>?) {

        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
          model.query = items[position]
        }
      }
    }
    spinner_nav.setSelection(0)
  }

  private fun getViewModel(): ExploreViewModel {
    return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
      override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val repo = RepositoryFactory.makeSearchRepository()
        @Suppress("UNCHECKED_CAST")
        return ExploreViewModel(repo) as T
      }
    })[ExploreViewModel::class.java]
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

  companion object {
    fun newInstance(): ExploreFragment {
      val fragment = ExploreFragment()
      val args = Bundle()
      fragment.arguments = args
      return fragment
    }
  }
}
