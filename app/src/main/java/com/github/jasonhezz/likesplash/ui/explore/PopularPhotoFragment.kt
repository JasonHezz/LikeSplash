package com.github.jasonhezz.likesplash.ui.explore

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.ui.controller.PopularPhotoController
import kotlinx.android.synthetic.main.fragment_popular_photo.*

class PopularPhotoFragment : Fragment() {

  private val controller by lazy { PopularPhotoController(context!!) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (arguments != null) {

    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_popular_photo, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    rv.setControllerAndBuildModels(controller)
//    rv.addItemDecoration(PopularPhotoDecoration(context!!))
  }

  companion object {
    fun newInstance(): PopularPhotoFragment {
      val fragment = PopularPhotoFragment()
      val args = Bundle()
      fragment.arguments = args
      return fragment
    }
  }
}// Required empty public constructor
