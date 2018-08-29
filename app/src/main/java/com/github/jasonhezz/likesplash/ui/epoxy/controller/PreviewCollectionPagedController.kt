package com.github.jasonhezz.likesplash.ui.epoxy.controller

import android.view.View
import com.airbnb.epoxy.AutoModel
import com.airbnb.epoxy.paging.PagingEpoxyController
import com.github.jasonhezz.likesplash.data.entities.Collection
import com.github.jasonhezz.likesplash.ui.epoxy.model.LoadingModel_
import com.github.jasonhezz.likesplash.ui.epoxy.model.previewCollection
import com.github.jasonhezz.likesplash.ui.epoxy.EpoxyModelProperty

/**
 * Created by JavaCoder on 2017/12/13.
 */
class PreviewCollectionPagedController(var callback: AdapterCallbacks? = null) : PagingEpoxyController<Collection>() {

    @AutoModel
    lateinit var loadingModel: LoadingModel_

    var isLoading by EpoxyModelProperty { false }

    override fun buildModels(list: MutableList<Collection>) {
        list.forEach { model ->
            previewCollection {
                id(model.id)
                collection(model)
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