
package com.nowfal.kdroidext.kotx.types

import android.os.Parcel
import android.os.Parcelable
import java.text.DateFormat
import java.text.ParseException
import java.util.*


/**
 * [MutableDateTime] is a mutable subclass of [DateTime].
 *
 * See [DateTime] for specific details.
 */
class MutableDateTime private constructor(calendar: Calendar,
                                          clone: Boolean = false): DateTime(calendar, clone) {

    /**
     * Constructs a new [MutableDateTime] object using specified time zone.
     *
     * If [timeZoneId] is null, uses the default time zone.
     */
    @JvmOverloads
    constructor(timeZoneId: String? = null): this(newCalendar(timeZoneId))

    /**
     * Constructs a new [MutableDateTime] object based on specified [calendar]
     * instance.
     */
    constructor(calendar: Calendar): this(calendar, true)

    /**
     * Constructs a new [MutableDateTime] object based on specified [dateTime]
     * instance.
     */
    constructor(dateTime: DateTime): this(dateTime.toCalendar())

    /**
     * Constructs a new [MutableDateTime] object with specified number of
     * milliseconds since epoch.
     *
     * Uses time zone with specified [timeZoneId], or the default time zone
     * if null.
     */
    @JvmOverloads
    constructor(millis: Long,
                timeZoneId: String? = null) : this(newCalendar(timeZoneId).apply {
        timeInMillis = millis
    })

    /**
     * Constructs a new [MutableDateTime] object with specified [year],
     * [month] and [day], and optionally [hours], [minutes], [seconds]
     * and [milliseconds].
     *
     * Uses time zone with specified [timeZoneId], or the default time zone
     * if null.
     */
    @JvmOverloads
    constructor(year: Int, month: Int, day: Int,
                hours: Int = 0, minutes: Int = 0, seconds: Int = 0,
                milliseconds: Int = 0,
                timeZoneId: String? = null): this(newCalendar(timeZoneId).apply {
        set(Calendar.YEAR, year)
        set(Calendar.MONTH, month - 1) // to match JodaTime
        set(Calendar.DAY_OF_MONTH, day)
        set(Calendar.HOUR_OF_DAY, hours)
        set(Calendar.MINUTE, minutes)
        set(Calendar.SECOND, seconds)
        set(Calendar.MILLISECOND, milliseconds)
    })

    /**
     * Constructor for [Parcelable.Creator].
     */
    private constructor(source: Parcel): this(
            millis = source.readLong(),
            timeZoneId = source.readString())

    /**
     * Gets/sets time in milliseconds since epoch.
     *
     * To get milliseconds within seconds, see [milli].
     */
    override var millis: Long
        get() = super.millis
        set(value) {
            calendar.timeInMillis = value
        }

    /**
     * Gets/sets year.
     */
    override var year: Int
        get() = super.year
        set(value) {
            calendar.set(Calendar.YEAR, value)
        }

    /**
     * Gets/sets month of year - first month is 1.
     *
     * Note that [DateTime] mimics the behavior of JodaTime, in that unlike
     * [Calendar], first month is 1, not zero.
     */
    override var month: Int
        get() = super.month
        set(value) {
            calendar.set(Calendar.MONTH, value - 1)
        }

    /**
     * Gets/sets day of month - first day is 1.
     */
    override var day: Int
        get() = super.day
        set(value) {
            calendar.set(Calendar.DAY_OF_MONTH, value)
        }

    /**
     * Gets/sets day of week - constants are identical to those used by [Calendar].
     */
    override var dayOfWeek: Int
        get() = super.day
        set(value) {
            calendar.set(Calendar.DAY_OF_WEEK, value)
        }

    /**
     * Gets/sets hour within day - between 0 to 23.
     */
    override var hour: Int
        get() = super.hour
        set(value) {
            calendar.set(Calendar.HOUR_OF_DAY, value)
        }

    /**
     * Gets/sets minute within hour.
     */
    override var minute: Int
        get() = super.minute
        set(value) {
            calendar.set(Calendar.MINUTE, value)
        }

    /**
     * Gets/sets second within minute.
     */
    override var second: Int
        get() = super.second
        set(value) {
            calendar.set(Calendar.SECOND, value)
        }

    /**
     * Gets/sets millisecond within second.
     */
    override var milli: Int
        get() = super.milli
        set(value) {
            calendar.set(Calendar.MILLISECOND, value)
        }

    /**
     * Gets/sets the timezone id for the instance.
     */
    override var timeZoneId: String
        get() = calendar.timeZone.id
        set(value) { calendar.timeZone = idToTimeZone(value) }

    /**
     * Updates [MutableDateTime] instance by adding [value]
     * to year component. Value may be negative.
     *
     * As this method uses [Calendar] to do math, the date/time components
     * will automatically be adjusted in case of an over/underflow.
     */
    fun addYears(value: Int) {
        calendar.add(Calendar.YEAR, value)
    }

    /**
     * Updates [MutableDateTime] instance by adding [value]
     * to month component. Value may be negative.
     *
     * As this method uses [Calendar] to do math, the date/time components
     * will automatically be adjusted in case of an over/underflow.
     */
    fun addMonths(value: Int) {
        calendar.add(Calendar.MONTH, value)
    }

    /**
     * Updates [MutableDateTime] instance by adding [value]
     * to day component. Value may be negative.
     *
     * As this method uses [Calendar] to do math, the date/time components
     * will automatically be adjusted in case of an over/underflow.
     */
    fun addDays(value: Int) {
        calendar.add(Calendar.DAY_OF_MONTH, value)
    }

    /**
     * Updates [MutableDateTime] instance by adding [value]
     * to hour component. Value may be negative.
     *
     * As this method uses [Calendar] to do math, the date/time components
     * will automatically be adjusted in case of an over/underflow.
     */
    fun addHours(value: Int) {
        calendar.add(Calendar.HOUR_OF_DAY, value)
    }

    /**
     * Updates [MutableDateTime] instance by adding [value]
     * to minute component. Value may be negative.
     *
     * As this method uses [Calendar] to do math, the date/time components
     * will automatically be adjusted in case of an over/underflow.
     */
    fun addMinutes(value: Int) {
        calendar.add(Calendar.MINUTE, value)
    }

    /**
     * Updates [MutableDateTime] instance by adding [value]
     * to second component. Value may be negative.
     *
     * As this method uses [Calendar] to do math, the date/time components
     * will automatically be adjusted in case of an over/underflow.
     */
    fun addSeconds(value: Int) {
        calendar.add(Calendar.SECOND, value)
    }

    /**
     * Updates [MutableDateTime] instance by adding [value]
     * to millisecond component. Value may be negative.
     *
     * As this method uses [Calendar] to do math, the date/time components
     * will automatically be adjusted in case of an over/underflow.
     */
    fun addMillis(value: Int) {
        calendar.add(Calendar.MILLISECOND, value)
    }

    /**
     * Updates [MutableDateTime] instance by setting hour, minute, second and
     * millisecond components to zero.
     */
    fun truncateTime() {
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
    }

    /**
     * Because [MutableDateTime] itself is mutable, returns the receiver
     * instance.
     */
    override fun toMutable(): MutableDateTime = this

    /**
     * Returns an immutable version of the instance.
     */
    fun toImmutable(): DateTime = DateTime(this)

    companion object {
        /**
         * Returns [MutableDateTime] instance representing current time and
         * date.
         */
        @get:JvmName("now")
        @JvmStatic
        val now
            get() = MutableDateTime()

        /**
         * Returns [MutableDateTime] instance representing date. Time
         * components will be set to zero.
         */
        @get:JvmName("today")
        @JvmStatic
        val today
            get() = MutableDateTime().apply { truncateTime() }

        /**
         * Parses [string] in specified [format] and returns a [MutableDateTime]
         * instance.
         *
         * Throws [ParseException] if the string cannot be parsed.
         */
        @JvmStatic
        fun parse(string: String, format: DateFormat): MutableDateTime =
                MutableDateTime(newCalendar().apply { time = format.parse(string) })

        /**
         * Parses [string] in specified [format] and returns a [MutableDateTime]
         * instance, or null if parsing fails.
         */
        @JvmStatic
        fun tryParse(string: String, format: DateFormat): MutableDateTime? = try {
            MutableDateTime(newCalendar().apply { time = format.parse(string) })
        } catch (e: ParseException) { null }

        /**
         * Implementation of [Parcelable.Creator].
         */
        @JvmField
        val CREATOR: Parcelable.Creator<MutableDateTime> = object : Parcelable.Creator<MutableDateTime> {
            override fun createFromParcel(source: Parcel): MutableDateTime = MutableDateTime(source)
            override fun newArray(size: Int): Array<MutableDateTime?> = arrayOfNulls(size)
        }
    }
}
