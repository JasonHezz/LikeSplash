package com.github.jasonhezz.likesplash.ui.profile

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewCompat
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.User
import com.github.jasonhezz.likesplash.data.api.Status
import com.github.jasonhezz.likesplash.ui.profile.collections.CollectionFragment
import com.github.jasonhezz.likesplash.ui.profile.likes.LikeFragment
import com.github.jasonhezz.likesplash.ui.profile.photos.PhotoFragment
import com.github.jasonhezz.likesplash.util.extension.AppBarStateChangeListener
import com.github.jasonhezz.likesplash.util.extension.State
import com.github.jasonhezz.likesplash.util.extension.loadUrl
import com.github.jasonhezz.likesplash.util.extension.showSnackbar
import com.github.jasonhezz.unofficialsplash.home.TabFragmentAdapter
import kotlinx.android.synthetic.main.fragment_profile.*

/**
 * Created by JavaCoder on 2017/6/28.
 */
class ProfileFragment : Fragment() {

  private lateinit var viewModel: ProfileViewModel
  private lateinit var tabAdapter: TabFragmentAdapter
  private var user: User? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (arguments != null) {
      user = arguments?.getParcelable(ARG_PARAM_USER)
    }
    viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
    viewModel.loadUser(user)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? =
      inflater.inflate(R.layout.fragment_profile, container, false)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initToolbar()
    initViewPager()
    viewModel.liveUser.observe(this, Observer {
      user_avatar.loadUrl(it?.profile_image?.large)
      collapsing_toolbar.title = it?.name
      follow_btn.text = if (it?.followedByUser == true) "Following" else "Follow"
      bio.text = it?.bio
      tab_layout.getTabAt(0)?.text = String.format(getString(R.string.photos), it?.total_photos)
      tab_layout.getTabAt(1)?.text = String.format(getString(R.string.likes), it?.total_likes)
      tab_layout.getTabAt(2)?.text = String.format(getString(R.string.collections), it?.total_collections)
    })
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
    tabAdapter.addFragment(PhotoFragment.newInstance(user))
    tabAdapter.addFragment(LikeFragment.newInstance(user))
    tabAdapter.addFragment(CollectionFragment.newInstance(user))
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