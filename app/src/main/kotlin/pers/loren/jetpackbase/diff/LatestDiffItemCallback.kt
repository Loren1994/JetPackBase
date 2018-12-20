package pers.loren.jetpackbase.diff

import android.support.v7.util.DiffUtil
import pers.loren.jetpackbase.beans.LatestBean

/**
 * Copyright © 2018/11/27 by loren
 */
class LatestDiffItemCallback : DiffUtil.ItemCallback<LatestBean>() {
    override fun areItemsTheSame(oldConcert: LatestBean,
                                 newConcert: LatestBean): Boolean =
            oldConcert.id == newConcert.id

    override fun areContentsTheSame(oldConcert: LatestBean,
                                    newConcert: LatestBean): Boolean =
            oldConcert == newConcert
}
