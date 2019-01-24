package in.nowfal.kdroidext.DateTimeUtils;

import java.util.Date;

/**
 * DateTimeUnits
 * Define units used by {@link DateTimeUtils#getDateDiff(Date, Date, DateTimeUnits)}
 * and also {@link DateTimeUtils#formatDate(long, DateTimeUnits)}
 *
 * @author Nowfal Salahudeen
 * @version 1.0
 */
@SuppressWarnings("WeakerAccess")
public enum DateTimeUnits {
    /**
     * Days
     */
    DAYS,
    /**
     * Hours
     */
    HOURS,
    /**
     * Minutes
     */
    MINUTES,
    /**
     * Seconds
     */
    SECONDS,
    /**
     * Milliseconds
     */
    MILLISECONDS,
}
