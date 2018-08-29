package com.github.jasonhezz.likesplash.ui.epoxy.controller

import com.airbnb.epoxy.AutoModel
import com.airbnb.epoxy.paging.PagingEpoxyController
import com.github.jasonhezz.likesplash.data.entities.User
import com.github.jasonhezz.likesplash.ui.epoxy.EpoxyModelProperty
import com.github.jasonhezz.likesplash.ui.epoxy.model.LoadingModel_
import com.github.jasonhezz.likesplash.ui.epoxy.model.user

/**
 * Created by JavaCoder on 2017/12/8.
 */
class UserPagedController : PagingEpoxyController<User>() {

    @AutoModel
    lateinit var loadingModel: LoadingModel_

    var isLoading by EpoxyModelProperty { false }

    override fun buildModels(list: MutableList<User>) {
        list.forEachIndexed { index, user ->
            user {
                id(index)
                user(user)
            }
        }
        loadingModel.addIf(isLoading, this)
    }
}