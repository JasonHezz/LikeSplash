package com.github.jasonhezz.likesplash.ui.wallpaper

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.entities.Wallpaper
import com.github.jasonhezz.likesplash.ui.MainActivity
import com.github.jasonhezz.likesplash.ui.search.SearchActivity
import kotlinx.android.synthetic.main.fragment_wallpaper.*
import org.koin.android.viewmodel.ext.android.viewModel


class WallpaperTabFragment : Fragment() {
    private val viewModel by viewModel<WallpaperTabViewModel>()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_wallpaper, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.result.observe(this, Observer {
            it?.let { list: List<Wallpaper> ->
                list.forEach { wallpaper ->
                    tab_layout.addTab(tab_layout.newTab().setText(wallpaper.wallpaperKey.capitalize()))
                }
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
