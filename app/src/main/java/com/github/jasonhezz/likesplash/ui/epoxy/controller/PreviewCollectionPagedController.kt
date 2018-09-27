package com.github.jasonhezz.likesplash.ui.epoxy.controller

import android.content.Context
import androidx.core.content.ContextCompat
import com.airbnb.epoxy.AutoModel
import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.entities.Collection
import com.github.jasonhezz.likesplash.ui.epoxy.EpoxyModelProperty
import com.github.jasonhezz.likesplash.ui.epoxy.model.LoadingModel_
import com.github.jasonhezz.likesplash.ui.epoxy.model.PreviewCollectionModel_
import com.github.jasonhezz.likesplash.util.recyclerview.SpanGirdItemDecoration
import com.google.android.flexbox.FlexboxItemDecoration

/**
 * Created by JavaCoder on 2017/12/13.
 */
class PreviewCollectionPagedController(
        val context: Context, var callback: AdapterCallbacks? = null
) : PagedListEpoxyController<Collection>(EpoxyAsyncUtil.getAsyncBackgroundHandler()) {

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

    override fun buildItemModel(currentPosition: Int, item: Collection?): EpoxyModel<*> {
        return PreviewCollectionModel_()
                .id(item?.id)
                .collection(item)
                .tagDivider(tagDivider)
                .previewImgDivider(previewImgDivider)
                .collectionClickListener { view ->
                    callback?.onCollectionClick(item!!)
                }
    }

    override fun addModels(models: List<EpoxyModel<*>>) {
        super.addModels(models)
        loadingModel.addIf(isLoading, this)
    }

    interface AdapterCallbacks {
        fun onCollectionClick(it: Collection)
    }
}