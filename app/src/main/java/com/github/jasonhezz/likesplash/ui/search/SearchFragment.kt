package com.github.jasonhezz.likesplash.ui.search

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.ui.epoxy.controller.SearchHintController
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.include_search_bar.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {
    private val viewModel by viewModel<SearchViewModel>()
    private val searchController by inject<SearchHintController>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {

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
    }

    private fun bindView() {
        search_view.imeOptions = search_view.imeOptions or EditorInfo.IME_ACTION_SEARCH or
                EditorInfo.IME_FLAG_NO_EXTRACT_UI or EditorInfo.IME_FLAG_NO_FULLSCREEN
        hint_rv.setController(searchController)
    }

    private fun bindEvent() {
        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
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

    companion object {
        fun newInstance(): SearchFragment {
            val fragment = SearchFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}