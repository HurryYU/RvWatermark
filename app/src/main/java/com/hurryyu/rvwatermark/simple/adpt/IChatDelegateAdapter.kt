package com.hurryyu.rvwatermark.simple.adpt

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hurryyu.rvwatermark.simple.model.ChatMessageBean

/**
 * ===================================================================
 * Author: HurryYu http://www.hurryyu.com & https://github.com/HurryYU
 * Email: 1037914505@qq.com
 * Time: 2020/2/21
 * Version: 1.0
 * Description:
 * ===================================================================
 */
interface IChatDelegateAdapter<VH : RecyclerView.ViewHolder> {
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH

    fun onBindViewHolder(holder: VH, position: Int, item: ChatMessageBean)

    fun canDelegate(data: ChatMessageBean): Boolean
}