package `in`.nowfal.kdroid

import `in`.nowfal.kdroidext.kex.snackbar
import `in`.nowfal.kdroidext.pref.KPrefHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        action_bar_root.snackbar("asdasfd")

        KPrefHelper(this)
    }
}
