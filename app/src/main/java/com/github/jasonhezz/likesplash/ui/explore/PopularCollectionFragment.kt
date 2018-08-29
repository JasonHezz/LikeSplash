package com.github.jasonhezz.likesplash.ui.explore

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.ui.epoxy.controller.PopularCollectionController
import kotlinx.android.synthetic.main.fragment_popular_collection.*
import org.koin.android.viewmodel.ext.android.viewModel

class PopularCollectionFragment : Fragment() {

    private val model: PopularCollectionViewModel by viewModel()
    private val controller by lazy { PopularCollectionController(context!!) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_popular_collection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv.setController(controller)
        model.result.observe(this, Observer { it ->
            controller.data = it
        })
    }

    companion object {
        fun newInstance(): PopularCollectionFragment {
            val fragment = PopularCollectionFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
