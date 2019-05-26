

package com.nowfal.kdroidext.kotx.ext.view

import android.view.View
import android.widget.EditText
import android.widget.TextView


/**
 * Returns string value of [TextView.getText].
 */
val TextView.textString: String?
    get() = text?.toString()

/**
 * Gets/sets string value of field as [String].
 */
var EditText.textString: String?
    get() = text?.toString()
    set(value) {
        setText(value)
    }

/**
 * Sets receiver's visibility to [View.GONE] if [TextView.getText] is
 * null or empty; sets it to [View.VISIBLE] otherwise.
 */
fun TextView.collapseIfEmpty() {
    visibility = if (!text.isNullOrEmpty()) View.VISIBLE else View.GONE
}

/**
 * Sets receiver's visibility to [View.INVISIBLE] if [TextView.getText] is
 * null or empty; sets it to [View.VISIBLE] otherwise.
 */
fun TextView.hideIfEmpty() {
    visibility = if (!text.isNullOrEmpty()) View.VISIBLE else View.INVISIBLE
}
