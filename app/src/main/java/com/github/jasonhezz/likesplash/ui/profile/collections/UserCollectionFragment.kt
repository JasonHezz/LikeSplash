package com.github.jasonhezz.likesplash.ui.profile.collections

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.api.Status
import com.github.jasonhezz.likesplash.data.entities.Collection
import com.github.jasonhezz.likesplash.data.entities.User
import com.github.jasonhezz.likesplash.ui.collection.detail.CollectionDetailActivity
import com.github.jasonhezz.likesplash.ui.epoxy.controller.CollectionPagedController
import com.github.jasonhezz.likesplash.util.recyclerview.SlideInItemAnimator
import kotlinx.android.synthetic.main.fragment_like.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

/**
 * Created by JavaCoder on 2017/6/28.
 */
class UserCollectionFragment : Fragment() {

    private val user by lazy { arguments?.getParcelable<User>(ARG_PARAM_USER) }
    private val viewModel by viewModel<UserCollectionViewModel> {
        parametersOf(user?.username!!)
    }
    private val controller by inject<CollectionPagedController> {
        parametersOf(
                object : CollectionPagedController.AdapterCallbacks {
                    override fun onAvatarClick() {

                    }

                    override fun onCollectionClick(it: Collection) {
                        startActivity(Intent(context, CollectionDetailActivity::class.java).apply {
                            putExtra("collection", it)
                        })
                    }
                }
        )
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? =
            inflater.inflate(R.layout.fragment_like, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initController()
    }

    private fun initController() {
        rv.itemAnimator = SlideInItemAnimator()
        rv.setController(controller)
        viewModel.collections.observe(this, Observer {
            controller.setList(it)
        })
        viewModel.networkState.observe(this, Observer {
            when (it?.status) {
                Status.LOADING_MORE -> {
                    controller.isLoading = true
                }
                Status.SUCCESS -> {
                    controller.isLoading = false
                }
                Status.ERROR -> {
                    Timber.e(it.message)
                }
                else -> {
                }
            }
        })
    }

    companion object {
        const val ARG_PARAM_USER = "userId"
        @JvmStatic
        fun newInstance(user: User?): UserCollectionFragment {
            val fragment = UserCollectionFragment()
            val args = Bundle()
            args.putParcelable(ARG_PARAM_USER, user)
            fragment.arguments = args
            return fragment
        }
    }
}