

package com.nowfal.kdroidext.kotx.types

import android.os.Parcel
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


/**
 * Calls [DateTime.parse] to parse a string in specified format.
 *
 * Throws [ParseException] if the string cannot be parsed.
 */
fun String.toDateTime(format: DateFormat): DateTime = DateTime.parse(this, format)

/**
 * Converts milliseconds since epoch to [DateTime].
 */
fun Long.toDateTime(timeZoneId: String? = null): DateTime = DateTime(this, timeZoneId)

/**
 * Converts [Calendar] to [DateTime].
 */
fun Calendar.toDateTime(): DateTime = DateTime(this)

/**
 * Converts milliseconds since epoch to [MutableDateTime].
 */
fun Long.toMutableDateTime(timeZoneId: String? = null): MutableDateTime =
        MutableDateTime(this, timeZoneId)

/**
 * Converts [Calendar] to [MutableDateTime].
 */
fun Calendar.toMutableDateTime(): MutableDateTime = MutableDateTime(this)

/**
 * Converts receiver to a [DateFormat] object, with optional [timeZoneId]
 * and [locale].
 */
fun String.toDateFormat(timeZoneId: String? = null,
                        locale: Locale? = Locale.getDefault()): DateFormat {
    return SimpleDateFormat(this, locale).apply {
        timeZone = timeZoneId?.let { TimeZone.getTimeZone(it) } ?: TimeZone.getDefault()
    }
}

/**
 * Extension method to read [DateTime] from a parcel.
 */
fun Parcel.readDateTime(): DateTime? =
        readParcelable(DateTime::class.java.classLoader)

/**
 * Extension method to read [MutableDateTime] from a parcel.
 */
fun Parcel.readMutableDateTime(): DateTime? =
        readParcelable(MutableDateTime::class.java.classLoader)

/**
 * Extension method to write [DateTime] to a parcel.
 */
fun Parcel.writeDateTime(value: DateTime?) {
    writeParcelable(value, 0)
}
