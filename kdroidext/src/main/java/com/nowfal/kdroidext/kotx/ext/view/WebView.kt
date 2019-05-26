

package com.nowfal.kdroidext.kotx.ext.view

import android.os.Build
import android.webkit.WebView
import com.nowfal.kdroidext.kotx.ext.basic.toBase64String


/**
 * Evaluates [script] in the currently loaded document.
 */
fun WebView.injectJS(script: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        evaluateJavascript(script, null)
    } else {
        loadUrl("javascript:$script")
    }
}

/**
 * Injects specified [css] style into currently loaded document.
 */
fun WebView.injectCSS(css: String) {
    injectJS("""(function() {
var style = document.createElement('style');
style.type = 'text/css';
style.innerHTML = window.atob('${css.toByteArray().toBase64String()}');
document.getElementsByTagName('head').item(0).appendChild(style);
})()
        """)
}
/*
            javascript:(function() {
                var parent = document.getElementsByTagName('head').item(0);
                var script = document.createElement('script');
                script.type = 'text/javascript';
                script.innerHTML = window.atob('$enc');
                parent.appendChild(script);
                })()
            """)
 */