
package com.nowfal.kdroidext.kotx.ext.content

import android.content.ContentValues


/**
 * Creates a [ContentValues] by reading [pairs] - similarly to how [mapOf]
 * and similar methods work.
 *
 * Note that value types are limited to those acceptable by [ContentValues] -
 * specifically, [String], [Byte], [Short], [Int], [Long], [Float], [Double],
 * [Boolean], [ByteArray].
 */
fun contentValuesOf(vararg pairs: Pair<String, Any?>): ContentValues {
    return ContentValues().apply {
        pairs.forEach {
            when (it.second) {
                null -> putNull(it.first)
                is String -> put(it.first, it.second as String)
                is Byte -> put(it.first, it.second as Byte)
                is Short -> put(it.first, it.second as Short)
                is Int -> put(it.first, it.second as Int)
                is Long -> put(it.first, it.second as Long)
                is Float -> put(it.first, it.second as Float)
                is Double -> put(it.first, it.second as Double)
                is Boolean -> put(it.first, it.second as Boolean)
                is ByteArray -> put(it.first, it.second as ByteArray)
            }
        }
    }
}

/**
 * Shorthand for [ContentValues.set].
 */
operator fun ContentValues.set(key: String, value: String?) {
    put(key, value)
}

/**
 * Shorthand for [ContentValues.set].
 */
operator fun ContentValues.set(key: String, value: Byte?) {
    put(key, value)
}

/**
 * Shorthand for [ContentValues.set].
 */
operator fun ContentValues.set(key: String, value: Short?) {
    put(key, value)
}

/**
 * Shorthand for [ContentValues.set].
 */
operator fun ContentValues.set(key: String, value: Int?) {
    put(key, value)
}

/**
 * Shorthand for [ContentValues.set].
 */
operator fun ContentValues.set(key: String, value: Long?) {
    put(key, value)
}

/**
 * Shorthand for [ContentValues.set].
 */
operator fun ContentValues.set(key: String, value: Float?) {
    put(key, value)
}

/**
 * Shorthand for [ContentValues.set].
 */
operator fun ContentValues.set(key: String, value: Double?) {
    put(key, value)
}

/**
 * Shorthand for [ContentValues.set].
 */
operator fun ContentValues.set(key: String, value: Boolean?) {
    put(key, value)
}

/**
 * Shorthand for [ContentValues.set].
 */
operator fun ContentValues.set(key: String, value: ByteArray?) {
    put(key, value)
}
