package com.example.liveevents

import androidx.lifecycle.ViewModel
import kotlin.concurrent.thread

class MainViewModel: ViewModel() {

    val events = MultipleLiveEvents<Int>()

    init {
        thread {
            while (true) {
                val time = (System.currentTimeMillis() / 1000) % 10000
                events.postValue(time.toInt())
                log("produced $time", "TEST_LOG")
                Thread.sleep(5000)
            }
        }
    }

}
