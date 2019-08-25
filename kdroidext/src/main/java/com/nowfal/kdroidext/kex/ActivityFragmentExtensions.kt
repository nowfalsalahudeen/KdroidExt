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

package com.nowfal.kdroidext.kex

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import androidx.annotation.*
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import android.view.View
import android.view.WindowManager

fun Fragment.color(@ColorRes clr: Int): Int {
    return context!!.color(clr)
}

fun Fragment.string(@StringRes str: Int): String {
    return getString(str)
}

fun Fragment.drawable(@DrawableRes drw: Int): Drawable? {
    return context!!.drawable(drw)
}

fun Fragment.dimen(@DimenRes dmn: Int): Float {
    return context!!.dimen(dmn)
}

fun Fragment.dimenInt(@DimenRes dmn: Int): Int {
    return context!!.dimenInt(dmn)
}

fun Fragment.int(@IntegerRes int: Int): Int {
    return context!!.int(int)
}

fun Fragment.font(@FontRes font: Int): Typeface? {
    return context!!.font(font)
}

fun Fragment.stringArray(array: Int): Array<String> {
    return context!!.stringArray(array)
}

fun Fragment.intArray(array: Int): IntArray {
    return context!!.intArray(array)
}

fun <T> Fragment.startActivityForResult(activityTo: Class<T>, requestCode: Int,
                                        extras: Bundle = Bundle(), overrideTransitions: Boolean = false,
                                        enterAnim: Int = 0, exitAnim: Int = 0) {
    val starter = Intent(activity, activityTo)
    if (!extras.isEmpty) {
        starter.putExtras(extras)
    }
    startActivityForResult(starter, requestCode)
    if (overrideTransitions) {
        activity!!.overridePendingTransition(enterAnim, exitAnim)
    }
}

fun <T> Fragment.startActivityFromFragmentWithResult(activityTo: Class<T>, requestCode: Int = 0, extras: Bundle = Bundle()) {
    val starter = Intent(activity, activityTo)
    if (!extras.isEmpty) {
        starter.putExtras(extras)
    }

    activity!!.startActivityFromFragment(this, starter, requestCode)
}

fun Fragment.popFragment() {
    val fm = requireFragmentManager()
    if (fm.backStackEntryCount == 1) return
    fm.popBackStack()
}

fun Fragment.popFragment(name: String, flags: Int) {
    val fm = requireFragmentManager()
    if (fm.backStackEntryCount == 1) return
    fm.popBackStack(name, flags)
}

fun Fragment.popFragment(id: Int, flags: Int) {
    val fm = requireFragmentManager()
    if (fm.backStackEntryCount == 1) return
    fm.popBackStack(id, flags)
}

fun Fragment.removeFragment(fragment: Fragment) {
    val ft = requireFragmentManager().beginTransaction()
    ft.remove(fragment)
    ft.commitNow()
}

fun Fragment.removeFragmentByTag(tag: String) {
    val ft = requireFragmentManager().beginTransaction()
    val fragment = requireFragmentManager().findFragmentByTag(tag) ?: return
    ft.remove(fragment)
    ft.commitNow()
}

fun Fragment.removeFragmentById(id: Int) {
    val ft = requireFragmentManager().beginTransaction()
    val fragment = requireFragmentManager().findFragmentById(id) ?: return
    ft.remove(fragment)
    ft.commitNow()
}

fun FragmentActivity.replaceFragment(fragment: Fragment, @IdRes container: Int,
                                     addToBackStack: Boolean = false, backStackName: String = "",
                                     @AnimRes inAnimationRes: Int = 0, @AnimRes outAnimationRes: Int = 0) {
    val ft = supportFragmentManager.beginTransaction()
    if (inAnimationRes != 0 && outAnimationRes != 0) {
        ft.setCustomAnimations(inAnimationRes, outAnimationRes)
    }
    ft.replace(container, fragment)

    if (addToBackStack) {
        ft.addToBackStack(backStackName)
    }

    ft.commit()
}

fun FragmentActivity.addFragment(fragment: Fragment, @IdRes container: Int,
                                 addToBackStack: Boolean = false, backStackName: String = "",
                                 @AnimRes inAnimationRes: Int = 0, @AnimRes outAnimationRes: Int = 0) {
    val ft = supportFragmentManager.beginTransaction()
    if (inAnimationRes != 0 && outAnimationRes != 0) {
        ft.setCustomAnimations(inAnimationRes, outAnimationRes)
    }
    ft.add(container, fragment)

    if (addToBackStack) {
        ft.addToBackStack(backStackName)
    }

    ft.commit()
}

fun FragmentActivity.popFragment() {
    val fm = supportFragmentManager
    if (fm.backStackEntryCount == 0) return
    fm.popBackStack()
}

fun FragmentActivity.popFragment(name: String, flags: Int) {
    val fm = supportFragmentManager
    if (fm.backStackEntryCount == 0) return
    fm.popBackStack(name, flags)
}

fun FragmentActivity.popFragment(id: Int, flags: Int) {
    val fm = supportFragmentManager
    if (fm.backStackEntryCount == 0) return
    fm.popBackStack(id, flags)
}

fun FragmentActivity.removeFragment(fragment: Fragment) {
    val ft = supportFragmentManager.beginTransaction()
    ft.remove(fragment)
    ft.commitNow()
}

fun FragmentActivity.removeFragmentByTag(tag: String) {
    val ft = supportFragmentManager.beginTransaction()
    val fragment = supportFragmentManager.findFragmentByTag(tag) ?: return
    ft.remove(fragment)
    ft.commitNow()
}

fun FragmentActivity.removeFragmentById(id: Int) {
    val ft = supportFragmentManager.beginTransaction()
    val fragment = supportFragmentManager.findFragmentById(id) ?: return
    ft.remove(fragment)
    ft.commitNow()
}

fun <T> Activity.startActivity(activityTo: Class<T>, extras: Bundle = Bundle(),
                               overrideTransitions: Boolean = false,
                               enterAnim: Int = 0, exitAnim: Int = 0) {
    val starter = Intent(this, activityTo)
    if (!extras.isEmpty) {
        starter.putExtras(extras)
    }
    startActivity(starter)
    if (overrideTransitions) {
        overridePendingTransition(enterAnim, exitAnim)
    }
}

fun <T> Activity.startActivityForResult(activityTo: Class<T>, requestCode: Int,
                                        extras: Bundle = Bundle(), overrideTransitions: Boolean = false,
                                        enterAnim: Int = 0, exitAnim: Int = 0) {
    val starter = Intent(this, activityTo)
    if (!extras.isEmpty) {
        starter.putExtras(extras)
    }
    startActivityForResult(starter, requestCode)
    if (overrideTransitions) {
        overridePendingTransition(enterAnim, exitAnim)
    }
}

fun <T> Activity.startActivityWithTransitions(activityTo: Class<T>, options: Bundle, extras: Bundle = Bundle()) {
    val starter = Intent(this, activityTo)
    if (!extras.isEmpty) {
        starter.putExtras(extras)
    }
    ActivityCompat.startActivity(this, starter, options)
}

fun <T> Activity.startActivityForResultWithTransitions(activityTo: Class<T>, requestCode: Int, options: Bundle,
                                                       extras: Bundle = Bundle()) {
    val starter = Intent(this, activityTo)
    if (!extras.isEmpty) {
        starter.putExtras(extras)
    }
    ActivityCompat.startActivityForResult(this, starter, requestCode, options)
}

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
fun <T> Activity.startActivityFromFragmentWithTransitions(activityTo: Class<T>, fragmentFrom: android.app.Fragment,
                                                          options: Bundle, requestCode: Int = 0, extras: Bundle = Bundle()) {
    val starter = Intent(this, activityTo)
    if (!extras.isEmpty) {
        starter.putExtras(extras)
    }
    startActivityFromFragment(fragmentFrom, starter, requestCode, options)
}

fun <T> Activity.startActivityFromFragmentWithResult(activityTo: Class<T>, fragmentFrom: android.app.Fragment, requestCode: Int, extras: Bundle = Bundle()) {
    val starter = Intent(this, activityTo)
    if (!extras.isEmpty) {
        starter.putExtras(extras)
    }

    startActivityFromFragment(fragmentFrom, starter, requestCode)
}

@RequiresApi(Build.VERSION_CODES.KITKAT)
fun Activity.makeTranslucentStatusBar() {
    window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
}

@RequiresApi(Build.VERSION_CODES.KITKAT)
fun Activity.makeNormalStatusBar(statusBarColor: Int = -1) {
    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    window.decorView.rootView.systemUiVisibility = 0
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.statusBarColor = if (statusBarColor == -1) Color.BLACK else statusBarColor
    }
}

@RequiresApi(Build.VERSION_CODES.KITKAT)
fun Activity.makeTranslucentNavigationBar() {
    window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
        WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
}

@RequiresApi(Build.VERSION_CODES.KITKAT)
fun Activity.makeNormalNavigationBar(navigationBarColor: Int = -1) {
    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
    window.decorView.rootView.systemUiVisibility = 0
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        window.navigationBarColor = if (navigationBarColor == -1) Color.BLACK else navigationBarColor
    }
}

@RequiresApi(Build.VERSION_CODES.M)
fun Activity.lightStatusBar(statusBarColor: Int = -1) {
    when (window.decorView.rootView.systemUiVisibility) {
        0 -> window.decorView.rootView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                window.decorView.rootView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR + View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
            } else {
                window.decorView.rootView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        }
    }
    window.statusBarColor = if (statusBarColor == -1) Color.WHITE else statusBarColor
}

@RequiresApi(Build.VERSION_CODES.O)
fun Activity.lightNavigation(navigationBarColor: Int = -1) {
    when (window.decorView.rootView.systemUiVisibility) {
        0 -> window.decorView.rootView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR -> {
            window.decorView.rootView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR + View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        }
    }
    window.navigationBarColor = if (navigationBarColor == -1) Color.WHITE else navigationBarColor
}