

package com.nowfal.kdroidext.kotx.ext.basic

import android.content.Context


/**
 * Converts receiver (value in dp - device-independent pixels) to pixels,
 * using [context].
 */
fun Int.dpToPixel(context: Context): Int {
    return (context.resources.displayMetrics.density * this).toInt()
}

/**
 * Converts receiver (value in sp - scaled device-independent pixels) to pixels,
 * using [context].
 */
fun Int.spToPixel(context: Context): Int {
    return (context.resources.displayMetrics.scaledDensity * this).toInt()
}
