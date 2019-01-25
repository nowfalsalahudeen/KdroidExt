/*
 * Copyright 2019 Nowfal Salahudeen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:Suppress("NOTHING_TO_INLINE")

package com.nowfal.kdroidext.kex

import android.app.Activity
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes

@Deprecated("Use findViewById() instead", ReplaceWith("findViewById()"))
inline fun <reified T : View> Activity.find(@IdRes id: Int): T = findViewById(id)

inline fun <reified T : Any> Activity.startActivityForResult(requestCode: Int, options: Bundle? = null, action: String? = null) =
        startActivityForResult(IntentFor<T>(this).setAction(action), requestCode, options)

inline fun Activity.lockCurrentScreenOrientation() {
    requestedOrientation = when (resources.configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
        else -> ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT
    }
}

inline fun Activity.unlockScreenOrientation() {
    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR
}