/*
 * Copyright (c) 2017 Zac Sweers
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jasonhezz.likesplash.util.extension

import android.view.View

//support library 25.3.1 is different from 26.1.0 under lolipop
fun View.showSnackbar(snackbarText: String, timeLength: Int = 250) {
    com.google.android.material.snackbar.Snackbar.make(this, snackbarText, timeLength).show()
}


