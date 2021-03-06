/*
 * Copyright 2019 Nowfal Salahudeen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:Suppress("NOTHING_TO_INLINE")

package com.nowfal.kdroidext.kex

import android.view.View
import android.view.View.*
import androidx.annotation.IdRes

@Deprecated("Use findViewById() instead", ReplaceWith("findViewById()"))
inline fun <reified T : View> View.find(@IdRes id: Int): T = findViewById(id)

var View.visible
    get() = visibility == VISIBLE
    set(value) {
        visibility = if (value) VISIBLE else GONE
    }

inline fun View.hide(gone: Boolean = true) {
    visibility = if (gone) GONE else INVISIBLE
}

inline fun View.show() {
    visibility = VISIBLE
}

inline fun View.invisible() {
    visibility = INVISIBLE
}

inline fun View.gone() {
    visibility = GONE
}


inline fun View.setSize(width:Int, height:Int) {
    val params = layoutParams
    params.width = width
    params.height = height
    layoutParams = params
}