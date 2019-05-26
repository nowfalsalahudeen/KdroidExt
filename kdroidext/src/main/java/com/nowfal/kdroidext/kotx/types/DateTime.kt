
package com.nowfal.kdroidext.kotx.types

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


/**
 * [DateTime] is a thin wrapper around [Calendar], designed to simplify
 * the functionality and odd syntax of the latter. It delegates most of its
 * internal functionality to Calendar in a way that maintains the object's
 * immutability.
 *
 * While [DateTime] mostly mirrors [Calendar] in its functionality, it does
 * differ in some aspects - for instance, the first month in [DateTime] is
 * numbered 1, not zero.
 */
open class DateTime protected constructor(calendar: Calendar,
                                          clone: Boolean = false): Parcelable, Comparable<DateTime> {

    /** @suppress */
    protected val calendar: Calendar = if (clone) calendar.clone() as Calendar else calendar

    /**
     * Constructs a new [DateTime] object using specified time zone.
     *
     * If [timeZoneId] is null, uses the default time zone.
     */
    constructor(timeZoneId: String? = null): this(newCalendar(timeZoneId))

    /**
     * Constructs a new [DateTime] object based on specified [calendar]
     * instance.
     */
    constructor(calendar: Calendar): this(calendar, true)

    /**
     * Constructs a new [DateTime] object based on specified [dateTime]
     * instance.
     */
    constructor(dateTime: DateTime): this(dateTime.toCalendar())

    /**
     * Constructs a new [DateTime] object with specified number of milliseconds
     * since epoch.
     *
     * New [DateTime] will use time zone with specified [timeZoneId], or the
     * default time zone if null.
     */
    @JvmOverloads
    constructor(millis: Long,
                timeZoneId: String? = null) : this(newCalendar(timeZoneId).apply {
        timeInMillis = millis
    })

    /**
     * Constructs a new [DateTime] object with specified [year], [month] and [day],
     * and optionally [hours], [minutes], [seconds] and [milliseconds].
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
     * Returns UTC time in milliseconds since epoch.
     *
     * To get milliseconds within seconds, see [milli].
     */
    open val millis: Long
        get() = calendar.timeInMillis

    /**
     * Returns year.
     */
    open val year: Int
        get() = calendar.get(Calendar.YEAR)

    /**
     * Returns month of year - first month is 1.
     *
     * Note that [DateTime] mimics the behavior of JodaTime, in that unlike
     * [Calendar], first month is 1, not zero.
     */
    open val month: Int
        get() = calendar.get(Calendar.MONTH) + 1

    /**
     * Returns day of month - first day is 1.
     */
    open val day: Int
        get() = calendar.get(Calendar.DAY_OF_MONTH)

    /**
     * Returns day of week - constants are identical to those used by [Calendar].
     */
    open val dayOfWeek: Int
        get() = calendar.get(Calendar.DAY_OF_WEEK)

    /**
     * Returns hour within day - between 0 to 23.
     */
    open val hour: Int
        get() = calendar.get(Calendar.HOUR_OF_DAY)

    /**
     * Returns minute within hour.
     */
    open val minute: Int
        get() = calendar.get(Calendar.MINUTE)

    /**
     * Returns second within minute.
     */
    open val second: Int
        get() = calendar.get(Calendar.SECOND)

    /**
     * Returns millisecond within second.
     */
    open val milli: Int
        get() = calendar.get(Calendar.MILLISECOND)

    /**
     * Returns the time zone id for the instance.
     */
    open val timeZoneId: String
        get() = calendar.timeZone.id

    /**
     * Returns an instance of [DateTime] resulting from adding [value]
     * to year component. Value may be negative.
     *
     * As this method uses [Calendar] to do math, the date/time components
     * will automatically be adjusted in case of an over/underflow.
     */
    fun addedYears(value: Int): DateTime = DateTime(toCalendar().apply {
        add(Calendar.YEAR, value)
    })

    /**
     * Returns an instance of [DateTime] resulting from adding [value]
     * to month component. Value may be negative.
     *
     * As this method uses [Calendar] to do math, the date/time components
     * will automatically be adjusted in case of an over/underflow.
     */
    fun addedMonths(value: Int): DateTime = DateTime(toCalendar().apply {
        add(Calendar.MONTH, value)
    })

    /**
     * Returns an instance of [DateTime] resulting from adding [value]
     * to day component. Value may be negative.
     *
     * As this method uses [Calendar] to do math, the date/time components
     * will automatically be adjusted in case of an over/underflow.
     */
    fun addedDays(value: Int) = DateTime(toCalendar().apply {
        add(Calendar.DAY_OF_MONTH, value)
    })

    /**
     * Returns an instance of [DateTime] resulting from adding [value]
     * to hour component. Value may be negative.
     *
     * As this method uses [Calendar] to do math, the date/time components
     * will automatically be adjusted in case of an over/underflow.
     */
    fun addedHours(value: Int) = DateTime(toCalendar().apply {
        add(Calendar.HOUR_OF_DAY, value)
    })

    /**
     * Returns an instance of [DateTime] resulting from adding [value]
     * to minute component. Value may be negative.
     *
     * As this method uses [Calendar] to do math, the date/time components
     * will automatically be adjusted in case of an over/underflow.
     */
    fun addedMinutes(value: Int) = DateTime(toCalendar().apply {
        add(Calendar.MINUTE, value)
    })

    /**
     * Returns an instance of [DateTime] resulting from adding [value]
     * to second component. Value may be negative.
     *
     * As this method uses [Calendar] to do math, the date/time components
     * will automatically be adjusted in case of an over/underflow.
     */
    fun addedSeconds(value: Int) = DateTime(toCalendar().apply {
        add(Calendar.SECOND, value)
    })

    /**
     * Returns an instance of [DateTime] resulting from adding [value]
     * to millisecond component. Value may be negative.
     *
     * As this method uses [Calendar] to do math, the date/time components
     * will automatically be adjusted in case of an over/underflow.
     */
    fun addedMillis(value: Int) = DateTime(toCalendar().apply {
        add(Calendar.MILLISECOND, value)
    })

    /**
     * Returns an instance of [DateTime] with hour, minute, second and
     * millisecond components set to zero.
     */
    fun truncatedTime() = DateTime(toCalendar().apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    })

    /**
     * Returns UTC representation of the [DateTime] instance.
     */
    fun toUTC(): DateTime = toTimeZone("Universal")

    /**
     * Returns the [DateTime] instance in specified time zone.
     */
    fun toTimeZone(timeZoneId: String?): DateTime = DateTime(toCalendar().apply {
        timeZone = idToTimeZone(timeZoneId)
    })

    /**
     * Returns a clone of the [Calendar] object.
     */
    fun toCalendar(): Calendar = calendar.clone() as Calendar

    /**
     * Returns a mutable version of the instance.
     */
    open fun toMutable(): MutableDateTime = MutableDateTime(this)

    /**
     * Returns true if the specified [DateTime], [Calendar], or [Long] (number
     * of milliseconds from epoch) is equal to instance.
     */
    override fun equals(other: Any?): Boolean = when (other) {
        is DateTime -> calendar == other.calendar
        is Calendar -> calendar == other
        is Long -> calendar.timeInMillis == other
        else -> false
    }

    /**
     * Returns true if receiver represents the same time as [other].
     *
     * Note that this is different than [equals], since that method takes
     * time zones into account. [on] is time zone agnostic.
     */
    infix fun on(other: DateTime): Boolean = calendar.compareTo(other.calendar) == 0

    /**
     * Returns true if receiver represents the same time as [other].
     *
     * Note that this is different than [equals], since that method takes
     * time zones into account. [on] is time zone agnostic.
     */
    infix fun on(other: Calendar): Boolean = calendar.compareTo(other) == 0

    /**
     * Returns true if receiver represents the same time as [other].
     */
    infix fun on(other: Long): Boolean = calendar.timeInMillis == other

    /**
     * Returns true if receiver falls before [other].
     */
    infix fun before(other: DateTime): Boolean = calendar < other.calendar

    /**
     * Returns true if receiver falls before [other].
     */
    infix fun before(other: Calendar): Boolean = calendar < other

    /**
     * Returns true if receiver falls before [other] - given in milliseconds
     * since epoch.
     */
    infix fun before(other: Long): Boolean = calendar.timeInMillis < other

    /**
     * Returns true if receiver falls on or before [other].
     */
    infix fun onOrBefore(other: DateTime): Boolean = calendar <= other.calendar

    /**
     * Returns true if receiver falls on or before [other].
     */
    infix fun onOrBefore(other: Calendar): Boolean = calendar <= other

    /**
     * Returns true if receiver falls on or before [other] - given in
     * milliseconds since epoch.
     */
    infix fun onOrBefore(other: Long): Boolean = calendar.timeInMillis <= other

    /**
     * Returns true if receiver falls after [other].
     */
    infix fun after(other: DateTime): Boolean = calendar > other.calendar

    /**
     * Returns true if receiver falls after [other].
     */
    infix fun after(other: Calendar): Boolean = calendar > other

    /**
     * Returns true if receiver falls after [other] - given in
     * milliseconds since epoch.
     */
    infix fun after(other: Long): Boolean = calendar.timeInMillis > other

    /**
     * Returns true if receiver falls on or after [other].
     */
    infix fun onOrAfter(other: DateTime): Boolean = calendar >= other.calendar

    /**
     * Returns true if receiver falls on or after [other].
     */
    infix fun onOrAfter(other: Calendar): Boolean = calendar >= other

    /**
     * Returns true if receiver falls on or after [other] - given in
     * milliseconds since epoch.
     */
    infix fun onOrAfter(other: Long): Boolean = calendar.timeInMillis >= other

    /**
     * Returns year as the first component.
     */
    operator fun component1() = year

    /**
     * Returns month as the second component.
     */
    operator fun component2() = month

    /**
     * Returns day of month as the third component.
     */
    operator fun component3() = day

    /**
     * Returns hour of day as the fourth component.
     */
    operator fun component4() = hour

    /**
     * Returns minute of hour as the fifth component.
     */
    operator fun component5() = minute

    /**
     * Returns second of minute as the sixth component.
     */
    operator fun component6() = second

    /**
     * Returns millisecond of minute as the seventh component.
     */
    operator fun component7() = milli

    /**
     * Returns -1, 0, 1 if the instance is less than, equal to, or
     * greater than specified [Calendar instance][other], respectively.
     */
    fun compareTo(other: Calendar): Int = calendar.compareTo(other)

    /**
     * Returns -1, 0, 1 if the instance is less than, equal to, or
     * greater than specified [number of milliseconds][other] from epoch,
     * respectively.
     */
    fun compareTo(other: Long): Int = calendar.timeInMillis.compareTo(other)

    /**
     * Returns -1, 0, 1 if the instance is less than, equal to, or greater
     * than [other] instance, respectively.
     */
    override fun compareTo(other: DateTime): Int =
            calendar.compareTo(other.calendar)

    /**
     * Returns hash code of the instance - which is equal to its [Calendar]'s
     * hashCode value.
     */
    override fun hashCode(): Int = calendar.hashCode()

    /**
     * Returns a string representation of [DateTime] instance in specified
     * [format] and optionally, [time zone][timeZoneId].
     *
     * If [timeZoneId] is omitted, the default time zone is used.
     * Format is specified in [SimpleDateFormat] syntax.
     */
    fun toString(format: String, timeZoneId: String? = null) =
            toString(getDateFormat(format, timeZoneId))

    /**
     * Returns a string representation of [DateTime] instance in
     * specified [DateFormat].
     */
    fun toString(format: DateFormat): String = format.format(calendar.time)

    /**
     * Returns a string representation of [DateTime] instance in RFC-3339
     * format, in UTC - e.g. "1983-07-15T08:00:59Z".
     */
    override fun toString() = toString(FORMAT_RFC_3339)

    /**
     * Returns zero.
     */
    override fun describeContents() = 0

    /**
     * Implements [Parcelable.writeToParcel] by writing number of milliseconds
     * since epoch and time zone id.
     */
    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeLong(calendar.timeInMillis)
        dest.writeString(calendar.timeZone.id)
    }

    companion object {
        const val JANUARY   = 1
        const val FEBRUARY  = 2
        const val MARCH     = 3
        const val APRIL     = 4
        const val MAY       = 5
        const val JUNE      = 6
        const val JULY      = 7
        const val AUGUST    = 8
        const val SEPTEMBER = 9
        const val OCTOBER   = 10
        const val NOVEMBER  = 11
        const val DECEMBER  = 12

        const val SUNDAY    = Calendar.SUNDAY
        const val MONDAY    = Calendar.MONDAY
        const val TUESDAY   = Calendar.TUESDAY
        const val WEDNESDAY = Calendar.WEDNESDAY
        const val THURSDAY  = Calendar.THURSDAY
        const val FRIDAY    = Calendar.FRIDAY
        const val SATURDAY  = Calendar.SATURDAY

        /**
         * [DateFormat] instance representing RFC-3339.
         */
        val FORMAT_RFC_3339: DateFormat by lazy {
            SimpleDateFormat(if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                "yyyy-MM-dd'T'HH:mm:ssXXX" else "yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault())
        }

        /** @suppress */
        @JvmStatic
        protected fun newCalendar(id: String? = null): Calendar =
                GregorianCalendar.getInstance(idToTimeZone(id))

        /** @suppress */
        @JvmStatic
        protected fun idToTimeZone(id: String? = null): TimeZone =
                id?.let { TimeZone.getTimeZone(id) } ?: TimeZone.getDefault()

        /** @suppress */
        @JvmStatic
        protected fun getDateFormat(format: String, timeZoneId: String?): DateFormat =
                SimpleDateFormat(format, Locale.getDefault()).apply {
                    timeZone = idToTimeZone(timeZoneId)
                }

        /**
         * Returns [DateTime] instance representing current time and date.
         */
        @get:JvmName("now")
        @JvmStatic
        val now
            get() = DateTime()

        /**
         * Returns [DateTime] instance representing date. Time components will
         * be set to zero.
         */
        @get:JvmName("today")
        @JvmStatic
        val today
            get() = DateTime(newCalendar().apply {
                set(Calendar.HOUR_OF_DAY, 0)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            })

        /**
         * Parses [string] in specified [format] and returns a [DateTime]
         * instance.
         *
         * Throws [ParseException] if the string cannot be parsed.
         */
        @JvmStatic
        fun parse(string: String, format: DateFormat): DateTime =
                DateTime(newCalendar().apply { time = format.parse(string) })

        /**
         * Parses [string] in specified [format] and returns a [DateTime]
         * instance, or null if parsing fails.
         */
        @JvmStatic
        fun tryParse(string: String, format: DateFormat): DateTime? = try {
            DateTime(newCalendar().apply { time = format.parse(string) })
        } catch (e: ParseException) { null }

        /**
         * Implementation of [Parcelable.Creator].
         */
        @Suppress("unused")
        @JvmField
        val CREATOR: Parcelable.Creator<DateTime> = object : Parcelable.Creator<DateTime> {
            override fun createFromParcel(source: Parcel): DateTime = DateTime(source)
            override fun newArray(size: Int): Array<DateTime?> = arrayOfNulls(size)
        }
    }
}
