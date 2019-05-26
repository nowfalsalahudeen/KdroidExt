

package com.nowfal.kdroidext.kotx.ext.app

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Resources
import android.preference.PreferenceManager
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar
import androidx.fragment.app.Fragment
import android.widget.Toast
import org.akop.kotx.ext.widget.SNACKBAR_DEFAULT_DURATION
import org.akop.kotx.ext.widget.showSnackbar

/**
 * Starts a new activity by creating a new [Intent], calling [block] with
 * the new instance, then passing the intent to [Context.startActivity].
 *
 * Does nothing if the fragment isn't attached to an activity.
 */
inline fun <reified T: Activity> Fragment.startActivity(block: Intent.() -> Unit = {}) {
    activity?.startActivity(Intent(activity!!, T::class.java).apply {
        block(this)
    })
}

/**
 * Returns string with specified [resource identifier][resId], or null
 * if the fragment isn't attached to an activity.
 */
fun Fragment.getStringSafe(@StringRes resId: Int): String? = activity?.getString(resId)

/**
 * Returns string with specified [resource identifier][resId] and
 * [format arguments][args], or null if the fragment isn't attached
 * to an activity.
 */
fun Fragment.getStringSafe(@StringRes resId: Int, vararg args: Any?) = activity?.getString(resId, *args)

/**
 * Returns container activity's [Resources] object, or
 * null if the fragment isn't attached to an activity.
 */
val Fragment.resourcesSafe
    get() = activity?.resources

/**
 * Creates and displays a new [Toast] with specified
 * [message][messageResId] and [duration]
 * (which defaults to [TOAST_DEFAULT_DURATION]).
 *
 * Does nothing if the fragment isn't attached to an activity.
 *
 * Example:
 *
 * ```
 * showToast(R.string.update_complete)
 * ```
 */
fun Fragment.showToast(@StringRes messageResId: Int,
                                             duration: Int = TOAST_DEFAULT_DURATION
) {
    activity?.showToast(messageResId, duration)
}

/**
 * Creates and displays a new [Toast] with specified
 * [message] and [duration] (which defaults to [TOAST_DEFAULT_DURATION]).
 *
 * Does nothing if the fragment isn't attached to an activity.
 *
 * Example:
 *
 * ```
 * showToast("Update complete")
 * ```
 */
fun Fragment.showToast(message: String?,
                                             duration: Int = TOAST_DEFAULT_DURATION
) {
    activity?.showToast(message, duration)
}

/**
 * Returns default [SharedPreferences] instance by calling
 * [PreferenceManager.getDefaultSharedPreferences] on
 * the container activity.
 *
 * Returns null if the fragment isn't attached to an activity.
 */
val Fragment.defaultSharedPreferences: SharedPreferences?
    get() = activity?.defaultSharedPreferences

/**
 * Creates a new [Snackbar]
 * with specified [message] and [duration],
 * then calls optional [block] with Snackbar as receiver before displaying.
 *
 * Uses [Fragment.getView] as the parent view.
 * Duration defaults to [SNACKBAR_DEFAULT_DURATION].
 *
 * Does nothing if the fragment isn't attached to an activity, or if [message]
 * is null.
 *
 * Example:
 * ```
 * showSnackbar("Do you agree?") {
 *     setAction("yes") { validateAndSubmit(true) }
 * }
 * ```
 */
fun Fragment.showSnackbar(message: CharSequence?,
                                                duration: Int = SNACKBAR_DEFAULT_DURATION,
                                                block: Snackbar.() -> Unit = {}) {
    this.view?.let { showSnackbar(it, message, duration, block) }
}

/**
 * Creates a new [Snackbar]
 * with specified [message][messageResId] and [duration],
 * then calls optional [block] with Snackbar as receiver before displaying.
 *
 * Uses [Fragment.getView] as the parent view.
 * Duration defaults to [SNACKBAR_DEFAULT_DURATION].
 *
 * Does nothing if the fragment isn't attached to an activity.
 *
 * Example:
 * ```
 * showSnackbar(R.string.do_you_agree) {
 *     setAction(R.string.yes) { validateAndSubmit(true) }
 * }
 * ```
 */
fun Fragment.showSnackbar(@StringRes messageResId: Int,
                                                duration: Int = SNACKBAR_DEFAULT_DURATION,
                                                block: Snackbar.() -> Unit = {}) {
    this.view?.let { showSnackbar(it, messageResId, duration, block) }
}

/**
 * Displays an alert dialog by creating a new [AlertDialogBuilder] using the
 * [title] and [message] provided, then passing it to [block] with the builder
 * as receiver.
 *
 * Does nothing if the fragment isn't attached to an activity.
 *
 * Example:
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
fun Fragment.showAlertDialog(message: String? = null,
                                                   title: String? = null,
                                                   block: AlertDialogBuilder.() -> Unit = {}): Dialog? {
    return activity?.showAlertDialog(message, title, block)
}

/**
 * Displays an alert dialog by creating a new [AlertDialogBuilder] using the
 * [title][titleResId] and [message][messageResId] string resource ids provided,
 * then passing it to [block] with the builder as receiver.
 *
 * Does nothing if the fragment isn't attached to an activity.
 *
 * Example:
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
fun Fragment.showAlertDialog(@StringRes messageResId: Int,
                                                   @StringRes titleResId: Int = 0,
                                                   block: AlertDialogBuilder.() -> Unit = {}): Dialog? {
    return activity?.showAlertDialog(messageResId, titleResId, block)
}
