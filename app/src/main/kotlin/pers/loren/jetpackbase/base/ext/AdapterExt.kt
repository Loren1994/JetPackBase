package pers.loren.jetpackbase.base.ext

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer
import pers.victor.ext.inflate

class Holder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer

abstract class CommonAdapter : RecyclerView.Adapter<Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = Holder(inflate(bindLayout(), parent))

    abstract fun bindLayout(): Int
}