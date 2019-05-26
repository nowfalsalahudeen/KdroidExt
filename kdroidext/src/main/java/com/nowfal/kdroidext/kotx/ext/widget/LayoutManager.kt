
package com.nowfal.kdroidext.kotx.ext.widget

import androidx.recyclerview.widget.LinearLayoutManager

/**
 * Returns the range of visible items in a [LinearLayoutManager].
 */
val LinearLayoutManager.visibleRange: IntRange
    get() = findFirstVisibleItemPosition()..findLastVisibleItemPosition()
