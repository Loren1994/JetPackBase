package pers.loren.jetpackbase.diff

import android.support.v7.util.DiffUtil

/**
 * Copyright © 2018/7/11 by loren
 */
class ListDiffCallback(val oldList: List<String>, val newList: List<String>) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    //有唯一字段就用唯一字段判断
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newList[newItemPosition].contentEquals(oldList[oldItemPosition])
    }

    //判断数据是否含有相同内容
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newList[newItemPosition].contentEquals(oldList[oldItemPosition])
    }

}