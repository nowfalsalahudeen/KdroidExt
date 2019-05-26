

package com.nowfal.kdroidext.kotx.ext.basic

import android.util.Base64
import java.security.MessageDigest


/**
 * Returns the byte array as a hexadecimal string.
 *
 * @return Hexadecimal representation of the byte array.
 */
fun ByteArray.toHexString(): String {
    val hexArray = "0123456789abcdef".toCharArray()
    val hexChars = CharArray(size * 2)
    for (i in 0 until size) {
        val b = this[i].toInt() and 0xff
        hexChars[i * 2] = hexArray[b ushr 4]
        hexChars[i * 2 + 1] = hexArray[b and 0x0f]
    }

    return String(hexChars)
}

/**
 * Returns Base64 representation of the byte array by calling
 * [Base64.encodeToString] with specified flags.
 *
 * @param flags Flags to pass to Base64 encoder. Default is [Base64.NO_WRAP].
 *
 * @return Base64 representation of the byte array.
 */
fun ByteArray.toBase64String(flags: Int = Base64.NO_WRAP): String
        = Base64.encodeToString(this, flags)

/**
 * Computes MD5 hash for the byte array using [MessageDigest].
 *
 * @return MD5 hash for the byte array.
 */
fun ByteArray.md5(): ByteArray = MessageDigest.getInstance("MD5").apply {
    update(this@md5, 0, size)
}.digest()

/**
 * Computes SHA1 hash for the byte array using [MessageDigest].
 *
 * @return SHA1 hash for the byte array.
 */
fun ByteArray.sha1(): ByteArray = MessageDigest.getInstance("SHA-1").apply {
    update(this@sha1, 0, size)
}.digest()
