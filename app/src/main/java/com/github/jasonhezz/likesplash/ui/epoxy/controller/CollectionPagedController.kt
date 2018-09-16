package com.github.jasonhezz.likesplash.ui.epoxy.controller

import com.airbnb.epoxy.AutoModel
import com.airbnb.epoxy.paging.PagingEpoxyController
import com.github.jasonhezz.likesplash.data.entities.Collection
import com.github.jasonhezz.likesplash.ui.epoxy.EpoxyModelProperty
import com.github.jasonhezz.likesplash.ui.epoxy.model.LoadingModel_
import com.github.jasonhezz.likesplash.ui.epoxy.model.collection

/**
 * Created by JavaCoder on 2017/12/13.
 */
class CollectionPagedController(
        var callback: AdapterCallbacks? = null
) : PagingEpoxyController<Collection>() {

    @AutoModel
    lateinit var loadingModel: LoadingModel_

    var isLoading by EpoxyModelProperty { false }

    override fun buildModels(list: MutableList<Collection>) {
        list.forEach {
            collection {
                id(it.id)
                collection(it)
                collectionClickListener { model, parentView, clickedView, position ->
                    callback?.onCollectionClick(it)
                }
            }
        }
        loadingModel.addIf(isLoading, this)
    }


    interface AdapterCallbacks {
        fun onAvatarClick()
        fun onCollectionClick(it: Collection)
    }
}