package com.nepplus.practiceproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startBtn.setOnClickListener {
            Intent(this, FragmentActivity::class.java).apply {
                putExtra(FRAGMENT_TYPE, 0)
                startActivity(this)
            }
        }
    }

    companion object{
        const val KEY_DATA = "KEY_DATA"
        const val REQUEST_CODE = 1001
        const val RESULT_TITLE = "RESULT_TITLE"
        const val FRAGMENT_TYPE = "FRAGMENT_TYPE"
    }
}