package com.github.jasonhezz.likesplash.ui.controller

import android.support.v4.content.ContextCompat
import com.airbnb.epoxy.AutoModel
import com.airbnb.epoxy.TypedEpoxyController
import com.github.jasonhezz.likesplash.App
import com.github.jasonhezz.likesplash.R
import com.github.jasonhezz.likesplash.data.entities.Photo
import com.github.jasonhezz.likesplash.data.model.ChipModel_
import com.github.jasonhezz.likesplash.data.model.LoadingModel_
import com.github.jasonhezz.likesplash.data.model.photoDetail
import com.github.jasonhezz.likesplash.data.model.previewCollection
import com.github.jasonhezz.likesplash.data.model.title
import com.github.jasonhezz.likesplash.util.extension.withModelsFrom
import com.github.jasonhezz.likesplash.view.flexCarousel
import com.google.android.flexbox.FlexboxItemDecoration

/**
 * Created by JavaCoder on 2017/10/16.
 */

class PhotoDetailController : TypedEpoxyController<Photo>() {

    @AutoModel
    lateinit var loadingModel: LoadingModel_

    var isLoading: Boolean = true
        set(value) {
            if (field != value) {
                field = value
                requestModelBuild()
            }
        }

    override fun buildModels(data: Photo?) {
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
                        FlexboxItemDecoration(it).apply {
                            setDrawable(
                                ContextCompat.getDrawable(App.applicationContext(), R.drawable.chip_divider)
                            )
                        }
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

