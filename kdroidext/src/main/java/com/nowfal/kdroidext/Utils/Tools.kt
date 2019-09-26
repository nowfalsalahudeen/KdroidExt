package com.nowfal.kdroidext.Utils

import android.app.Activity
import android.app.Dialog
import android.content.*
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Color
import android.graphics.PorterDuff
import android.net.Uri
import android.os.Build
import android.util.Log
import android.util.TypedValue
import android.view.Menu
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.widget.NestedScrollView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import java.text.SimpleDateFormat
import java.util.Date

import android.content.Context.WINDOW_SERVICE
import com.bumptech.glide.Priority
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import com.nowfal.kdroidext.R
import de.hdodenhof.circleimageview.CircleImageView


val screenWidth: Int
    get() = Resources.getSystem().displayMetrics.widthPixels

val screenHeight: Int
    get() = Resources.getSystem().displayMetrics.heightPixels


fun adjustFontScale(configuration: Configuration, mContext: Context) {
    if (configuration.fontScale > 1) {
        Log.e(
            "fontScale",
            "fontScale=" + configuration.fontScale
        ) //Custom Log class, you can use Log.w
        Log.e("fontScale", "font too big. scale down...") //Custom Log class, you can use Log.w
        configuration.fontScale = 1.toFloat()
        val metrics = mContext.resources.displayMetrics
        val wm = mContext.getSystemService(WINDOW_SERVICE) as WindowManager
        wm.defaultDisplay.getMetrics(metrics)
        metrics.scaledDensity = configuration.fontScale * metrics.density
        mContext.resources.updateConfiguration(configuration, metrics)
    } else if (configuration.fontScale < 1 && configuration.fontScale > .8) {
        Log.e(
            "fontScale",
            "fontScale=" + configuration.fontScale
        ) //Custom Log class, you can use Log.w
        Log.e("fontScale", "font too big. scale down...") //Custom Log class, you can use Log.w
        configuration.fontScale = .85.toFloat()
        val metrics = mContext.resources.displayMetrics
        val wm = mContext.getSystemService(WINDOW_SERVICE) as WindowManager
        wm.defaultDisplay.getMetrics(metrics)
        metrics.scaledDensity = configuration.fontScale * metrics.density
        mContext.resources.updateConfiguration(configuration, metrics)
    }
}

fun setSystemBarColor(act: Activity, @ColorRes color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        val window = act.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = ContextCompat.getColor(act, color)
    }
}

fun setSystemBarColorDialog(act: Context, dialog: Dialog, @ColorRes color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        val window = dialog.window
        window!!.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = ContextCompat.getColor(act, color)
    }
}

fun setSystemBarLight(act: Activity) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val view = act.findViewById<View>(android.R.id.content)
        var flags = view.systemUiVisibility
        flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        view.systemUiVisibility = flags
    }
}

fun setSystemBarLightDialog(dialog: Dialog) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val view = dialog.findViewById<View>(android.R.id.content)
        var flags = view.systemUiVisibility
        flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        view.systemUiVisibility = flags
    }
}


fun clearSystemBarLight(act: Activity, @ColorRes color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val window = act.window
        window.statusBarColor = ContextCompat.getColor(act, color)
    }
}

/**
 * Making notification bar transparent
 */
fun setSystemBarTransparent(act: Activity) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        val window = act.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
    }
}

fun setImageUrl(@DrawableRes drawable: Int, img: ImageView) {
    try {

        Glide.with(img.context).load(drawable)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)
            .skipMemoryCache(true)
//            .transition(DrawableTransitionOptions.withCrossFade())
            .into(img)
    } catch (e: Exception) {
    }

}


fun setImageUrl(url: String?, img: ImageView) {
    try {
        Glide.with(img.context).load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)
            .skipMemoryCache(true)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(img)
    } catch (e: Exception) {
        e.printStackTrace()
    }

}

fun setImageUrl(url: String?, img: CircleImageView) {
    try {
        Glide.with(img.context).load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)
            .skipMemoryCache(true)
//            .transition(DrawableTransitionOptions.withCrossFade())
            .into(img)
    } catch (e: Exception) {
        e.printStackTrace()
    }

}

fun getFormattedDateSimple(dateTime: Long?): String {
    val newFormat = SimpleDateFormat("MMMM dd, yyyy")
    return newFormat.format(Date(dateTime!!))
}

fun getFormattedDateEvent(dateTime: Long?): String {
    val newFormat = SimpleDateFormat("EEE, MMM dd yyyy")
    return newFormat.format(Date(dateTime!!))
}

fun getFormattedTimeEvent(time: Long?): String {
    val newFormat = SimpleDateFormat("h:mm a")
    return newFormat.format(Date(time!!))
}

fun getEmailFromName(name: String?): String? {
    return if (name != null && name != "") {
        name.replace(" ".toRegex(), ".").toLowerCase() + "@mail.com"
    } else name
}

fun dpToPx(c: Context, dp: Int): Int {
    val r = c.resources
    return Math
        .round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), r.displayMetrics))
}


fun copyToClipboard(context: Context, data: String) {
    val clipboard = context
        .getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("clipboard", data)
    clipboard.setPrimaryClip(clip)
    Toast.makeText(context, "Text copied to clipboard", Toast.LENGTH_SHORT).show()
}

fun nestedScrollTo(nested: NestedScrollView, targetView: View) {
    nested.post { nested.scrollTo(500, targetView.bottom) }
}

fun dip2px(context: Context, dpValue: Float): Int {
    val scale = context.resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}

fun px2dip(context: Context, pxValue: Float): Int {
    val scale = context.resources.displayMetrics.density
    return (pxValue / scale + 0.5f).toInt()
}

fun toggleArrow(view: View): Boolean {
    return if (view.rotation == 0f) {
        view.animate().setDuration(200).rotation(180f)
        true
    } else {
        view.animate().setDuration(200).rotation(0f)
        false
    }
}

@JvmOverloads
fun toggleArrow(show: Boolean, view: View, delay: Boolean = true): Boolean {
    return if (show) {
        view.animate().setDuration((if (delay) 200 else 0).toLong()).rotation(180f)
        true
    } else {
        view.animate().setDuration((if (delay) 200 else 0).toLong()).rotation(0f)
        false
    }
}

fun changeNavigateionIconColor(toolbar: Toolbar, @ColorInt color: Int) {
    val drawable = toolbar.navigationIcon
    drawable!!.mutate()
    drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
}

fun changeMenuIconColor(menu: Menu, @ColorInt color: Int) {
    for (i in 0 until menu.size()) {
        val drawable = menu.getItem(i).icon ?: continue
        drawable.mutate()
        drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
    }
}

fun changeOverflowMenuIconColor(toolbar: Toolbar, @ColorInt color: Int) {
    try {
        val drawable = toolbar.overflowIcon
        drawable!!.mutate()
        drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
    } catch (e: Exception) {
    }

}


fun insertPeriodically(text: String, insert: String, period: Int): String {
    val builder = StringBuilder(
        text.length + insert.length * (text.length / period) + 1
    )
    var index = 0
    var prefix = ""
    while (index < text.length) {
        builder.append(prefix)
        prefix = insert
        builder.append(text.substring(index, Math.min(index + period, text.length)))
        index += period
    }
    return builder.toString()
}


fun rateAction(activity: Activity) {
    val uri = Uri.parse("market://details?id=" + activity.packageName)
    val goToMarket = Intent(Intent.ACTION_VIEW, uri)
    try {
        activity.startActivity(goToMarket)
    } catch (e: ActivityNotFoundException) {
        activity.startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://play.google.com/store/apps/details?id=" + activity.packageName)
            )
        )
    }

}

fun shareText(subject: String, body: String, mContext: Context) {
    val txtIntent = Intent(Intent.ACTION_SEND)
    txtIntent.type = "text/plain"
    txtIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
    txtIntent.putExtra(Intent.EXTRA_TEXT, body)
    mContext.startActivity(Intent.createChooser(txtIntent, "Share"))
}

