

package com.nowfal.kdroidext.kotx.ext.content

import android.content.SharedPreferences
import kotlin.reflect.KProperty


/**
 * Creates a new {@link SharedPreferences.Editor}, calls the specified [block],
 * then calls {@link SharedPreferences.Editor#apply}
 */
fun SharedPreferences.edit(block: SharedPreferences.Editor.() -> Unit) {
    edit().apply { block(this) }.apply()
}

/**
 * Write a nullable String value in the preferences editor,
 * to be written back once {@link SharedPreferences.Editor#commit} or
 * {@link SharedPreferences.Editor#apply} are called.
 *
 * @param key Name of the preference.
 * @param value New value.
 */
fun SharedPreferences.Editor.put(key: String, value: String?) {
    putString(key, value)
}

/**
 * Write a nullable set of Strings in the preferences editor,
 * to be written back once {@link SharedPreferences.Editor#commit} or
 * {@link SharedPreferences.Editor#apply} are called.
 *
 * @param key Name of the preference.
 * @param value New value.
 */
fun SharedPreferences.Editor.put(key: String, value: Set<String?>?) {
    putStringSet(key, value)
}

/**
 * Write an integer value in the preferences editor,
 * to be written back once {@link SharedPreferences.Editor#commit} or
 * {@link SharedPreferences.Editor#apply} are called.
 *
 * @param key Name of the preference.
 * @param value New value.
 */
fun SharedPreferences.Editor.put(key: String, value: Int) {
    putInt(key, value)
}

/**
 * Write a float value in the preferences editor,
 * to be written back once {@link SharedPreferences.Editor#commit} or
 * {@link SharedPreferences.Editor#apply} are called.
 *
 * @param key Name of the preference.
 * @param value New value.
 */
fun SharedPreferences.Editor.put(key: String, value: Float) {
    putFloat(key, value)
}

/**
 * Write a long value in the preferences editor,
 * to be written back once {@link SharedPreferences.Editor#commit} or
 * {@link SharedPreferences.Editor#apply} are called.
 *
 * @param key Name of the preference.
 * @param value New value.
 */
fun SharedPreferences.Editor.put(key: String, value: Long) {
    putLong(key, value)
}

/**
 * Write a boolean value in the preferences editor,
 * to be written back once {@link SharedPreferences.Editor#commit} or
 * {@link SharedPreferences.Editor#apply} are called.
 *
 * @param key Name of the preference.
 * @param value New value.
 */
fun SharedPreferences.Editor.put(key: String, value: Boolean) {
    putBoolean(key, value)
}

/**
 * Allows reading of [SharedPreferences] setting via delegated property.
 *
 * Note that using the property on any type not supported by
 * [SharedPreferences] natively will generate an error.
 *
 * Example:
 *
 * ```
 * var color: String? by Preferences.sharedPrefs
 * // Corresponds to Preferences.sharedPrefs.getString("color", "red")
 * val colorWithDefault: String?
 *     get() = color ?: "red"
 *
 * Log.v(LOG_TAG, "color: $colorWithDefault")
 * color = "blue"
 * Log.v(LOG_TAG, "color: $colorWithDefault")
 *
 * // Outputs:
 * // color: red
 * // color: blue
 * ```
 */
@Suppress("IMPLICIT_CAST_TO_ANY")
inline operator fun <reified V> SharedPreferences.getValue(thisRef: Any?, property: KProperty<*>): V? {
    if (!contains(property.name)) return null
    return when (V::class.java) {
        String::class.java -> getString(property.name, null)
        java.lang.Boolean::class.java -> getBoolean(property.name, false)
        java.lang.Integer::class.java -> getInt(property.name, 0)
        java.lang.Float::class.java -> getFloat(property.name, 0f)
        java.lang.Long::class.java -> getLong(property.name, 0)
        Set::class.java -> getStringSet(property.name, null)
        else -> throw UnsupportedClassVersionError("Unrecognized type: ${V::class.java.canonicalName}")
    } as V?
}

/**
 * Allows reading of [SharedPreferences] setting via delegated property.
 *
 * Note that using the property on any type not supported by
 * [SharedPreferences] natively will generate an error.
 *
 * See example in [SharedPreferences.getValue].
 */
@Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")
operator fun <V> SharedPreferences.setValue(thisRef: Any?, property: KProperty<*>, value: V?) {
    val key = property.name
    edit {
        @Suppress("UNCHECKED_CAST")
        when (value) {
            null -> remove(key)
            is String -> put(key, value as String?)
            is java.lang.Boolean -> put(key, value as Boolean)
            is java.lang.Integer -> put(key, value as Int)
            is java.lang.Float -> put(key, value as Float)
            is java.lang.Long -> put(key, value as Long)
            is Set<*> -> put(key, value as Set<String>?)
            else -> throw UnsupportedClassVersionError("Unrecognized type: ${(value as Any)::class.java.canonicalName}")
        }
    }
}
