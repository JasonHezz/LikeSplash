package com.github.jasonhezz.likesplash.ui.explore

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_explore.*

class ExploreFragment : Fragment() {

  val items = listOf("Business", "Girl", "Nature", "Technology", "Food", "Travel", "Happy", "Cool")

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (arguments != null) {

    }
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
