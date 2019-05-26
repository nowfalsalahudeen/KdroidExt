

package com.nowfal.kdroidext.kotx.ext.app

import android.content.Context
import android.content.DialogInterface
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog


/**
 * Helper class to set alert dialog buttons.
 */
class AlertDialogButton {

    @StringRes var textResId: Int = 0
    var text: String? = null
    var onClick:((dialog: DialogInterface, which: Int) -> Unit)? = null

    /**
     * Sets click handler and [caption] for the button.
     */
    operator fun invoke(caption: String? = null,
                        onClick:((dialog: DialogInterface, which: Int) -> Unit)? = null) {
        this.text = caption
        this.onClick = onClick
    }

    /**
     * Sets click handler and [captionResId] for the button.
     */
    operator fun invoke(captionResId: Int = 0,
                        onClick:((dialog: DialogInterface, which: Int) -> Unit)? = null) {
        this.textResId = captionResId
        this.onClick = onClick
    }

    internal val isSet: Boolean
            get() = (text != null || textResId != 0)

    internal fun caption(context: Context): String? =
            text ?: if (textResId != 0) context.getString(textResId) else null
}

/**
 * Meta-builder class used to build alert dialogs.
 *
 * @property message Dialog message.
 * @property messageResId Dialog message string resource id.
 * @property title Dialog title.
 * @property titleResId Dialog title string resource id.
 * @property cancelable Whether user should be able to cancel out of the dialog.
 */
class AlertDialogBuilder private constructor(private val context: Context,
                                             var message: String? = null,
                                             @StringRes var messageResId: Int = 0,
                                             var title: String? = null,
                                             @StringRes var titleResId: Int = 0,
                                             var cancelable: Boolean = true) {

    /**
     * Constructs a new [AlertDialogBuilder].
     */
    constructor(context: Context): this(
            context = context,
            message = null, messageResId = 0,
            title = null, titleResId = 0)

    /**
     * Constructs a new [AlertDialogBuilder] with specified [message][messageResId]
     * and [title][titleResId].
     */
    constructor(context: Context, messageResId: Int, titleResId: Int = 0): this(
            context = context,
            message = null, messageResId = messageResId,
            title = null, titleResId = titleResId)

    /**
     * Constructs a new [AlertDialogBuilder] with specified [message]
     * and [title].
     */
    constructor(context: Context, message: String?, title: String? = null): this(
            context = context,
            message = message, messageResId = 0,
            title = title, titleResId = 0)

    /**
     * Used to set caption and click handler of the dialog's positive button.
     */
    val positiveButton = AlertDialogButton()
    /**
     * Used to set caption and click handler of the dialog's negative button.
     */
    val negativeButton = AlertDialogButton()
    /**
     * Used to set caption and click handler of the dialog's neutral button.
     */
    val neutralButton = AlertDialogButton()

    private fun safeString(@StringRes resId: Int): String? =
            if (resId != 0) context.getString(resId) else null

    internal fun toDialog() = AlertDialog.Builder(context).apply {
        setMessage(message ?: safeString(messageResId))
        setTitle(title ?: safeString(titleResId))
        if (positiveButton.isSet) {
            setPositiveButton(positiveButton.caption(context)) { d, w -> positiveButton.onClick?.invoke(d, w) }
        }
        if (negativeButton.isSet) {
            setNegativeButton(negativeButton.caption(context)) { d, w -> negativeButton.onClick?.invoke(d, w) }
        }
        if (neutralButton.isSet) {
            setNeutralButton(neutralButton.caption(context)) { d, w -> neutralButton.onClick?.invoke(d, w) }
        }
        setCancelable(cancelable)
    }.create()
}
