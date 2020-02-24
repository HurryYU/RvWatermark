package com.hurryyu.rvwatermark.simple.model

import com.hurryyu.rvwatermark.simple.model.ChatFrom

/**
 * ===========================================================
 * Author: HurryYu Coding：https://coding.net/u/yuzihao
 * Email: 1037914505@qq.com
 * Time: 2020/2/21 18:35
 * Version: 1.0
 * Description:
 * ===========================================================
 */
data class ChatMessageBean(
    val id: Long,
    val chatFrom: ChatFrom,
    val message: String
)