package pers.loren.jetpackbase.base.ext

/**
 * Copyright © 2018/12/21 by loren
 */
@Suppress("DataClassPrivateConstructor")
data class NetworkState private constructor(val status: Status, val msg: String? = null) {
    companion object {
        val LOADED = NetworkState(Status.SUCCESS)
        val LOADING = NetworkState(Status.RUNNING)
        val COMPLETE = NetworkState(Status.COMPLETE)
        val AFTER_LOADING = NetworkState(Status.AFTER_LOADING)
        fun error(msg: String?) = NetworkState(Status.FAILED, msg)
    }
}

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED,
    COMPLETE,
    AFTER_LOADING
}

