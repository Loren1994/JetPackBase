package pers.loren.jetpackbase.interfaces

import pers.loren.jetpackbase.base.ext.Listing

/**
 * Copyright © 2018/12/21 by loren
 */
interface LatestRepository<T> {
    fun getPageWithSize(size: Int): Listing<T>
}