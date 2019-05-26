

package com.nowfal.kdroidext.kotx.ext.basic

import android.annotation.SuppressLint
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.text.Html
import android.text.TextUtils
import android.util.Log
import java.net.URL
import java.net.URLDecoder
import java.net.URLEncoder


/**
 * Converts HTML text to a formatted string as performed by [Html.fromHtml].
 * Suppresses any exceptions.
 *
 * @return An HTML-formatted [CharSequence], null in case of an error.
 */
@SuppressLint("NewApi")
fun String.htmlAsSpannable(tagHandler: Html.TagHandler? = null): CharSequence? =
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> try {
                Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY,
                        null, tagHandler)
            } catch (e: RuntimeException) {
                // Malformed HTML causes errors
                Log.v("String.kt", "htmlAsSpannable failed", e)
                null
            }
            else -> try {
                @Suppress("DEPRECATION")
                Html.fromHtml(this, null, tagHandler)
            } catch (e: RuntimeException) {
                Log.v("String.kt", "htmlAsSpannable failed", e)
                null
            }
        }

/**
 * HTML-encodes a string by calling [TextUtils.htmlEncode].
 *
 * @return HTML-encoded version of the string (UTF-8 by default).
 */
fun String.htmlEncode(): String = TextUtils.htmlEncode(this)

/**
 * URL-encodes a string by calling [URLEncoder.encode].
 *
 * @param encoding String encoding.
 *
 * @return URL-encoded version of the string.
 */
fun String.urlEncode(encoding: String = "UTF-8"): String = URLEncoder.encode(this, encoding)

/**
 * Decodes a URL-encoded string by calling [URLDecoder.decode].
 *
 * @param encoding String encoding (UTF-8 by default).
 *
 * @return Decoded version of URL-encoded string.
 */
fun String.urlDecode(encoding: String = "UTF-8"): String = URLDecoder.decode(this, encoding)

/**
 * Converts a String to [Uri] by calling [Uri.parse].
 */
fun String.toUri(): Uri = Uri.parse(this)

/**
 * Converts a String to [Uri] by calling [Uri.parse]; returning null if
 * the string cannot be parsed.
 */
fun String.toUriOrNull(): Uri? =
        try { Uri.parse(this) } catch(e: Exception) { null }

/**
 * Compares two nullable [String] objects.
 */
fun String?.compareTo(other: String?) = when {
    this == null && other == null -> 0
    other == null -> -1
    this == null -> 1
    else -> this.compareTo(other)
}

/**
 * Converts a String to [URL], with an optional URL [context].
 */
fun String.toURL(context: URL? = null): URL = URL(context, this)

/**
 * Converts a String to [URL], with an optional URL [context] returning null
 * if string cannot be parsed.
 */
fun String.toURLOrNull(context: URL? = null): URL? = try {
    URL(context, this) } catch(e: Exception) { null }

/**
 * Uses [Color.parseColor] to convert string representation of a color
 * (such as "#cedefa") to integer representation.
 */
fun String.toColorInt(): Int = Color.parseColor(this)

/**
 * Uses [Color.parseColor] to convert string representation of a color
 * to integer, or null if string can't be parsed.
 */
fun String.toColorIntOrNull(): Int? = try {
    Color.parseColor(this) } catch(e: Exception) { null }
