

package com.nowfal.kdroidext.kotx.ext.basic

import android.net.Uri


/**
 * Returns query parameter matching [param].
 */
operator fun Uri.get(param: String): String? = getQueryParameter(param)
