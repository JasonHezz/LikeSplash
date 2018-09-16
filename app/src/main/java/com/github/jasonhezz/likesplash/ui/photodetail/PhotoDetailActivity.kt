package com.github.jasonhezz.likesplash.ui.photodetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.jasonhezz.likesplash.util.extension.replaceFragmentInActivity

class PhotoDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            replaceFragmentInActivity(PhotoDetailFragment.newInstance(intent.getStringExtra("id")), android.R.id.content)
        }
    }
}
