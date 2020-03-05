package com.hurryyu.rvwatermark.simple

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hurryyu.rvwatermark.RvWatermark
import com.hurryyu.rvwatermark.drawable.TextWatermarkDrawable
import com.hurryyu.rvwatermark.simple.adpt.ChatFriendMessageDelegateAdapter
import com.hurryyu.rvwatermark.simple.adpt.ChatMessageAdapter
import com.hurryyu.rvwatermark.simple.adpt.ChatMyMessageDelegateAdapter
import com.hurryyu.rvwatermark.simple.model.ChatFrom
import com.hurryyu.rvwatermark.simple.model.ChatMessageBean
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val chatMessageList = ArrayList<ChatMessageBean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapters = ChatMessageAdapter().apply {
            addDelegateAdapter(ChatFriendMessageDelegateAdapter())
            addDelegateAdapter(ChatMyMessageDelegateAdapter())
        }
        rv.apply {
            addItemDecoration(ChatMessageSpaceDecoration())
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = adapters
        }
        initData()
        adapters.setNewData(chatMessageList)
        addWatermark(rv)
    }

    private fun addWatermark(recyclerView: RecyclerView) {
//        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.icon_android)
//        val drawable = BitmapWatermarkDrawable.build(bitmap) {
//            watermarkBitmapAlpha(0.5F)
//            watermarkBitmapDegrees(0F)
//            watermarkBitmapScaleValue(1F)
//        }

        val drawable = TextWatermarkDrawable.build("HurryYu") {
//            watermarkTextAlpha(0.5F)
//            watermarkTextColor(Color.GRAY)
//            watermarkTextSize(12F)
//            watermarkTextDegrees(-30F)
        }
        RvWatermark.build(drawable) {
//            isFollowScroll(true)
//            watermarkColumnNumber(3)
//            watermarkRowHeight(120)
        }.bindToRecyclerView(recyclerView)
    }

    private fun initData() {
        for (i in 0 until 5) {
            chatMessageList.add(
                ChatMessageBean(
                    1,
                    ChatFrom.FROM_MY,
                    "老婆在吗?"
                )
            )
            chatMessageList.add(
                ChatMessageBean(
                    2,
                    ChatFrom.FROM_MY,
                    "怎么不理我?"
                )
            )
            chatMessageList.add(
                ChatMessageBean(
                    3,
                    ChatFrom.FROM_FRIEND,
                    "没有不理你,在老王家忙"
                )
            )
            chatMessageList.add(
                ChatMessageBean(
                    4,
                    ChatFrom.FROM_MY,
                    "好的"
                )
            )
            chatMessageList.add(
                ChatMessageBean(
                    5,
                    ChatFrom.FROM_MY,
                    "那你早点回来呀"
                )
            )
            chatMessageList.add(
                ChatMessageBean(
                    6,
                    ChatFrom.FROM_MY,
                    "我饭已经做好了,等你回来啊"
                )
            )
            chatMessageList.add(
                ChatMessageBean(
                    7,
                    ChatFrom.FROM_FRIEND,
                    "啊~快...知&.知道了"
                )
            )
        }
    }
}
