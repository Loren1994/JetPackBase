package pers.loren.jetpackbase.repository

import android.arch.lifecycle.MutableLiveData
import com.google.gson.reflect.TypeToken
import pers.loren.jetpackbase.base.ext.LATEST_URL
import pers.loren.jetpackbase.base.ext.http
import pers.loren.jetpackbase.base.ext.parseObject
import pers.loren.jetpackbase.beans.LatestBean

/**
 * Copyright © 2018/11/27 by loren
 */
class LatestRepository {
    var data: MutableLiveData<MutableList<LatestBean>> = MutableLiveData()

    fun getLatestList() {
        http {
            url = LATEST_URL
            success = {
                val latestList = parseObject<MutableList<LatestBean>>(it, object : TypeToken<MutableList<LatestBean>>() {}.type)
                data.postValue(latestList)
            }
            fail = { }
        }
    }
}