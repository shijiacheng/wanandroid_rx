package com.shijc.wanandroidrx.ui.wxarticle.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.shijc.wanandroidrx.R
import com.shijc.wanandroidrx.ui.home.bean.ArticleModel
import com.shijc.wanandroidrx.utils.TimeUtils
import com.shijc.wanandroidrx.utils.UIhelper

/**
 * @Package com.shijc.wanandroidkotlin.ui.systemtree.adapter
 * @Description:
 * @author shijiacheng
 * @date 2019/2/14 上午 10:37
 * @version V1.0
 */
class WxArticleListAdapter(private val context:Context, private val data:List<ArticleModel>) : RecyclerView.Adapter<WxArticleListAdapter.WxArticleListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WxArticleListViewHolder {
        return WxArticleListViewHolder(LayoutInflater.from(context).inflate(R.layout.item_wx_article,parent,false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: WxArticleListViewHolder, position: Int) {

        holder.tvTitle.text = data[position].title
        holder.tvTime.text = TimeUtils.long2String(data[position].publishTime,TimeUtils.FORMAT_TYPE_1)

        holder.itemView.setOnClickListener {
            UIhelper.openWebView(context,data[position].link)
        }
    }

    inner class WxArticleListViewHolder(view: View):RecyclerView.ViewHolder(view){
        var tvTitle: TextView = view.findViewById(R.id.tv_title)
        var tvTime: TextView = view.findViewById(R.id.tv_time)
    }

}