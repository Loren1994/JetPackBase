package pers.loren.jetpackbase.base.ext

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList

/**
 * Copyright © 2018/12/21 by loren
 */
data class Listing<T>(
        val pagedList: LiveData<PagedList<T>>,
        val networkState: LiveData<NetworkState>,
        val refresh: () -> Unit)