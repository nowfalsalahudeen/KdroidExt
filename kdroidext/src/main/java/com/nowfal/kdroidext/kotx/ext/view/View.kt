
package com.nowfal.kdroidext.kotx.ext.view

import android.os.Build
import androidx.annotation.AnimRes
import androidx.annotation.MenuRes
import androidx.appcompat.widget.PopupMenu
import android.view.MenuItem
import android.view.View
import android.view.ViewParent
import android.view.ViewTreeObserver
import android.view.animation.Animation
import android.view.animation.AnimationUtils


fun View.addGlobalLayoutListener(l: ViewTreeObserver.OnGlobalLayoutListener) {
    viewTreeObserver?.addOnGlobalLayoutListener(l)
}

fun View.removeGlobalLayoutListener(l: ViewTreeObserver.OnGlobalLayoutListener) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        viewTreeObserver?.removeOnGlobalLayoutListener(l)
    } else {
        @Suppress("DEPRECATION")
        viewTreeObserver?.removeGlobalOnLayoutListener(l)
    }
}

/**
 * Loads and begins [animation with specified resource id][animationResId].
 */
fun View.animate(@AnimRes animationResId: Int) {
    startAnimation(AnimationUtils.loadAnimation(context, animationResId))
}

/**
 * Loads and begins [animation with specified resource id][animationResId],
 * optionally executing a block specified by [butFirst] prior to animation,
 * [andThen] following the animation, and [onRepeat] upon repeat.
 */
fun View.animate(@AnimRes animationResId: Int,
                 butFirst: Animation.(View) -> Unit = {},
                 andThen: Animation.(View) -> Unit = {},
                 onRepeat: Animation.(View) -> Unit = {}) {
    animate(AnimationUtils.loadAnimation(context, animationResId),
            butFirst, andThen, onRepeat)
}

/**
 * Begins [animation], optionally executing a block specified by
 * [butFirst] prior to animation, [andThen] following the animation, and
 * [onRepeat] upon repeat.
 *
 * Don't set an [Animation.AnimationListener] prior to calling this method,
 * because it will be reset.
 */
fun View.animate(animation: Animation,
                 butFirst: Animation.(View) -> Unit = {},
                 andThen: Animation.(View) -> Unit = {},
                 onRepeat: Animation.(View) -> Unit = {}) {
    startAnimation(animation.apply {
        setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation) {
                onRepeat(animation, this@animate)
            }
            override fun onAnimationStart(animation: Animation) {
                butFirst(animation, this@animate)
            }
            override fun onAnimationEnd(animation: Animation) {
                andThen(animation, this@animate)
                animation.setAnimationListener(null)
            }
        })
    })
}

/**
 * Finds parent of the view of type [T], not including the view itself.
 */
@Suppress("UNCHECKED_CAST")
inline fun <reified T> View.findParent(): T? {
    var vp: ViewParent? = parent
    while (vp != null) {
        if (vp is T) return vp
        vp = vp.parent
    }

    return null
}

/**
 * Displays a popup by inflating menu with specified
 * [menu resource id][menuResourceId], calling [onClick] when an item
 * is clicked, and optionally calling [onInit] with
 * [PopupMenu] as receiver to initialize prior to display.
 */
fun View.showPopup(@MenuRes menuResourceId: Int,
                   onInit: PopupMenu.() -> Unit = {},
                   onClick: (MenuItem) -> Boolean) {
    PopupMenu(context, this).apply {
        menuInflater.inflate(menuResourceId, menu)
        onInit(this)
        setOnMenuItemClickListener(onClick)
    }.show()
}

/**
 * Sets receiver's visibility to [View.VISIBLE] if [value] is true;
 * sets it to [View.INVISIBLE] otherwise. Opposite of [hideIf]; also
 * see [expandIf].
 */
fun View.showIf(value: Boolean) {
    visibility = if (value) View.VISIBLE else View.INVISIBLE
}

/**
 * Sets receiver's visibility to [View.VISIBLE] if [value] is true;
 * sets it to [View.GONE] otherwise. Opposite of [collapseIf]; also
 * see [showIf].
 */
fun View.expandIf(value: Boolean = false) {
    visibility = if (value) View.VISIBLE else View.GONE
}

/**
 * Sets receiver's visibility to [View.INVISIBLE] if [value] is true;
 * sets it to [View.VISIBLE] otherwise. Opposite of [showIf]; also
 * see [collapseIf].
 */
fun View.hideIf(value: Boolean) {
    visibility = if (!value) View.VISIBLE else View.INVISIBLE
}

/**
 * Sets receiver's visibility to [View.GONE] if [value] is true;
 * sets it to [View.VISIBLE] otherwise. Opposite of [expandIf]; also
 * see [hideIf].
 */
fun View.collapseIf(value: Boolean) {
    visibility = if (!value) View.VISIBLE else View.GONE
}

/**
 * Sets receiver's visibility [View.VISIBLE].
 */
fun View.expand() {
    visibility = View.VISIBLE
}

/**
 * Sets receiver's visibility [View.GONE].
 */
fun View.collapse() {
    visibility = View.GONE
}

/**
 * Sets receiver's visibility [View.INVISIBLE].
 */
fun View.hide() {
    visibility = View.INVISIBLE
}

/**
 * Calls [block] when layout for the view is complete by registering a
 * [ViewTreeObserver.OnGlobalLayoutListener], and automatically unregistering
 * when the callback completes.
 */
fun <T: View> T.onLayoutReady(block: (v: T) -> Unit) {
    var ll: ViewTreeObserver.OnGlobalLayoutListener? = null
    ll = ViewTreeObserver.OnGlobalLayoutListener {
        block(this)
        removeGlobalLayoutListener(ll!!)
    }

    addGlobalLayoutListener(ll)
}
