package com.redmadrobot.sample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_launcher.*

class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)

        btnBasicGallery.setOnClickListener { startSample(SampleType.BASIC) }
        btnCustomGallery.setOnClickListener { startSample(SampleType.CUSTOM) }
    }

    private fun startSample(type: SampleType) {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra(MainActivity.EXTRA_SAMPLE_TYPE, type)
        }
        startActivity(intent)
    }

}