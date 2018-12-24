package pers.loren.jetpackbase.workManager

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import pers.loren.jetpackbase.base.ext.log

/**
 * Copyright © 2018/12/21 by loren
 */
class ScheduleWork(context: Context, workerParameters: WorkerParameters)
    : Worker(context, workerParameters) {
    override fun doWork(): Result {
        val data = inputData.getString("time")
        log(">>>ScheduleWork执行一次\n接收值:$data")
        val outData = Data.Builder()
                .putString("name", "this is output data!!!")
                .build()
        return Result.success(outData)
    }
}