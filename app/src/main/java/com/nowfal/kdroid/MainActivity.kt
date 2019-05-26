package com.nowfal.kdroid

import `in`.nowfal.kdroid.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nowfal.kdroidext.Utils.setImageUrl
import com.nowfal.kdroidext.kotx.ext.app.broadcastLocal
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
setImageUrl("https://vtoqas3.s3.amazonaws.com/da9e3825132c34e5799e61fc08a22336_avatar_5c5568f1a87ba.png",iv)
        this.broadcastLocal("ASd")
    }
}
