package pers.loren.jetpackbase.adapters

import android.annotation.SuppressLint
import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_ble_list.*
import pers.loren.jetpackbase.R
import pers.loren.jetpackbase.beans.LatestBean

/**
 * Copyright © 2018/11/27 by loren
 */
class LatestAdapter(diffCallback: DiffUtil.ItemCallback<LatestBean>) : PagedListAdapter<LatestBean, LatestAdapter.Holder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.item_ble_list, parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.ble_name_tv.text = "${position + 1}. ${(getItem(position) as LatestBean).title}"
    }

    class Holder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer
}