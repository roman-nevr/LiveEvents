package com.example.liveevents

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.ConcurrentLinkedQueue

class MultipleLiveEvents<T>: LiveData<T>() {

    private val queue = ConcurrentLinkedQueue<T>()

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, Observer {
            var value = queue.poll()
            while (value != null) {
                observer.onChanged(value)
                value = queue.poll()
            }
        })
    }

    public override fun postValue(value: T) {
        queue.add(value)
        super.postValue(value)
    }
}
