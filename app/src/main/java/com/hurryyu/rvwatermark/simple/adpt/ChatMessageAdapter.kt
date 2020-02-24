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
class ChatMessageAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val chatMessageList = ArrayList<ChatMessageBean>()
    private val delegateAdapterList = ArrayList<IChatDelegateAdapter<RecyclerView.ViewHolder>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val delegateAdapter = delegateAdapterList[viewType]
        return delegateAdapter.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val delegateAdapter = delegateAdapterList[holder.itemViewType]
        delegateAdapter.onBindViewHolder(holder, position, chatMessageList[position])
    }

    fun addDelegateAdapter(delegateAdapter: IChatDelegateAdapter<*>): ChatMessageAdapter {
        @Suppress("UNCHECKED_CAST")
        delegateAdapterList.add(delegateAdapter as IChatDelegateAdapter<RecyclerView.ViewHolder>)
        return this
    }

    fun setNewData(data: List<ChatMessageBean>) {
        chatMessageList.clear()
        chatMessageList.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        val chatMessageBean = chatMessageList[position]
        for (delegateAdapter in delegateAdapterList) {
            if (delegateAdapter.canDelegate(chatMessageBean)) {
                return delegateAdapterList.indexOf(delegateAdapter)
            }
        }
        throw RuntimeException("no delegateAdapter found")
    }

    override fun getItemCount(): Int = chatMessageList.size
}