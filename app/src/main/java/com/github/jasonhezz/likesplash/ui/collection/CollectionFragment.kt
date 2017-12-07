package com.github.jasonhezz.likesplash.ui.collection

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.jasonhezz.likesplash.R

/**
 * Created by JavaCoder on 2017/12/7.
 */
class CollectionFragment : Fragment() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (arguments != null) {

    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_collection, container, false)
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
