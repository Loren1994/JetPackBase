package pers.loren.jetpackbase.repository

import android.arch.lifecycle.Transformations
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import pers.loren.jetpackbase.base.ext.Listing
import pers.loren.jetpackbase.beans.LatestBean
import pers.loren.jetpackbase.interfaces.LatestRepository
import pers.loren.jetpackbase.paging.LatestDataSourceFactory

/**
 * Copyright © 2018/11/27 by loren
 */
class LatestRepositoryImpl : LatestRepository<LatestBean> {

    override fun getPageWithSize(size: Int): Listing<LatestBean> {
        val factory = LatestDataSourceFactory()
        val loadState = Transformations.switchMap(factory.sourceLiveData) { it.loadState }
        return Listing<LatestBean>(LivePagedListBuilder(factory,
                PagedList.Config.Builder()
                        .setPageSize(size)
                        .setEnablePlaceholders(true)
                        .setInitialLoadSizeHint(size)
                        .setPrefetchDistance(size / 3)
                        .build())
                .build(), loadState) { factory.sourceLiveData.value?.invalidate() }
    }

    // other http request
}