package com.example.liveevents

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.events.observe(this, Observer { time ->
            log("delivered $time", "TEST_LOG")
            textView.text = textView.text.toString() + "\n" + time
            Toast.makeText(this, time.toString(), Toast.LENGTH_SHORT).show()
        })
    }
}

fun Any.log(log: String, tag: String? = null) {
    if (BuildConfig.DEBUG) {
        Log.w(
                (tag ?: this::class.java.simpleName).take(23),
                log
        )
    }
}
