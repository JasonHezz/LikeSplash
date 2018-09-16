package com.github.jasonhezz.likesplash.ui.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.jasonhezz.likesplash.data.entities.User
import com.github.jasonhezz.likesplash.util.extension.replaceFragmentInActivity

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            val user = intent.getParcelableExtra<User>(ARG_PARAM_USER)
            replaceFragmentInActivity(ProfileFragment.newInstance(user), android.R.id.content)
        }
    }

    companion object {
        const val ARG_PARAM_USER = "userId"
    }
}
