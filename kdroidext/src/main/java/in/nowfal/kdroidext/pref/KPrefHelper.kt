package `in`.nowfal.kdroidext.pref

import android.annotation.TargetApi
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.preference.PreferenceManager

/**
 * Created by Viral Patel on 01-Feb-16.
 */
class KPrefHelper {

    private val settings: SharedPreferences

    //region Default Values for various types
    //endregion

    //region Getters and Setters for default Values
    var intDefaultVal = 0
    var longDefaultVal: Long = 0
    var floatDefaultVal = 0f
    var isBoolDefaultVal = false
    var stringDefaultVal = ""
    var stringSetDefaultVal: Set<String>? = null
    //endregion

    //region Get Methods
    val all: Map<String, *>
        get() = settings.all
    //endregion

    //region Constructors
    /**
     * Uses the default shared preferences
     */
    constructor(context: Context) {
        settings = PreferenceManager.getDefaultSharedPreferences(context)
    }

    /**
     * creates a new preference file with specified name and mode
     * @param context
     * @param preferenceFileName
     * @param mode
     */
    @JvmOverloads
    constructor(context: Context, preferenceFileName: String, mode: Int = 0) {
        settings = context.getSharedPreferences(preferenceFileName, mode)
    }
    //endregion

    //#TODO Builder Pattern here can be helpful in making the use of this much elegant
    //region Put Methods
    fun putInt(key: String, `val`: Int) {
        settings.edit().putInt(key, `val`).apply()
    }

    fun putString(key: String, `val`: String) {
        settings.edit().putString(key, `val`).apply()
    }

    fun putBoolean(key: String, `val`: Boolean) {
        settings.edit().putBoolean(key, `val`).apply()
    }

    fun putFloat(key: String, `val`: Float) {
        settings.edit().putFloat(key, `val`).apply()
    }

    fun putLong(key: String, `val`: Long) {
        settings.edit().putLong(key, `val`).apply()
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    fun putStringSet(key: String, `val`: Set<String>) {
        settings.edit().putStringSet(key, `val`).apply()
    }

    fun getInt(key: String, defaultValue: Int): Int {
        return settings.getInt(key, defaultValue)
    }

    fun getInt(key: String): Int {
        return settings.getInt(key, intDefaultVal)
    }

    fun getString(key: String, defaultValue: String): String? {
        return settings.getString(key, defaultValue)
    }

    fun getString(key: String): String? {
        return settings.getString(key, stringDefaultVal)
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return settings.getBoolean(key, defaultValue)
    }

    fun getBoolean(key: String): Boolean {
        return settings.getBoolean(key, isBoolDefaultVal)
    }

    fun getFloat(key: String, defaultValue: Float): Float {
        return settings.getFloat(key, defaultValue)
    }

    fun getFloat(key: String): Float {
        return settings.getFloat(key, floatDefaultVal)
    }

    fun getLong(key: String, defaultValue: Long): Long {
        return settings.getLong(key, defaultValue)
    }

    fun getLong(key: String): Long {
        return settings.getLong(key, longDefaultVal)
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    fun getStringSet(key: String, defaultValue: Set<String>): Set<String>? {
        return settings.getStringSet(key, defaultValue)
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    fun getStringSet(key: String): Set<String>? {
        return settings.getStringSet(key, stringSetDefaultVal)
    }
    //endregion

    //region Listener registering and unregistering methods
    fun registerListener(listener: SharedPreferences.OnSharedPreferenceChangeListener) {
        settings.registerOnSharedPreferenceChangeListener(listener)
    }

    fun unregisterListener(listener: SharedPreferences.OnSharedPreferenceChangeListener) {
        settings.unregisterOnSharedPreferenceChangeListener(listener)
    }
    //endregion
}
