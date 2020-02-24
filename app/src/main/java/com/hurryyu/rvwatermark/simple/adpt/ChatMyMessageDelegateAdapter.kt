package com.hurryyu.rvwatermark.simple.adpt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hurryyu.rvwatermark.simple.model.ChatFrom
import com.hurryyu.rvwatermark.simple.model.ChatMessageBean
import com.hurryyu.rvwatermark.simple.R
import kotlinx.android.synthetic.main.item_chat_message_my.view.*

/**
 * ===================================================================
 * Author: HurryYu http://www.hurryyu.com & https://github.com/HurryYU
 * Email: 1037914505@qq.com
 * Time: 2020/2/21
 * Version: 1.0
 * Description:
 * ===================================================================
 */
class ChatMyMessageDelegateAdapter :
    IChatDelegateAdapter<ChatMyMessageDelegateAdapter.ChatMyMessageViewHolder> {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatMyMessageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chat_message_my, parent, false)
        return ChatMyMessageViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ChatMyMessageViewHolder,
        position: Int,
        item: ChatMessageBean
    ) {
        holder.tvMessage.text = item.message
    }

    override fun canDelegate(data: ChatMessageBean): Boolean = data.chatFrom == ChatFrom.FROM_MY

    class ChatMyMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvMessage: TextView = itemView.tv_my_message
    }
}