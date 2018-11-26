package pers.loren.jetpackbase.base.ext

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.orhanobut.logger.Logger
import pers.loren.jetpackbase.R
import org.greenrobot.eventbus.EventBus
import java.lang.reflect.Type

fun ImageView.url(url: String, holder: Int = R.drawable.ic_default_avatar, error: Int = R.drawable.ic_default_avatar) = Glide.with(this.context)
        .load(url)
        .into(this)

inline fun <reified T> parseObject(json: String?) = Gson().fromJson(json, T::class.java)!!

fun <T> parseObject(json: String?, type: Type): T = Gson().fromJson(json, type)

fun postEvent(event: Any) = EventBus.getDefault().post(event)

fun postStickyEvent(event: Any) = EventBus.getDefault().postSticky(event)

fun log(log: Any?) = Logger.i(log.toString())
fun json(json: Any?) = Logger.json(json.toString())
fun err(error: Any?) = Logger.e(error.toString())



