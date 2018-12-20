package pers.loren.jetpackbase.paging

import android.arch.paging.PageKeyedDataSource
import com.google.gson.reflect.TypeToken
import pers.loren.jetpackbase.base.ext.LATEST_URL
import pers.loren.jetpackbase.base.ext.http
import pers.loren.jetpackbase.base.ext.log
import pers.loren.jetpackbase.base.ext.parseObject
import pers.loren.jetpackbase.beans.LatestBean
import pers.victor.ext.toast

/**
 * Copyright © 2018/11/27 by loren
 * 要显示来自后端服务器的数据，请使用Retrofit API的同步版本将信息加载到您自己的自定义DataSource对象中
 */
class LatestDateSource : PageKeyedDataSource<Int, LatestBean>() {

    private var pageIndex = 1

    //初始请求数据,必须要同步请求
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, LatestBean>) {
        log(">>>loadInitial")
        getLatestList(page = pageIndex, size = params.requestedLoadSize) {
            callback.onResult(it, null, ++pageIndex)
        }
    }

    //请求后续数据,异步
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, LatestBean>) {
        log(">>>loadAfter")
        getLatestList(page = pageIndex, size = params.requestedLoadSize) {
            callback.onResult(it, params.key)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, LatestBean>) {
        log(">>>loadBefore - ${params.key}")
    }

    private fun getLatestList(page: Int, size: Int, callback: (temp: MutableList<LatestBean>) -> Unit) {
        log(">>>getLatestList - $page - $size")
        http {
            url = LATEST_URL
            success = {
                val latestList = parseObject<MutableList<LatestBean>>(it, object : TypeToken<MutableList<LatestBean>>() {}.type)
                callback.invoke(latestList)
            }
            fail = { toast("请求失败") }
        }
    }
}