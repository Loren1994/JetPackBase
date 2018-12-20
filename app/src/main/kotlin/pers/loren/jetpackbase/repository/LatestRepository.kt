package pers.loren.jetpackbase.repository

import android.arch.lifecycle.LiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import pers.loren.jetpackbase.beans.LatestBean
import pers.loren.jetpackbase.paging.LatestDataSourceFactory

/**
 * Copyright © 2018/11/27 by loren
 */
class LatestRepository {

    fun getPage(): LiveData<PagedList<LatestBean>> {
        return LivePagedListBuilder(LatestDataSourceFactory(),
                PagedList.Config.Builder()
                        .setPageSize(20)
                        .setEnablePlaceholders(true)
                        .setInitialLoadSizeHint(20)
                        .setPrefetchDistance(2)
                        .build())
                .build()
    }

    // other http request
}