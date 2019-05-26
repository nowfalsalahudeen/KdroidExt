

package com.nowfal.kdroidext.kotx.ext.collections

import android.util.LruCache


/**
 * Gets value with specific key from the cache. If the value is not present,
 * calls [defaultValue] to obtain a non-null value which is placed into the
 * cache, then returned.
 *
 * This method is thread-safe.
 */
inline fun <K, V> LruCache<K, V>.getOrPut(key: K, defaultValue: () -> V): V {
    synchronized(this) {
        this[key]?.let { return it }
        return defaultValue().apply { put(key, this) }
    }
}

/**
 * Gets value with specific key from the cache. If the value is not present,
 * calls [defaultValue] to obtain a value which is placed into the cache
 * if not null, then returned.
 *
 * This method is thread-safe.
 */
inline fun <K, V> LruCache<K, V>.getOrPutNotNull(key: K, defaultValue: () -> V?): V? {
    synchronized(this) {
        this[key]?.let { return it }
        return defaultValue()?.apply { put(key, this) }
    }
}

/**
 * Returns an array containing the keys in the cache.
 */
fun <V> LruCache<Int, V>.keys(): IntArray =
        snapshot().keys.toIntArray()

/**
 * Returns an array containing the keys in the cache.
 */
fun <V> LruCache<Long, V>.keys(): LongArray =
        snapshot().keys.toLongArray()

/**
 * Returns an array containing the keys in the cache.
 */
inline fun <reified K, V> LruCache<K, V>.keys(): Array<K> =
        snapshot().keys.toTypedArray()
