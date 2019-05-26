

package com.nowfal.kdroidext.kotx.ext.app

import android.app.Activity
import android.content.Intent
import android.view.View


/**
 * Hides the soft keyboard if it's visible by calling
 * [android.view.inputmethod.InputMethodManager.hideSoftInputFromWindow].
 */
fun Activity.hideKeyboard() {
    val focused = currentFocus ?: return
    focused.clearFocus()
    focused.windowToken?.let { inputMethodManager.hideSoftInputFromWindow(it, 0) }
}

/**
 * Starts a new activity by creating a new [Intent], calling [block] with
 * the new instance as receiver, then passing the intent to
 * [Activity.startActivityForResult].
 */
inline fun <reified T: Activity> Activity.startActivityForResult(requestCode: Int, block: Intent.() -> Unit = {}) {
    startActivityForResult(Intent(this, T::class.java).apply {
        block(this)
    }, requestCode)
}

/**
 * Returns the Activity's content (root) view.
 */
val Activity.contentView: View?
    get() = findViewById(android.R.id.content)
