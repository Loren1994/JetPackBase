package pers.loren.jetpackbase.viewModels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import pers.loren.jetpackbase.repository.LatestRepositoryImpl


/**
 * Copyright © 2018/11/26 by loren
 * 将数据逻辑放到 ViewModel 类中,ViewModel 应该作为 UI 控制器和应用程序其它部分的连接服务
 * 不是由 ViewModel 负责获取数据（例如：从网络获取）
 * 相反，ViewModel 调用相应的组件获取数据,然后将数据获取结果提供给 UI 控制器
 * 不要在 ViewModel 中引用 View 或者 Activity 的 context
 */
class LatestViewModel(val latestRepository: LatestRepositoryImpl) : ViewModel() {

    private var data: MutableLiveData<Any> = MutableLiveData()

    private val repoResult = Transformations.map(data) {
        latestRepository.getPageWithSize(20)
    }!!

    val page = Transformations.switchMap(repoResult) { it.pagedList }!!
    val networkState = Transformations.switchMap(repoResult) { it.networkState }!!

    fun refresh() {
        repoResult.value?.refresh?.invoke()
    }

    fun showData() {
        data.value = null
    }

}