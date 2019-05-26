

package com.nowfal.kdroidext.kotx.ext.app

import android.os.Bundle
import android.os.Parcelable
import android.util.SparseArray


/**
 * Calls specified [block], passing in a [Bundle] instance, which is returned
 * when the block completes.
 */
fun newBundle(block: Bundle.() -> Unit): Bundle = Bundle().apply {
    block(this)
}

/**
 * Shorthand for [Bundle.putBoolean].
 */
fun Bundle.put(key: String?, value: Boolean) {
    putBoolean(key, value)
}

/**
 * Shorthand for [Bundle.putByte].
 */
fun Bundle.put(key: String?, value: Byte) {
    putByte(key, value)
}

/**
 * Shorthand for [Bundle.putChar].
 */
fun Bundle.put(key: String?, value: Char) {
    putChar(key, value)
}

/**
 * Shorthand for [Bundle.putShort].
 */
fun Bundle.put(key: String?, value: Short) {
    putShort(key, value)
}

/**
 * Shorthand for [Bundle.putInt].
 */
fun Bundle.put(key: String?, value: Int) {
    putInt(key, value)
}

/**
 * Shorthand for [Bundle.putLong].
 */
fun Bundle.put(key: String?, value: Long) {
    putLong(key, value)
}

/**
 * Shorthand for [Bundle.putFloat].
 */
fun Bundle.put(key: String?, value: Float) {
    putFloat(key, value)
}

/**
 * Shorthand for [Bundle.putDouble].
 */
fun Bundle.put(key: String?, value: Double) {
    putDouble(key, value)
}

/**
 * Shorthand for [Bundle.putParcelable].
 */
fun Bundle.put(key: String?, value: Parcelable?) {
    putParcelable(key, value)
}

/**
 * Shorthand for [Bundle.putByteArray].
 */
fun Bundle.put(key: String?, value: ByteArray?) {
    putByteArray(key, value)
}

/**
 * Shorthand for [Bundle.putShortArray].
 */
fun Bundle.put(key: String?, value: ShortArray?) {
    putShortArray(key, value)
}

/**
 * Shorthand for [Bundle.putCharArray].
 */
fun Bundle.put(key: String?, value: CharArray?) {
    putCharArray(key, value)
}

/**
 * Shorthand for [Bundle.putFloatArray].
 */
fun Bundle.put(key: String?, value: FloatArray?) {
    putFloatArray(key, value)
}

/**
 * Shorthand for [Bundle.putIntArray].
 */
fun Bundle.put(key: String?, value: IntArray?) {
    putIntArray(key, value)
}

/**
 * Shorthand for [Bundle.putLongArray].
 */
fun Bundle.put(key: String?, value: LongArray?) {
    putLongArray(key, value)
}

/**
 * Shorthand for [Bundle.putString].
 */
fun Bundle.put(key: String?, value: String?) {
    putString(key, value)
}

/**
 * Shorthand for [Bundle.putBundle].
 */
fun Bundle.put(key: String?, value: Bundle?) {
    putBundle(key, value)
}

/**
 * Shorthand for [Bundle.putCharSequenceArray].
 */
fun Bundle.put(key: String?, value: Array<CharSequence?>?) {
    putCharSequenceArray(key, value)
}

/**
 * Shorthand for [Bundle.putParcelableArray].
 */
fun Bundle.put(key: String?, value: Array<Parcelable?>?) {
    putParcelableArray(key, value)
}

/**
 * Shorthand for [Bundle.putParcelableArrayList].
 */
fun Bundle.put(key: String?, value: ArrayList<out Parcelable?>?) {
    putParcelableArrayList(key, value)
}

/**
 * Shorthand for [Bundle.putSparseParcelableArray].
 */
fun Bundle.put(key: String?, value: SparseArray<out Parcelable?>?) {
    putSparseParcelableArray(key, value)
}
