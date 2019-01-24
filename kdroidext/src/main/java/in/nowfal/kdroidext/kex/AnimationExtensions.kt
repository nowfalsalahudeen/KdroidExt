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

import android.animation.Animator
import android.os.Build
import android.transition.Transition
import androidx.annotation.RequiresApi

fun Animator.addListener(
        onEnd: (Animator) -> Unit = {},
        onStart: (Animator) -> Unit = {},
        onCancel: (Animator) -> Unit = {},
        onRepeat: (Animator) -> Unit = {}
) {
    addListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animator: Animator) {
            onRepeat(animator)
        }

        override fun onAnimationEnd(animator: Animator) {
            onEnd(animator)
        }

        override fun onAnimationCancel(animator: Animator) {
            onCancel(animator)
        }

        override fun onAnimationStart(animator: Animator) {
            onStart(animator)
        }
    })
}

fun Animator.onStart(onStart: (Animator) -> Unit) {
    addListener(onStart = onStart)
}

fun Animator.onEnd(onEnd: (Animator) -> Unit) {
    addListener(onEnd = onEnd)
}

fun Animator.onCancel(onCancel: (Animator) -> Unit) {
    addListener(onCancel = onCancel)
}

fun Animator.onRepeat(onRepeat: (Animator) -> Unit) {
    addListener(onRepeat = onRepeat)
}

@RequiresApi(19)
fun Animator.addPauseListener(
        onResume: (Animator) -> Unit = {},
        onPause: (Animator) -> Unit = {}
): Animator.AnimatorPauseListener {
    val listener = object : Animator.AnimatorPauseListener {
        override fun onAnimationPause(animator: Animator) {
            onPause(animator)
        }

        override fun onAnimationResume(animator: Animator) {
            onResume(animator)
        }
    }
    addPauseListener(listener)
    return listener
}

@RequiresApi(19)
fun Animator.onResume(onResume: (Animator) -> Unit) {
    addPauseListener(onResume = onResume)
}

@RequiresApi(19)
fun Animator.onPause(onPause: (Animator) -> Unit) {
    addPauseListener(onPause = onPause)
}

@RequiresApi(Build.VERSION_CODES.KITKAT)
fun Transition.addListener(
        onTransitionEnd: (Transition) -> Unit = {},
        onTransitionResume: (Transition) -> Unit = {},
        onTransitionPause: (Transition) -> Unit = {},
        onTransitionCancel: (Transition) -> Unit = {},
        onTransitionStart: (Transition) -> Unit = {}
) {
    addListener(@RequiresApi(Build.VERSION_CODES.KITKAT)
    object : Transition.TransitionListener {
        override fun onTransitionEnd(transition: Transition) {
            onTransitionEnd(transition)
        }

        override fun onTransitionResume(transition: Transition) {
            onTransitionResume(transition)
        }

        override fun onTransitionPause(transition: Transition) {
            onTransitionPause(transition)
        }

        override fun onTransitionCancel(transition: Transition) {
            onTransitionCancel(transition)
        }

        override fun onTransitionStart(transition: Transition) {
            onTransitionStart(transition)
        }

    })
}

@RequiresApi(Build.VERSION_CODES.KITKAT)
fun Transition.onTransitionEnd(onTransitionEnd: (Transition) -> Unit) {
    addListener(onTransitionEnd = onTransitionEnd)
}
@RequiresApi(Build.VERSION_CODES.KITKAT)
fun Transition.onTransitionResume(onTransitionResume: (Transition) -> Unit) {
    addListener(onTransitionResume = onTransitionResume)
}
@RequiresApi(Build.VERSION_CODES.KITKAT)
fun Transition.onTransitionPause(onTransitionPause: (Transition) -> Unit) {
    addListener(onTransitionPause = onTransitionPause)
}
@RequiresApi(Build.VERSION_CODES.KITKAT)
fun Transition.onTransitionCancel(onTransitionCancel: (Transition) -> Unit) {
    addListener(onTransitionCancel = onTransitionCancel)
}
@RequiresApi(Build.VERSION_CODES.KITKAT)
fun Transition.onTransitionStart(onTransitionStart: (Transition) -> Unit) {
    addListener(onTransitionStart = onTransitionStart)
}