package com.github.jasonhezz.likesplash.ui.wallpaper

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.entities.Wallpaper
import com.github.jasonhezz.likesplash.ui.MainActivity
import com.github.jasonhezz.likesplash.ui.search.SearchActivity
import com.github.jasonhezz.likesplash.util.adapter.TabFragmentAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_tab_wallpaper.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class WallpaperTabFragment : Fragment() {

    private val viewModel by viewModel<WallpaperTabViewModel>()
    private val tabAdapter by lazy { TabFragmentAdapter(childFragmentManager) }


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab_wallpaper, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.result.observe(this, Observer {
            it?.let { list: List<Wallpaper> ->
                list.forEach { wallpaper ->
                    tab_layout.addTab(tab_layout.newTab().setText(wallpaper.wallpaperKey.capitalize()))
                    tabAdapter.addFragment(WallpaperFragment.newInstance(wallpaper))
                }
                view_pager.apply {
                    adapter = tabAdapter
                    addOnPageChangeListener(com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener(tab_layout))
                }
                tab_layout?.addOnTabSelectedListener(com.google.android.material.tabs.TabLayout.ViewPagerOnTabSelectedListener(view_pager))
            }
        })
        toolbar.run {
            inflateMenu(R.menu.menu_search)
            setNavigationOnClickListener { if (activity is MainActivity) (activity as MainActivity).openDrawer() }
            setOnMenuItemClickListener {
                startActivity(Intent(context, SearchActivity::class.java))
                true
            }
        }
    }

    companion object {
        fun newInstance(): WallpaperTabFragment {
            val fragment = WallpaperTabFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
