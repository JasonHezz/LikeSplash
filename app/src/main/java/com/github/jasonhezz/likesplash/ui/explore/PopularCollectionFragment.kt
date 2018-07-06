package com.github.jasonhezz.likesplash.ui.explore

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.ExploreCollection
import com.github.jasonhezz.likesplash.ui.controller.PopularCollectionController
import com.github.jasonhezz.likesplash.util.network.NetModule
import kotlinx.android.synthetic.main.fragment_popular_collection.*
import retrofit2.Call
import retrofit2.Response
import timber.log.Timber

class PopularCollectionFragment : Fragment() {

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular_collection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv.setController(controller)
        NetModule.provideExploreService().getExploreCollection().enqueue(
            object : retrofit2.Callback<List<ExploreCollection>> {
                override fun onFailure(call: Call<List<ExploreCollection>>?, t: Throwable?) {
                    Timber.d(t)
                }

                override fun onResponse(
                    call: Call<List<ExploreCollection>>?,
                    response: Response<List<ExploreCollection>>?
                ) {
                    response?.body()?.let {
                        controller.explores = it
                    }
                }
            })
        /*rv.setControllerAndBuildModels(controller)
        rv.isNestedScrollingEnabled = false*/
    }

    companion object {
        fun newInstance(): PopularCollectionFragment {
            val fragment = PopularCollectionFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
