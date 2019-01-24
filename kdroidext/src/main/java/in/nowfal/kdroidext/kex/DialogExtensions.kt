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
package `in`.nowfal.kdroidext.kex

import `in`.nowfal.kdroidext.kex.*
import android.app.Dialog
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import androidx.annotation.*
import androidx.fragment.app.DialogFragment

fun Dialog.color(@ColorRes clr: Int): Int {
    return context.color(clr)
}

fun Dialog.string(@StringRes str: Int): String {
    return context.string(str)
}

fun Dialog.drawable(@DrawableRes drw: Int): Drawable? {
    return context.drawable(drw)
}

fun Dialog.dimen(@DimenRes dmn: Int): Float {
    return context.dimen(dmn)
}

fun Dialog.dimenInt(@DimenRes dmn: Int): Int {
    return context.dimenInt(dmn)
}

fun Dialog.int(@IntegerRes int: Int): Int {
    return context.int(int)
}

fun Dialog.font(@FontRes font: Int): Typeface? {
    return context.font(font)
}

fun Dialog.stringArray(array: Int): Array<String> {
    return context.stringArray(array)
}

fun Dialog.intArray(array: Int): IntArray {
    return context.intArray(array)
}

fun DialogFragment.color(@ColorRes clr: Int): Int {
    return context!!.color(clr)
}

fun DialogFragment.string(@StringRes str: Int): String {
    return context!!.string(str)
}

fun DialogFragment.drawable(@DrawableRes drw: Int): Drawable? {
    return context!!.drawable(drw)
}

fun DialogFragment.dimen(@DimenRes dmn: Int): Float {
    return context!!.dimen(dmn)
}

fun DialogFragment.dimenInt(@DimenRes dmn: Int): Int {
    return context!!.dimenInt(dmn)
}

fun DialogFragment.int(@IntegerRes int: Int): Int {
    return context!!.int(int)
}

fun DialogFragment.font(@FontRes font: Int): Typeface? {
    return context!!.font(font)
}

fun DialogFragment.stringArray(array: Int): Array<String> {
    return context!!.stringArray(array)
}

fun DialogFragment.intArray(array: Int): IntArray {
    return context!!.intArray(array)
}