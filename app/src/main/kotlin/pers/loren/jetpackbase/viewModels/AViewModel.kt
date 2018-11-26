package pers.loren.jetpackbase.viewModels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.google.gson.reflect.TypeToken
import pers.loren.jetpackbase.beans.LatestBean
import pers.loren.jetpackbase.base.ext.LATEST_URL
import pers.loren.jetpackbase.base.ext.http
import pers.loren.jetpackbase.base.ext.parseObject


/**
 * Copyright © 2018/11/26 by loren
 */
class AViewModel : ViewModel() {

    private var data: MutableLiveData<MutableList<LatestBean>> = MutableLiveData()

    fun getData(): LiveData<MutableList<LatestBean>> {
        http {
            url = LATEST_URL
            success = {
                val latestList = parseObject<MutableList<LatestBean>>(it, object : TypeToken<MutableList<LatestBean>>() {}.type)
                data.postValue(latestList)
            }
            fail = { }
        }
        return data
    }
}