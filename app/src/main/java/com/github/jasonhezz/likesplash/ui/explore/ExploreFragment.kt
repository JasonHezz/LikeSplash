package com.github.jasonhezz.likesplash.ui.explore

import android.arch.lifecycle.Observer
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
import com.github.jasonhezz.likesplash.data.User
import com.github.jasonhezz.likesplash.ui.MainActivity
import com.github.jasonhezz.likesplash.ui.common.EndlessRecyclerViewScrollListener
import com.github.jasonhezz.likesplash.ui.controller.PhotoController
import com.github.jasonhezz.likesplash.ui.profile.ProfileActivity
import kotlinx.android.synthetic.main.fragment_explore.*

class ExploreFragment : Fragment() {

  private val items = listOf("Business", "Girl", "Nature", "Technology", "Food", "Travel", "Happy",
      "Cool")
  private lateinit var viewModel: ExploreViewModel
  private var controller = PhotoController().apply { setFilterDuplicates(true) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (arguments != null) {

    }
    viewModel = ViewModelProviders.of(this).get(ExploreViewModel::class.java)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_explore, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    toolbar.setNavigationOnClickListener { if (activity is MainActivity) (activity as MainActivity).openDrawer() }
    val adapter = ArrayAdapter(context, R.layout.toolbar_title, items)
    adapter.setDropDownViewResource(android.R.layout.simple_list_item_1)
    spinner_nav.adapter = adapter
    spinner_nav.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onNothingSelected(p0: AdapterView<*>?) {

      }

      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        viewModel.requery(items[position])
      }
    }
    rv.adapter = controller.adapter
    rv.addOnScrollListener(EndlessRecyclerViewScrollListener(rv.layoutManager, { _, _ ->
      viewModel.onListScrolledToEnd()
    }))
    controller.callback = object : PhotoController.AdapterCallbacks {
      override fun onAvatarClick(id: User?) {
        startActivity(
            Intent(context, ProfileActivity::class.java).putExtra(ProfileActivity.ARG_PARAM_USER,
                id))
      }

      override fun onPhotoClick() {

      }
    }
    viewModel.photos.observe(this, Observer {
      it?.let { controller.photos = it }
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
