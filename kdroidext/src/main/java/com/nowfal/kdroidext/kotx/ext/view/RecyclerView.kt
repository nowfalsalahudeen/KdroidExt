
package com.nowfal.kdroidext.kotx.ext.view

import androidx.recyclerview.widget.RecyclerView


/**
 * Convenient form of [RecyclerView.Adapter.notifyItemRangeInserted] that
 * accepts an [IntRange].
 */
fun RecyclerView.Adapter<*>.notifyItemRangeInserted(range: IntRange) {
    notifyItemRangeInserted(range.start, range.count())
}

/**
 * Convenient form of [RecyclerView.Adapter.notifyItemRangeChanged] that
 * accepts an [IntRange].
 */
fun RecyclerView.Adapter<*>.notifyItemRangeChanged(range: IntRange) {
    notifyItemRangeChanged(range.start, range.count())
}

/**
 * Convenient form of [RecyclerView.Adapter.notifyItemRangeRemoved] that
 * accepts an [IntRange].
 */
fun RecyclerView.Adapter<*>.notifyItemRangeRemoved(range: IntRange) {
    notifyItemRangeRemoved(range.start, range.count())
}
