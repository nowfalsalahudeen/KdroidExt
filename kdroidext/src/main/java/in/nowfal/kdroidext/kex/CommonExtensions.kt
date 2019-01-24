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
package `in`.nowfal.kdroidext.kex

import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior

fun BottomSheetBehavior<View>.onSlide(onSlide: (View, Float) -> Unit) {
    setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
        override fun onSlide(bottomSheet: View, slideOffset: Float) {
            onSlide(bottomSheet, slideOffset)
        }

        override fun onStateChanged(bottomSheet: View, newState: Int) {
        }

    })
}

fun BottomSheetBehavior<View>.onStateChanged(onStateChanged: (View, Int) -> Unit) {
    setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
        override fun onSlide(bottomSheet: View, slideOffset: Float) {
        }

        override fun onStateChanged(bottomSheet: View, newState: Int) {
            onStateChanged(bottomSheet, newState)
        }

    })
}