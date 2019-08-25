/*
 * Copyright 2019 Nowfal Salahudeen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.nowfal.kdroidext.kex

import java.util.*

fun Calendar.getDayOfWeek(date: Date): Int {
    val c = Calendar.getInstance()
    c.time = date
    return c.get(Calendar.DAY_OF_WEEK)
}

fun Calendar.getDayOfWeek(): Int {
    return getDayOfWeek(Date())
}

fun Calendar.getDayOfMonth(date: Date): Int {
    val c = Calendar.getInstance()
    c.time = date
    return c.get(Calendar.DAY_OF_MONTH)
}

fun Calendar.getDayOfMonth(): Int {
    return getDayOfMonth(Date())
}

fun Calendar.getDayOfWeekInMonth(date: Date): Int {
    val c = Calendar.getInstance()
    c.time = date
    return c.get(Calendar.DAY_OF_WEEK_IN_MONTH)
}

fun Calendar.getDayOfWeekInMonth(): Int {
    return getDayOfWeekInMonth(Date())
}

fun Calendar.getYear(date: Date): Int {
    val c = Calendar.getInstance()
    c.time = date
    return c.get(Calendar.YEAR)
}

fun Calendar.getYear(): Int {
    return getYear(Date())
}

fun Calendar.getDayOfYear(date: Date): Int {
    val c = Calendar.getInstance()
    c.time = date
    return c.get(Calendar.DAY_OF_YEAR)
}

fun Calendar.getDayOfYear(): Int {
    return getDayOfYear(Date())
}

fun Calendar.getWeekOfYear(date: Date): Int {
    val c = Calendar.getInstance()
    c.time = date
    return c.get(Calendar.WEEK_OF_YEAR)
}

fun Calendar.getWeekOfYear(): Int {
    return getWeekOfYear(Date())
}

fun Calendar.getWeekOfMonth(date: Date): Int {
    val c = Calendar.getInstance()
    c.time = date
    return c.get(Calendar.WEEK_OF_MONTH)
}

fun Calendar.getWeekOfMonth(): Int {
    return getWeekOfMonth(Date())
}

fun Calendar.getHourOfDay(date: Date): Int {
    val c = Calendar.getInstance()
    c.time = date
    return c.get(Calendar.HOUR_OF_DAY)
}

fun Calendar.getHourOfDay(): Int {
    return getHourOfDay(Date())
}

fun Calendar.getHour(date: Date): Int {
    val c = Calendar.getInstance()
    c.time = date
    return c.get(Calendar.HOUR)
}

fun Calendar.getHour(): Int {
    return getHour(Date())
}

fun Calendar.getMinute(date: Date): Int {
    val c = Calendar.getInstance()
    c.time = date
    return c.get(Calendar.MINUTE)
}

fun Calendar.getMinute(): Int {
    return getMinute(Date())
}

fun Calendar.getSecond(date: Date): Int {
    val c = Calendar.getInstance()
    c.time = date
    return c.get(Calendar.SECOND)
}

fun Calendar.getSecond(): Int {
    return getSecond(Date())
}

fun Calendar.getMillisecond(date: Date): Int {
    val c = Calendar.getInstance()
    c.time = date
    return c.get(Calendar.MILLISECOND)
}

fun Calendar.getMillisecond(): Int {
    return getMillisecond(Date())
}