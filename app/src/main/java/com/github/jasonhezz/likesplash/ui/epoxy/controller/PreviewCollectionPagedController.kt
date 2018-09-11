package com.github.jasonhezz.likesplash.ui.epoxy.controller

import android.content.Context
import android.support.design.chip.Chip
import android.support.v4.content.ContextCompat
import android.view.View
import com.airbnb.epoxy.AutoModel
import com.airbnb.epoxy.paging.PagingEpoxyController
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.entities.Collection
import com.github.jasonhezz.likesplash.data.entities.Tag
import com.github.jasonhezz.likesplash.ui.epoxy.EpoxyModelProperty
import com.github.jasonhezz.likesplash.ui.epoxy.model.LoadingModel_
import com.github.jasonhezz.likesplash.ui.epoxy.model.previewCollection
import com.github.jasonhezz.likesplash.util.recyclerview.SpanGirdItemDecoration
import com.google.android.flexbox.FlexboxItemDecoration

/**
 * Created by JavaCoder on 2017/12/13.
 */
class PreviewCollectionPagedController(val context: Context, var callback: AdapterCallbacks? = null) : PagingEpoxyController<Collection>() {

    @AutoModel
    lateinit var loadingModel: LoadingModel_

    var isLoading by EpoxyModelProperty { false }

    val tagDivider = FlexboxItemDecoration(context).apply {
        setDrawable(
                ContextCompat.getDrawable(context, R.drawable.chip_divider)
        )
    }

    val previewImgDivider =
            SpanGirdItemDecoration(ContextCompat.getDrawable(context, R.drawable.preview_img_divider)!!)


    override fun buildModels(list: MutableList<Collection>) {
        list.forEach { model ->
            previewCollection {
                id(model.id)
                collection(model)
                tagDivider(tagDivider)
                previewImgDivider(previewImgDivider)
                collectionClickListener(View.OnClickListener {
                    callback?.onCollectionClick(model)
                })
            }
        }
        loadingModel.addIf(isLoading, this)
    }

    interface AdapterCallbacks {
        fun onCollectionClick(it: Collection)
    }
}