package pers.loren.jetpackbase.paging

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import com.google.gson.reflect.TypeToken
import pers.loren.jetpackbase.base.ext.*
import pers.loren.jetpackbase.beans.LatestBean

/**
 * Copyright © 2018/11/27 by loren
 * 要显示来自后端服务器的数据，请使用Retrofit API的同步版本将信息加载到您自己的自定义DataSource对象中
 */
class LatestDateSource : PageKeyedDataSource<Int, LatestBean>() {

    var pageIndex = 1
    val loadState = MutableLiveData<NetworkState>()

    //初始请求数据,必须要同步请求
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, LatestBean>) {
        log(">>>loadInitial")
        loadState.postValue(NetworkState.LOADING)
        pageIndex = 1
        getLatestList(page = pageIndex, size = params.requestedLoadSize) {
            callback.onResult(it, 0, ++pageIndex)
        }
    }

    //请求后续数据,异步
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, LatestBean>) {
        log(">>>loadAfter - ${params.key}")
        loadState.postValue(NetworkState.AFTER_LOADING)
        getLatestList(page = pageIndex, size = params.requestedLoadSize) {
            callback.onResult(it, ++pageIndex)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, LatestBean>) {
        log(">>>loadBefore - ${params.key}")
    }

    private fun getLatestList(page: Int, size: Int, callback: (temp: MutableList<LatestBean>) -> Unit) {
        //模拟已到最后一页
        if (pageIndex == 3) {
            loadState.postValue(NetworkState.COMPLETE)
            return
        }
        log(">>>getLatestList - $page - $size")
        httpGetBySync {
            url = LATEST_URL
            success = {
                val latestList = parseObject<MutableList<LatestBean>>(it, object : TypeToken<MutableList<LatestBean>>() {}.type)
                callback.invoke(latestList)
                loadState.postValue(NetworkState.LOADED)
            }
            fail = { loadState.postValue(NetworkState.error(it)) }
        }
    }
}