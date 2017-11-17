package com.github.jasonhezz.likesplash.ui

import android.app.SharedElementCallback
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.graphics.drawable.DrawerArrowDrawable
import android.view.View
import com.github.jasonhezz.likesplash.util.replaceFragmentInActivity
import kotlinx.android.synthetic.main.fragment_search.*

class SearchActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
//    setContentView(R.layout.fragment_search)

    if (savedInstanceState == null) replaceFragmentInActivity(SearchFragment.newInstance(),
        android.R.id.content)
    //don't know why...if not,transition not working
    search_nav?.setOnClickListener {}
    /*setEnterSharedElementCallback(object : SharedElementCallback() {

      override fun onSharedElementStart(sharedElementNames: MutableList<String>?,
          sharedElements: MutableList<View>?, sharedElementSnapshots: MutableList<View>?) {
        super.onSharedElementStart(sharedElementNames, sharedElements, sharedElementSnapshots)
        search_nav.setImageDrawable(DrawerArrowDrawable(this@SearchActivity).apply { progress = 1f })
      }
    })*/
  }


}
