package com.github.jasonhezz.likesplash.ui.search

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.SearchView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.ui.epoxy.controller.SearchHintController
import com.github.jasonhezz.likesplash.util.adapter.TabFragmentAdapter
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.include_search_bar.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SearchFragment : Fragment() {
    private val viewModel by viewModel<SearchViewModel>()
    private val searchController by inject<SearchHintController> {
        parametersOf(
                object : SearchHintController.AdapterCallbacks {
                    override fun onClick(it: String?) {
                        if (TextUtils.isEmpty(it)) return

                        search_view.setQuery(it, true)
                    }
                })
    }
    private lateinit var tabAdapter: TabFragmentAdapter

    private val query by lazy { arguments?.getString(KEY_PARAM) }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState?.let {
            tabAdapter.saveState()
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_search, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView()
        bindEvent()

        if (TextUtils.isEmpty(query)) return

        search_view.setQuery(query, true)
    }

    private fun bindView() {
        search_view.imeOptions = search_view.imeOptions or EditorInfo.IME_ACTION_SEARCH or
                EditorInfo.IME_FLAG_NO_EXTRACT_UI or EditorInfo.IME_FLAG_NO_FULLSCREEN
        hint_rv.setController(searchController)
    }

    private fun bindEvent() {
        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (TextUtils.isEmpty(query)) return true
                initViewPager(query!!, tab_layout.selectedTabPosition)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                hint_rv.visibility = View.VISIBLE
                tab_layout.visibility = View.GONE
                view_pager.visibility = View.GONE

                viewModel.query.value = newText
                return true
            }
        })
        search_nav?.setOnClickListener {
            activity?.supportFinishAfterTransition()
        }
        viewModel.hints.observe(this, Observer {
            searchController.data = it
        })
        viewModel.query.observe(this, Observer {
            it?.let { searchController.query = it }
        })

    }

    private fun initViewPager(query: String, select: Int) {
        hint_rv.visibility = View.GONE
        tab_layout.visibility = View.VISIBLE
        view_pager.visibility = View.VISIBLE

        tabAdapter = TabFragmentAdapter(childFragmentManager)
        tabAdapter.addFragment(PicturesFragment.newInstance(query))
        tabAdapter.addFragment(CollectionsFragment.newInstance(query))
        tabAdapter.addFragment(UserFragment.newInstance(query))
        view_pager.apply {
            adapter = tabAdapter
            offscreenPageLimit = 3
            addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))
        }
        tab_layout?.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(view_pager))

        if (select == -1) return
        view_pager.currentItem = select
    }

    companion object {
        const val KEY_PARAM = "key"

        fun newInstance(query: String?): SearchFragment {
            val fragment = SearchFragment()
            val args = Bundle()
            args.putString(KEY_PARAM, query)
            fragment.arguments = args
            return fragment
        }
    }
}