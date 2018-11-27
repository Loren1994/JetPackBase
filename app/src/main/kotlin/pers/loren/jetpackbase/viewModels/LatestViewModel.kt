package pers.loren.jetpackbase.viewModels

import android.arch.lifecycle.ViewModel
import pers.loren.jetpackbase.repository.LatestRepository


/**
 * Copyright © 2018/11/26 by loren
 * 将数据逻辑放到 ViewModel 类中,ViewModel 应该作为 UI 控制器和应用程序其它部分的连接服务
 * 不是由 ViewModel 负责获取数据（例如：从网络获取）
 * 相反，ViewModel 调用相应的组件获取数据,然后将数据获取结果提供给 UI 控制器
 * 不要在 ViewModel 中引用 View 或者 Activity 的 context
 */
class LatestViewModel(val latestRepository: LatestRepository) : ViewModel() {

    val data = latestRepository.data

    fun getLatest() {
        latestRepository.getLatestList()
    }
}