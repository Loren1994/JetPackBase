package pers.loren.jetpackbase.paging

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import pers.loren.jetpackbase.beans.LatestBean

/**
 * Copyright © 2018/11/27 by loren
 */
class LatestDataSourceFactory : DataSource.Factory<Int, LatestBean>() {

    var sourceLiveData = MutableLiveData<LatestDateSource>()

    override fun create(): DataSource<Int, LatestBean> {
        val dataSource = LatestDateSource()
        sourceLiveData.postValue(dataSource)
        return dataSource
    }
}