package com.github.jasonhezz.likesplash.ui.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.jasonhezz.likesplash.util.extension.replaceFragmentInActivity

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//    setContentView(R.layout.fragment_search)
        if (savedInstanceState == null) replaceFragmentInActivity(
                SearchFragment.newInstance(),
                android.R.id.content
        )
        //don't know why...if not,transition not working
        /*setEnterSharedElementCallback(object : SharedElementCallback() {

          override fun onSharedElementStart(sharedElementNames: MutableList<String>?,
              sharedElements: MutableList<View>?, sharedElementSnapshots: MutableList<View>?) {
            super.onSharedElementStart(sharedElementNames, sharedElements, sharedElementSnapshots)
            search_nav.setImageDrawable(DrawerArrowDrawable(this@SearchActivity).apply { progress = 1f })
          }
        })*/
    }
}
