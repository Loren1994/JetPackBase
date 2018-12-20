package pers.loren.jetpackbase.base.ext

import android.os.Handler
import android.os.Looper
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import pers.victor.ext.isNetworkConnected
import pers.victor.ext.spGetString
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * Created by Victor on 2017/6/16. (ง •̀_•́)ง
 */

private val client = OkHttpClient.Builder().connectTimeout(15, TimeUnit.SECONDS).build()
private val json = MediaType.parse("application/json; charset=utf-8")
private val handler = Handler(Looper.getMainLooper())

class Http {
    lateinit var url: String
    var body: JSONObject? = null
    var success: ((json: String?) -> Unit)? = null
    var fail: ((reason: String?) -> Unit)? = null
}

fun http(create: Http.() -> Unit) {
    val h = Http().apply { create() }
    if (h.body == null) httpGet(h) else httpPost(h)
}

private fun httpGet(http: Http) {
    if (!isNetworkConnected()) {
        http.fail?.invoke("网络未连接")
        return
    }
    val url = http.url
    val request = Request.Builder()
            .url(url)
            .build()
//    var urlLog = "get: $url\n${request.headers()}"
//    if ("?" in url) {
//        val s = url.split("?").drop(1)[0]
//        val params: String
//        if ("&" in s) {
//            val array = s.split("&")
//            params = array.joinToString("\n") {
//                val kv = it.split("=")
//                if (kv.size > 1) {
//                    "${kv[0]} = ${kv[1]}"
//                } else {
//                    "${kv[0]} = "
//                }
//            }
//        } else {
//            val kv = s.split("=")
//            params = if (kv.size > 1) {
//                "${kv[0]} = ${kv[1]}"
//            } else {
//                "${kv[0]} = "
//            }
//        }
//
//        urlLog += params
//    }
//    log(urlLog)
    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            if (e.message != null && !e.message!!.contentEquals("Canceled"))
                httpFailure(e, http)
        }

        @Throws(IOException::class)
        override fun onResponse(call: Call, response: Response) {
            httpResponse(response, http)
        }
    })
}

private fun httpPost(http: Http) {
    if (!isNetworkConnected()) {
        http.fail?.invoke("网络未连接，请检查网络设置！")
        return
    }
    val jo = http.body!!
    val requestBody = RequestBody.create(json, jo.toString())
    val request = Request.Builder()
            .url(http.url)
            .post(requestBody)
            .build()
    log("post: ${http.url}\n${request.headers()}")
    json(jo.toString())
    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            if (e.message != null && !e.message!!.contentEquals("Canceled"))
                httpFailure(e, http)
        }

        @Throws(IOException::class)
        override fun onResponse(call: Call, response: Response) {
            httpResponse(response, http)
        }
    })
}

private fun httpFailure(e: IOException, http: Http) {
    var msg = e.message ?: "网络错误"
    if ("failed to connect to" in msg.toLowerCase() || "timeout" in msg.toLowerCase()) {
        msg = "请求失败"
    }
    handler.post { http.fail?.invoke(msg) }
}

private fun httpResponse(response: Response, http: Http) {
    val result = response.body()?.string() ?: ""
//    json(result)
    val jo: JSONArray //JSONObject
    try {
        jo = JSONArray(result)
    } catch (e: Exception) {
        e.printStackTrace()
//        err("response = $result")
        handler.post { http.fail?.invoke("请求错误") }
        return
    }
//    if (jo.has("statusCode")) {
//        if ((jo.getInt("statusCode")) == 200) {
//            handler.post {
                http.success?.invoke(jo.toString())
//            }
//        } else {
//            handler.post { http.fail?.invoke(jo.getString("statusMsg")) }
//        }
//    } else {
//        err(result)
//        handler.post { http.fail?.invoke("请求失败") }
//    }
}