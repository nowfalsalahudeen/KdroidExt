
package com.nowfal.kdroidext.kotx.ext.content

import android.content.res.Resources
import android.content.res.TypedArray
import androidx.annotation.RawRes


/**
 * Reads and returns a raw resource as a string.
 *
 * Intended use is for smaller text files, since buffering is done on the
 * calling thread.
 *
 * @param resId Raw resource identifier.
 *
 * @return Contents of raw resource as a string.
 */
fun Resources.rawResourceAsString(@RawRes resId: Int): String =
        openRawResource(resId).bufferedReader().use { it.readText() }

/**
 * Invokes [block], passing it the receiver and returning the value
 * it returns. Recycles the receiver after [block] completes.
 *
 * @param block Lambda function accepting an instance of the receiver.
 *
 * @return Value returned by [block].
 */
fun <T> TypedArray.use(block: (TypedArray) -> T): T {
    val r = block(this)
    recycle()
    return r
}
