package com.github.jasonhezz.likesplash.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.jasonhezz.likesplash.R


/**
 * Created by JavaCoder on 2017/6/28.
 */
class EditProfileFragment : Fragment() {

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_edit, container, false)
  }


  companion object {
    @JvmStatic
    fun newInstance() = EditProfileFragment()
  }
}