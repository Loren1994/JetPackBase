package pers.loren.jetpackbase.lifecycleObserver

import pers.loren.jetpackbase.base.ext.log

/**
 * Copyright © 2018/11/23 by loren
 */
class LauncherObserver(var flag: String = "") : CommonLifeObserver() {

    override fun start() {
        log("$flag start")
    }

    override fun stop() {
        log("$flag stop")
    }
}