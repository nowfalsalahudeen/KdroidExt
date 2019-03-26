package com.nowfal.kdroidext.humanize.duration.languages

interface LanguageDictionary {
    fun provide(key: String, count: Double): String
}