package pers.loren.jetpackbase

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.squareup.leakcanary.LeakCanary
import pers.victor.ext.Ext

/**
 * Copyright © 2018/5/16 by loren
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Ext.ctx = this
        initLogger()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
    }

    private fun initLogger() {
        val formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)
                .methodCount(0)
                .tag("loren")
                .build()
        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
    }
}
