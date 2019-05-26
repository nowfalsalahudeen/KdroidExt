// Copyright (c) Akop Karapetyan
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package org.akop.kotx.ext.widget

import androidx.annotation.*
import com.google.android.material.snackbar.Snackbar
import android.view.View
import android.widget.TextView


/**
 * Default [Snackbar] duration. Set to [Snackbar.LENGTH_LONG].
 */
const val SNACKBAR_DEFAULT_DURATION = Snackbar.LENGTH_LONG

/**
 * Creates a new [Snackbar]
 * with specified [parent] view, [message] and [duration],
 * then calls optional [block] to initialize before displaying.
 *
 * Does nothing if [message] is null.
 *
 * Duration defaults to [SNACKBAR_DEFAULT_DURATION].
 */
fun showSnackbar(parent: View, message: CharSequence?,
                 duration: Int = SNACKBAR_DEFAULT_DURATION,
                 block: Snackbar.() -> Unit = {}) {
    if (message == null) return
    Snackbar.make(parent, message, duration).apply {
        block(this)
    }.show()
}

/**
 * Creates a new [Snackbar]
 * with specified [parent] view, [messageResId] and [duration],
 * then calls optional [block] to initialize before displaying.
 *
 * Duration defaults to [SNACKBAR_DEFAULT_DURATION].
 */
fun showSnackbar(parent: View, @StringRes messageResId: Int,
                 duration: Int = SNACKBAR_DEFAULT_DURATION,
                 block: Snackbar.() -> Unit = {}) {
    Snackbar.make(parent, messageResId, duration).apply {
        block(this)
    }.show()
}

/**
 * Returns the [Snackbar]'s text view by looking for
 * `android.support.design.R.id.snackbar_text` in the view's layout.
 */
val Snackbar.textView: TextView?
    get() = view.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
