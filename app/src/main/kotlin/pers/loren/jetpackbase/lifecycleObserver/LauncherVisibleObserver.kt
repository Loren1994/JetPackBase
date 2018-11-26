package pers.loren.jetpackbase.lifecycleObserver

import pers.loren.jetpackbase.base.ext.log
import pers.loren.jetpackbase.interfaces.IHomeChangeFragment

/**
 * Copyright © 2018/11/23 by loren
 */
class LauncherVisibleObserver(var flag: String = "", val iHomeChangeFragment: IHomeChangeFragment? = null) : CommonLifeObserver() {

    override fun start() {
        log("$flag stop")
        iHomeChangeFragment?.visible()
    }
}