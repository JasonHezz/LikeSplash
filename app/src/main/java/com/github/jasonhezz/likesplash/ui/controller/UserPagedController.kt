package com.github.jasonhezz.likesplash.ui.controller

import com.airbnb.epoxy.AutoModel
import com.airbnb.epoxy.paging.PagingEpoxyController
import com.github.jasonhezz.likesplash.data.User
import com.github.jasonhezz.likesplash.data.model.LoadingModel_
import com.github.jasonhezz.likesplash.data.model.user

/**
 * Created by JavaCoder on 2017/12/8.
 */
class UserPagedController : PagingEpoxyController<User>() {
  @AutoModel
  lateinit var loadingModel: LoadingModel_

  var isLoading: Boolean = false
    set(value) {
      if (field != value) {
        field = value
        requestModelBuild()
      }
    }

  override fun buildModels(list: MutableList<User>) {
    list.forEach {
      user {
        id(it.id?:"")
        user(it)
      }
    }
    loadingModel.addIf(isLoading, this)
  }
}