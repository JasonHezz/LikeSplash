package com.github.jasonhezz.likesplash.ui.wallpaper

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.api.Status
import com.github.jasonhezz.likesplash.data.entities.Photo
import com.github.jasonhezz.likesplash.data.entities.User
import com.github.jasonhezz.likesplash.data.entities.Wallpaper
import com.github.jasonhezz.likesplash.ui.collection.detail.CollectionDetailViewModel
import com.github.jasonhezz.likesplash.ui.epoxy.controller.PhotoPagedController
import com.github.jasonhezz.likesplash.ui.photodetail.PhotoDetailActivity
import com.github.jasonhezz.likesplash.ui.profile.ProfileActivity
import kotlinx.android.synthetic.main.fragment_wallpaper.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class WallpaperFragment : Fragment() {

    private val wallpaper by lazy { arguments?.getParcelable<Wallpaper?>(ARG_PARAM_WALLPAPER) }
    private val viewModel by viewModel<CollectionDetailViewModel> {
        parametersOf("${wallpaper?.id}", false)
    }
    private val controller by inject<PhotoPagedController> {
        parametersOf(
                object : PhotoPagedController.AdapterCallbacks {
                    override fun onAvatarClick(view: View, user: User?) {
                        val options = ActivityOptions.makeSceneTransitionAnimation(
                                requireActivity(), view, getString(R.string.transition_avatar))
                        startActivity(Intent(requireContext(), ProfileActivity::class.java)
                                .putExtra(ProfileActivity.ARG_PARAM_USER, user), options.toBundle())
                    }

                    override fun onPhotoClick(it: Photo) {
                        startActivity(Intent(requireContext(), PhotoDetailActivity::class.java)
                                .putExtra("id", it.id))
                    }
                }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {

        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_wallpaper, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv.setController(controller)
        viewModel.photos.observe(this, Observer {
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

        private const val ARG_PARAM_WALLPAPER = "wallpaper"

        fun newInstance(id: Wallpaper): WallpaperFragment {
            val fragment = WallpaperFragment()
            val args = Bundle()
            args.putParcelable(ARG_PARAM_WALLPAPER, id)
            fragment.arguments = args
            return fragment
        }
    }
}
