

package com.nowfal.kdroidext.kotx.ext.app

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.app.NotificationManager
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Resources
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.preference.PreferenceManager
import androidx.annotation.AttrRes
import androidx.annotation.StringRes
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.appcompat.content.res.AppCompatResources
import android.util.AttributeSet
import android.util.TypedValue
import android.view.inputmethod.InputMethodManager
import android.widget.Toast


/**
 * Default toast duration. Set to [Toast.LENGTH_LONG].
 */
const val TOAST_DEFAULT_DURATION = Toast.LENGTH_LONG

/**
 * Starts a new activity by creating a new [Intent], calling [block] with
 * the new instance, then passing the intent to [Context.startActivity].
 */
inline fun <reified T: Activity> Context.startActivity(block: Intent.() -> Unit = {}) {
    startActivity(Intent(this, T::class.java).apply {
        block(this)
    })
}

/**
 * Returns a [LocalBroadcastManager] instance by calling
 * [LocalBroadcastManager.getInstance].
 */
val Context.localBroadcastManager: LocalBroadcastManager
    get() = LocalBroadcastManager.getInstance(this)

/**
 * Returns [InputMethodManager] instance by calling [Context.getSystemService].
 */
val Context.inputMethodManager: InputMethodManager
    get() = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

/**
 * Returns [SearchManager] instance by calling [Context.getSystemService].
 */
val Context.searchManager: SearchManager
    get() = getSystemService(Context.SEARCH_SERVICE) as SearchManager

/**
 * Returns [ConnectivityManager] instance by calling [Context.getSystemService].
 */
val Context.connectivityManager: ConnectivityManager
    get() = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

/**
 * Returns [NotificationManager] instance by calling [Context.getSystemService].
 */
val Context.notificationManager: NotificationManager
    get() = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

/**
 * Returns default [SharedPreferences] instance by calling
 * [PreferenceManager.getDefaultSharedPreferences].
 */
val Context.defaultSharedPreferences: SharedPreferences
    get() = PreferenceManager.getDefaultSharedPreferences(this)

/**
 * Sends a local broadcast by creating a new [Intent] with specified [action],
 * calling optional [block] with the instance before calling
 * [LocalBroadcastManager.sendBroadcast].
 */
fun Context.broadcastLocal(action: String, block: Intent.() -> Unit = {}) {
    broadcastLocal(Intent(action).apply {
        block(this)
    })
}

/**
 * Sends a local broadcast by calling [LocalBroadcastManager.sendBroadcast]
 * with specified [intent].
 */
fun Context.broadcastLocal(intent: Intent) {
    localBroadcastManager.sendBroadcast(intent)
}

/**
 * Returns true if the device is connected to at least one network.
 * Note that this doesn't necessarily indicate internet access.
 */
val Context.isConnectedToNetwork: Boolean
    @SuppressLint("MissingPermission")
    get() = connectivityManager.activeNetworkInfo?.isConnected == true

/**
 * Returns the app version string, or null if it can't be determined.
 */
fun Context.appVersionName(): String? =
        applicationContext.packageManager.getPackageInfo(packageName, 0)?.versionName

/**
 * Returns the app version code, or -1 if it can't be determined.
 */
fun Context.appVersionCode(): Int =
        applicationContext.packageManager.getPackageInfo(packageName, 0)?.versionCode ?: -1

/**
 * Obtains [TypedArray] by calling [Resources.Theme.obtainStyledAttributes]
 * with supplied parameters, then passing the array to the supplied [block],
 * before recycling it.
 */
fun Context.withStyledAttributes(attrs: IntArray, set: AttributeSet? = null,
                                 defStyleAttr: Int = 0, defStyleRes: Int = 0,
                                 block: TypedArray.() -> Unit) {
    set?.let {
        theme.obtainStyledAttributes(set, attrs, defStyleAttr, defStyleRes)
                .apply(block)
                .recycle()
    }
}

/**
 * Creates and displays a new [Toast] with specified
 * [message][messageResId].
 *
 * Example:
 *
 * ```
 * showToast(R.string.update_complete)
 * ```
 */
fun Context.showToast(@StringRes messageResId: Int,
                      duration: Int = TOAST_DEFAULT_DURATION
) {
    Toast.makeText(this, messageResId, duration).show()
}

/**
 * Creates and displays a new [Toast] with specified [message].
 *
 * Example:
 *
 * ```
 * showToast("Update complete")
 * ```
 */
fun Context.showToast(message: String?,
                      duration: Int = TOAST_DEFAULT_DURATION
) {
    Toast.makeText(this, message, duration).show()
}

/**
 * Displays an alert dialog by creating a new [AlertDialogBuilder] using the
 * [title] and [message] provided, then passing it to [block] with the builder
 * as receiver.
 *
 * Example usage:
 *
 * ```
 * showAlertDialog("Danger zone?") {
 *     positiveButton("yes") { _, _ -> deleteAll() }
 *     negativeButton("no")
 * }
 * ```
 *
 * @return Instance of displayed dialog.
 */
fun Context.showAlertDialog(message: String? = null,
                            title: String? = null,
                            block: AlertDialogBuilder.() -> Unit = {}): Dialog {
    val dialog = AlertDialogBuilder(this, message, title).apply {
        block(this)
    }.toDialog()

    dialog.show()
    return dialog
}

/**
 * Displays an alert dialog by creating a new [AlertDialogBuilder] using the
 * [title][titleResId] and [message][messageResId] string resource ids provided,
 * then passing it to [block] with the builder as receiver.
 *
 * Example usage:
 *
 * ```
 * showAlertDialog(R.string.delete_everything) {
 *     positiveButton(R.string.yes) { _, _ -> deleteAll() }
 *     negativeButton(R.string.no)
 * }
 * ```
 *
 * @return Instance of displayed dialog.
 */
fun Context.showAlertDialog(@StringRes messageResId: Int,
                            @StringRes titleResId: Int = 0,
                            block: AlertDialogBuilder.() -> Unit = {}): Dialog {
    val dialog = AlertDialogBuilder(this, messageResId, titleResId).apply {
        block(this)
    }.toDialog()

    dialog.show()
    return dialog
}

/**
 * Resolves an attribute with specified [resource id][resId], returning its
 * integer value if successful. Throws an exception if attribute is invalid.
 *
 * @param resolveRefs true to resolve references (default)
 */
fun Context.resolveIntAttribute(@AttrRes resId: Int,
                                resolveRefs: Boolean = true): Int =
        resolveIntAttributeSafe(resId, resolveRefs)
                ?: throw IllegalArgumentException("Attribute not found")

/**
 * Safe variant of [Context.resolveIntAttribute].
 * Resolves an attribute with specified [resource id][resId], returning its
 * integer value if successful, null otherwise.
 *
 * @param resolveRefs true to resolve references (default)
 */
fun Context.resolveIntAttributeSafe(@AttrRes resId: Int,
                                    resolveRefs: Boolean = true): Int? {
    val v = TypedValue()
    return if (theme.resolveAttribute(resId, v, resolveRefs)) v.data else null
}

/**
 * Helper method call to [AppCompatResources.getDrawable] - returns a drawable
 * with specific resource id. Despite the name, the function this method calls
 * will also load non-vector drawables. Will also work on pre-vector versions
 * of the platform.
 *
 * @return Drawable with specific resource id.
 */
fun Context.getVectorDrawable(resId: Int): Drawable? =
        AppCompatResources.getDrawable(this, resId)
