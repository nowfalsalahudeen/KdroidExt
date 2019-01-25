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
package com.nowfal.kdroidext.kex

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import android.widget.Toast
import androidx.annotation.*

fun Context.color(@ColorRes clr: Int): Int {
    return ContextCompat.getColor(this, clr)
}

fun Context.string(@StringRes str: Int): String {
    return getString(str)
}

fun Context.drawable(@DrawableRes drw: Int): Drawable? {
    return ContextCompat.getDrawable(this, drw)
}

fun Context.dimen(@DimenRes dmn: Int): Float {
    return resources.getDimension(dmn)
}

fun Context.dimenInt(@DimenRes dmn: Int): Int {
    return resources.getDimensionPixelSize(dmn)
}

fun Context.int(@IntegerRes int: Int): Int {
    return resources.getInteger(int)
}

fun Context.font(@FontRes font: Int): Typeface? {
    return ResourcesCompat.getFont(this, font)
}

fun Context.stringArray(array: Int): Array<String> {
    return resources.getStringArray(array)
}

fun Context.intArray(array: Int): IntArray {
    return resources.getIntArray(array)
}

fun Context.toast(message: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}