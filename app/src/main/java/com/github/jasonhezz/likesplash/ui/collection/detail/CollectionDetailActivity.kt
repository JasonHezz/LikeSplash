package com.github.jasonhezz.likesplash.ui.collection.detail

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androidx.core.view.isVisible
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.api.Status
import com.github.jasonhezz.likesplash.data.entities.Collection
import com.github.jasonhezz.likesplash.ui.epoxy.controller.PhotoPagedController
import com.github.jasonhezz.likesplash.util.glide.GlideApp
import kotlinx.android.synthetic.main.activity_collection_detail.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class CollectionDetailActivity : AppCompatActivity() {

    private val collection: Collection? by lazy { intent.getParcelableExtra<Collection?>("collection") }
    private val viewModel: CollectionDetailViewModel by viewModel {
        parametersOf("${collection?.id}", intent.getBooleanExtra("isCurated", false))
    }
    private val controller = PhotoPagedController().apply { setFilterDuplicates(true) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collection_detail)
        initUI()
        initController()
        fab.setOnClickListener {
            fabProgressCircle.show()
        }
    }

    private fun initController() {
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

    private fun initUI() {
        toolbar.inflateMenu(R.menu.menu_share)
        toolbar.title = collection?.title
        toolbar.setNavigationOnClickListener { finish() }
        user_name.text = collection?.user?.name
        if (collection?.description.isNullOrEmpty()) description_tv.isVisible = false
        else description_tv.text = collection?.description
        GlideApp
            .with(this)
            .load(collection?.user?.profile_image?.medium)
            .into(user_avatar)
        GlideApp
            .with(this)
            .load(collection?.coverPhoto?.urls?.regular)
            .into(cover)
    }
}
