package com.github.jasonhezz.likesplash.ui.profile

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewCompat
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.api.Status
import com.github.jasonhezz.likesplash.data.entities.User
import com.github.jasonhezz.likesplash.ui.profile.collections.UserCollectionFragment
import com.github.jasonhezz.likesplash.ui.profile.likes.UserLikeFragment
import com.github.jasonhezz.likesplash.ui.profile.photos.UserPhotoFragment
import com.github.jasonhezz.likesplash.util.adapter.TabFragmentAdapter
import com.github.jasonhezz.likesplash.util.extension.AppBarStateChangeListener
import com.github.jasonhezz.likesplash.util.extension.State
import com.github.jasonhezz.likesplash.util.extension.showSnackbar
import com.github.jasonhezz.likesplash.util.glide.GlideApp
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by JavaCoder on 2017/6/28.
 */
class ProfileFragment : Fragment() {

    private val viewModel: ProfileViewModel by viewModel()
    private lateinit var tabAdapter: TabFragmentAdapter
    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            user = arguments?.getParcelable(ARG_PARAM_USER)
        }
        viewModel.loadUser(user)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_profile, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initViewPager()
        viewModel.liveUser.observe(this, Observer {
            GlideApp
                .with(this)
                .load(user?.profile_image?.medium)
                .into(user_avatar)
            collapsing_toolbar.title = it?.name
            follow_btn.text = if (it?.followedByUser == true) "Following" else "Follow"
            bio.text = it?.bio
            tab_layout.getTabAt(0)?.text = String.format(getString(R.string.photos), it?.totalPhotos)
            tab_layout.getTabAt(1)?.text = String.format(getString(R.string.likes), it?.totalLikes)
            tab_layout.getTabAt(2)?.text = String.format(
                getString(R.string.collections),
                it?.totalCollections
            )
        })

        follow_btn.setOnClickListener {
            TransitionManager.beginDelayedTransition(profile_coordinator_layout)
            follow_btn.isActivated = !follow_btn.isActivated
            follow_btn.text = if (follow_btn.isActivated) "Following" else "Follow"
        }
        viewModel.messages.observe(this, Observer {
            when (it?.status) {
                Status.REFRESHING -> {

                }
                Status.ERROR -> {
                    view_pager.showSnackbar(it.message ?: "")
                }
                Status.SUCCESS -> {
                }
                else -> {
                }
            }
        })
    }

    private fun initToolbar() {
        profile_toolbar?.apply {
            setNavigationOnClickListener { activity?.supportFinishAfterTransition() }
        }
        app_bar_layout.addOnOffsetChangedListener(object : AppBarStateChangeListener() {
            override fun onStateChanged(appBarLayout: AppBarLayout, state: State) {
            }

            override fun onOffsetChanged(state: State, fraction: Float) {
                ViewCompat.setTranslationZ(app_bar_layout, 6f * fraction)
            }
        })
    }

    private fun initViewPager() {
        tabAdapter = TabFragmentAdapter(childFragmentManager)
        tabAdapter.addFragment(UserPhotoFragment.newInstance(user))
        tabAdapter.addFragment(UserLikeFragment.newInstance(user))
        tabAdapter.addFragment(UserCollectionFragment.newInstance(user))
        view_pager.apply {
            adapter = tabAdapter
            offscreenPageLimit = 3
            addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))
        }
        tab_layout?.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(view_pager))
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                activity?.supportFinishAfterTransition()
            }
            else -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val ARG_PARAM_USER = "userId"
        @JvmStatic
        fun newInstance(user: User?): ProfileFragment {
            val fragment = ProfileFragment()
            val args = Bundle()
            args.putParcelable(ARG_PARAM_USER, user)
            fragment.arguments = args
            return fragment
        }
    }
}