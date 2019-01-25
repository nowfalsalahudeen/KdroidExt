package com.nowfal.kdroidext.DateTimeUtils;

/**
 * DateTimeFormat
 *  Patterns used to parse given date {@link DateTimeUtils} will use those pattern
 *
 * @author Nowfal Salahudeen
 * @version 1.0
 */
@SuppressWarnings("WeakerAccess")
public class DateTimeFormat {
    /**
     * Typical MySqL/SQL dateTime format with dash as separator
     */
    public static final String DATE_TIME_PATTERN_1  = "yyyy-MM-dd HH:mm:ss";
    /**
     * Typical MySqL/SQL dateTime format with slash as seperator
     */
    public static final String DATE_TIME_PATTERN_2  = "dd/MM/yyyy HH:mm:ss";
    /**
     * Typical MySqL/SQL date format with dash as separator
     */
    public static final String DATE_PATTERN_1 = "yyyy-MM-dd";
    /**
     * Typical MySqL/SQL date format with slash as seperator
     */
    public static final String DATE_PATTERN_2 = "dd/MM/yyyy";
    /**
     * Time format full
     */
    public static final String TIME_PATTERN_1 = "HH:mm:ss";
}
