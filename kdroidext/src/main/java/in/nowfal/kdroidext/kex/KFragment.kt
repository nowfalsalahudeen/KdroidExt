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

package `in`.nowfal.kdroidext.kex

import android.preference.Preference
import android.preference.PreferenceFragment
import android.preference.PreferenceManager
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

inline fun Fragment.getDefaultSharedPreferences() = PreferenceManager.getDefaultSharedPreferences(activity)

inline fun Fragment.toast(text: CharSequence): Toast? = activity?.toast(text)

inline fun Fragment.longToast(text: CharSequence): Toast? = activity?.longToast(text)

inline fun Fragment.toast(@StringRes resId: Int): Toast? = activity?.toast(resId)

inline fun Fragment.longToast(@StringRes resId: Int): Toast? = activity?.longToast(resId)

inline fun <reified T : Preference> PreferenceFragment.findPref(key: String): T = findPreference(key) as T
