package com.github.jasonhezz.likesplash.util.extension

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by JavaCoder on 2017/11/28.
 */
operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
  add(disposable)
}