package com.github.jasonhezz.likesplash.ui.search

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.SearchView
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.github.jasonhezz.likesplash.R
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : Fragment() {


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (arguments != null) {

    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? =// Inflate the layout for this fragment
      inflater.inflate(R.layout.fragment_search, container, false)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    search_view?.apply {
      imeOptions = imeOptions or EditorInfo.IME_ACTION_SEARCH or
          EditorInfo.IME_FLAG_NO_EXTRACT_UI or EditorInfo.IME_FLAG_NO_FULLSCREEN
      setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
          searchFor()
          return true
        }

        override fun onQueryTextChange(newText: String?): Boolean = true
      })
    }
    search_nav?.setOnClickListener {
      search_nav.foreground = null
      activity?.supportFinishAfterTransition()
    }

    scrim.setOnClickListener {
      search_nav.background = null
      activity?.supportFinishAfterTransition()
    }
  }

  private fun searchFor() {
    TransitionManager.beginDelayedTransition(container)
    progress.visibility = View.VISIBLE
  }


  companion object {

    fun newInstance(): SearchFragment {
      val fragment = SearchFragment()
      val args = Bundle()
      fragment.arguments = args
      return fragment
    }
  }
}