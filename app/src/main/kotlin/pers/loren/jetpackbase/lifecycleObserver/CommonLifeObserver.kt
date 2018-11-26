package pers.loren.jetpackbase.lifecycleObserver

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import pers.loren.jetpackbase.base.ext.log

/**
 * Copyright © 2018/11/23 by loren
 */
abstract class CommonLifeObserver : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    open fun start() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    open fun stop() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    open fun change() {
    }

}