package com.github.jasonhezz.likesplash.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.SharedElementCallback
import android.support.v7.graphics.drawable.DrawerArrowDrawable
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
      savedInstanceState: Bundle?): View? {
    // Inflate the layout for this fragment
//    setEnterSharedElementCallback()
    setEnterSharedElementCallback(object : SharedElementCallback() {
      override fun onSharedElementEnd(sharedElementNames: MutableList<String>?,
          sharedElements: MutableList<View>?, sharedElementSnapshots: MutableList<View>?) {
        search_nav.setImageDrawable(DrawerArrowDrawable(context).apply { progress = 1f })
      }
    })
    return inflater.inflate(R.layout.fragment_search, container, false)
  }

  override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    search_view?.apply {
      imeOptions = imeOptions or EditorInfo.IME_ACTION_SEARCH or
          EditorInfo.IME_FLAG_NO_EXTRACT_UI or EditorInfo.IME_FLAG_NO_FULLSCREEN
    }
    search_nav?.setOnClickListener {
      activity.supportFinishAfterTransition()
    }
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