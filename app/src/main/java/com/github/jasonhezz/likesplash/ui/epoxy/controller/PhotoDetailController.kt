package com.github.jasonhezz.likesplash.ui.epoxy.controller

import android.content.Context
import android.support.v4.content.ContextCompat
import com.airbnb.epoxy.AsyncEpoxyController
import com.airbnb.epoxy.AutoModel
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.entities.Photo
import com.github.jasonhezz.likesplash.ui.epoxy.EpoxyModelProperty
import com.github.jasonhezz.likesplash.ui.epoxy.model.*
import com.github.jasonhezz.likesplash.ui.epoxy.withModelsFrom
import com.github.jasonhezz.likesplash.util.recyclerview.SpanGirdItemDecoration
import com.github.jasonhezz.likesplash.view.flexCarousel
import com.google.android.flexbox.FlexboxItemDecoration

/**
 * Created by JavaCoder on 2017/10/16.
 */

class PhotoDetailController(val context: Context) : AsyncEpoxyController() {

    @AutoModel
    lateinit var loadingModel: LoadingModel_

    var isLoading by EpoxyModelProperty { false }

    var data: Photo? by EpoxyModelProperty { null }


    val tagDivider = FlexboxItemDecoration(context).apply {
        setDrawable(
                ContextCompat.getDrawable(context, R.drawable.chip_divider)
        )
    }

    val previewImgDivider =
            SpanGirdItemDecoration(ContextCompat.getDrawable(context, R.drawable.preview_img_divider)!!)

    override fun buildModels() {
        data?.let {

            photoDetail {
                id(it.id)
                photo(it)
            }

            title {
                id("relate_photo_title")
                title("Related photos")
            }

            loadingModel.addIf(isLoading, this)

            if (it.relatedCollections?.results?.isEmpty() == false) {

                title {
                    id("collection_title")
                    title("Related collections")
                }

                it.relatedCollections.results.forEach {
                    previewCollection {
                        id(it.id)
                        tagDivider(tagDivider)
                        previewImgDivider(previewImgDivider)
                        collection(it)
                    }
                }
            }

            if (it.tags?.isEmpty() == false) {

                title {
                    id("related_tag_title")
                    title("Related tags")
                }

                flexCarousel {
                    id("tag_carousel")
                    addItemDecoration {
                        tagDivider
                    }
                    isFullSpan(true)
                    withModelsFrom(it.tags) {
                        ChipModel_().id(it.title).tag(it)
                    }
                }
            }
        }
    }
}

