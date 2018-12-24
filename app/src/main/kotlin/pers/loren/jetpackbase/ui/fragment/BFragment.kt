package pers.loren.jetpackbase.ui.fragment

import android.arch.lifecycle.Observer
import android.view.View
import androidx.work.*
import kotlinx.android.synthetic.main.b_fragment.*
import pers.loren.jetpackbase.R
import pers.loren.jetpackbase.base.ext.log
import pers.loren.jetpackbase.base.ui.BaseFragment
import pers.loren.jetpackbase.workManager.ScheduleWork
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Copyright © 2018/11/23 by loren
 */
class BFragment : BaseFragment() {

    override fun useTitleBar() = true

    override fun initWidgets() {
        setTitleBarText("WorkManager")
        work_tv.setOnClickListener { addWork() }
        stop_tv.setOnClickListener { stopWork() }
    }

    private fun addWork() {
        val dateFormat = SimpleDateFormat("hh:mm:ss", Locale.getDefault())
        val constraint = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresBatteryNotLow(true)//不在电量不足时执行
                .setRequiresCharging(true)//在充电时执行
                .setRequiresStorageNotLow(true)//不在存储容量不足时执行
                .build()
        val data = Data.Builder()
                .putString("time", dateFormat.format(Date()))
                .build()
        /*最小间隔15min - 可用单任务中执行新的单任务规避15min的限制*/
        val periodRequest = PeriodicWorkRequest.Builder(ScheduleWork::class.java,
                1, TimeUnit.MINUTES)
                .setConstraints(constraint)
                .setInputData(data)
                .build()
        WorkManager.getInstance()
                .getWorkInfoByIdLiveData(periodRequest.id)
                .observe(this, Observer {
                    log("${it?.state}\n${it?.outputData?.getString("name")}")
                    //FIXME - outputData.getString() return null
                })
        WorkManager.getInstance().enqueue(periodRequest)
    }

    private fun stopWork() {
        WorkManager.getInstance().cancelAllWork()
    }

    override fun setListeners() {
    }

    override fun onWidgetsClick(v: View) {
    }

    override fun bindLayout() = R.layout.b_fragment
}