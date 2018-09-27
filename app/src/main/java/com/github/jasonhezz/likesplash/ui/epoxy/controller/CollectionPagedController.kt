package com.github.jasonhezz.likesplash.ui.epoxy.controller

import com.airbnb.epoxy.AutoModel
import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.github.jasonhezz.likesplash.data.entities.Collection
import com.github.jasonhezz.likesplash.ui.epoxy.EpoxyModelProperty
import com.github.jasonhezz.likesplash.ui.epoxy.model.CollectionModel_
import com.github.jasonhezz.likesplash.ui.epoxy.model.LoadingModel_

/**
 * Created by JavaCoder on 2017/12/13.
 */
class CollectionPagedController(
        var callback: AdapterCallbacks? = null
) : PagedListEpoxyController<Collection>(EpoxyAsyncUtil.getAsyncBackgroundHandler()) {

    @AutoModel
    lateinit var loadingModel: LoadingModel_

    var isLoading by EpoxyModelProperty { false }

    override fun buildItemModel(currentPosition: Int, item: Collection?): EpoxyModel<*> {
        return CollectionModel_()
                .id(item?.id)
                .collection(item!!)
                .collectionClickListener { model, parentView, clickedView, position ->
                    callback?.onCollectionClick(item)
                }
    }

    override fun addModels(models: List<EpoxyModel<*>>) {
        super.addModels(models)
        loadingModel.addIf(isLoading, this)
    }

    interface AdapterCallbacks {
        fun onAvatarClick()
        fun onCollectionClick(it: Collection)
    }
}