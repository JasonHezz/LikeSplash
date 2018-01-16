package com.github.jasonhezz.likesplash.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.jasonhezz.likesplash.R

/**
 * Created by JasonHezz on 2018/1/15.

 */
class AddCollectionFragment : DialogFragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    dialog.window.decorView.setPadding(0, 0, 0, 0)
    dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//    dialog.window.attributes.windowAnimations = R.style.WindowAnimation_Grow
    dialog.window.setLayout(900, 560)
    return inflater.inflate(R.layout.dialog_search, container, false)
  }

  companion object {
    @JvmStatic
    fun newInstance(): AddCollectionFragment {
      val fragment = AddCollectionFragment()
      val args = Bundle()
      fragment.arguments = args
      return fragment
    }
  }
}