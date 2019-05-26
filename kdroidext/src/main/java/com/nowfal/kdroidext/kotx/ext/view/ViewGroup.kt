
package com.nowfal.kdroidext.kotx.ext.view

import androidx.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/** @suppress
 * Iterator class for children of a [ViewGroup].
 */
internal class ViewGroupIterator(private val parent: ViewGroup): Iterator<View> {

    private var index = 0

    override fun hasNext(): Boolean = index < parent.childCount

    override fun next(): View = parent.getChildAt(index++)
}

/**
 * Returns true if the [ViewGroup] has no children.
 */
val ViewGroup.isEmpty
    get() = childCount == 0

/**
 * Returns child view at specified [index].
 */
operator fun ViewGroup.get(index: Int): View = getChildAt(index)

/**
 * Returns child view at specified [index].
 */
fun ViewGroup.getOrNull(index: Int): View? =
        if (index in 0 until childCount) getChildAt(index) else null

/**
 * Returns an [Iterable] object for the children of a [ViewGroup].
 */
fun ViewGroup.children(): Iterable<View> = object:Iterable<View> {
    override fun iterator(): Iterator<View> = ViewGroupIterator(this@children)
}

/**
 * Inflates a (child) view using [LayoutInflater] obtained with the view's
 * context.
 *
 * @param layoutId Resource id of the layout to inflate.
 * @param attachToRoot true to attach the child view to the parent after
 * inflation (false by default).
 *
 * @return Inflated [View].
 */
fun ViewGroup.inflate(@LayoutRes layoutId: Int, attachToRoot: Boolean = false): View =
        LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)

/**
 * Returns first child as type [T].
 */
@Suppress("UNCHECKED_CAST")
inline fun <reified T: View> ViewGroup.firstChild(): T = getChildAt(0) as T

/**
 * Returns first child as type [T] if available, null if the [ViewGroup] has
 * no children, or if the child isn't of the expected type.
 */
@Suppress("UNCHECKED_CAST")
inline fun <reified T: View> ViewGroup.firstChildOrNull(): T? =
        if (!isEmpty) getChildAt(0) as? T else null

/**
 * Adds a child [view] to the receiver.
 */
operator fun ViewGroup.plusAssign(view: View) {
    addView(view)
}

/**
 * Adds child [views] to the receiver, in the original order.
 */
operator fun ViewGroup.plusAssign(views: Iterable<View>) {
    for (v: View in views) addView(v)
}

/**
 * Removes child [view] from the receiver.
 */
operator fun ViewGroup.minusAssign(view: View) {
    removeView(view)
}

/**
 * Sets receiver's visibility to [View.GONE] if receiver has no children;
 * sets it to [View.VISIBLE] otherwise.
 */
fun ViewGroup.collapseIfEmpty() {
    visibility = if (!isEmpty) View.VISIBLE else View.GONE
}

/**
 * Sets receiver's visibility to [View.INVISIBLE] if receiver has no children;
 * sets it to [View.VISIBLE] otherwise.
 */
fun ViewGroup.hideIfEmpty() {
    visibility = if (!isEmpty) View.VISIBLE else View.INVISIBLE
}
