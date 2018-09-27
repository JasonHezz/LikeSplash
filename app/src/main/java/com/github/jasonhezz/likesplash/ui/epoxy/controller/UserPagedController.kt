package com.github.jasonhezz.likesplash.ui.epoxy.controller

import com.airbnb.epoxy.AutoModel
import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.github.jasonhezz.likesplash.data.entities.User
import com.github.jasonhezz.likesplash.ui.epoxy.EpoxyModelProperty
import com.github.jasonhezz.likesplash.ui.epoxy.model.LoadingModel_
import com.github.jasonhezz.likesplash.ui.epoxy.model.UserModel_

/**
 * Created by JavaCoder on 2017/12/8.
 */
class UserPagedController : PagedListEpoxyController<User>(EpoxyAsyncUtil.getAsyncBackgroundHandler()) {

    @AutoModel
    lateinit var loadingModel: LoadingModel_

    var isLoading by EpoxyModelProperty { false }

    override fun buildItemModel(currentPosition: Int, item: User?): EpoxyModel<*> {
        return UserModel_()
                .id(currentPosition)
                .user(item)
    }

    override fun addModels(models: List<EpoxyModel<*>>) {
        super.addModels(models)
        loadingModel.addIf(isLoading, this)
    }
}