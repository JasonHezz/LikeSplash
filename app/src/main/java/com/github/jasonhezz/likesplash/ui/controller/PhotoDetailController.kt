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

            loadingModel.addIf(isLoading,this)

            it.relatedCollections?.let {
                title {
                    id("collection_title")
                    title("Featured in ${it.total ?: 0} collections")
                }
                it.results?.let {
                    it.forEach {
                        previewCollection {
                            id(it.id)
                            collection(it)
                        }
                    }
                }
            }

            it.tags?.let {
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
                    withModelsFrom(it) {
                        ChipModel_().id(it.title).tag(it)
                    }
                }
            }
        }
    }
}

