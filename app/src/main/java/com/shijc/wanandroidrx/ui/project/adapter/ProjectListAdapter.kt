package com.shijc.wanandroidrx.ui.project.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
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
class ProjectListAdapter(private val context:Context, private val data:List<ArticleModel>) : RecyclerView.Adapter<ProjectListAdapter.ProjectListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectListViewHolder {
        return ProjectListViewHolder(LayoutInflater.from(context).inflate(R.layout.item_project_list,parent,false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ProjectListViewHolder, position: Int) {

        Glide.with(context).load(data[position].envelopePic).asBitmap().into(holder.ivCover)
        holder.tvTitle.text = data[position].title
        holder.tvDesc.text = data[position].desc
        holder.tvAuthor.text = data[position].author
        holder.tvTime.text = TimeUtils.long2String(data[position].publishTime,TimeUtils.FORMAT_TYPE_1)

        holder.itemView.setOnClickListener {
            UIhelper.openWebView(context,data[position].link)
        }
    }

    inner class ProjectListViewHolder(view: View):RecyclerView.ViewHolder(view){
        var ivCover: ImageView = view.findViewById(R.id.iv_cover)
        var tvTitle: TextView = view.findViewById(R.id.tv_title)
        var tvDesc: TextView = view.findViewById(R.id.tv_desc)
        var tvAuthor: TextView = view.findViewById(R.id.tv_author)
        var tvTime: TextView = view.findViewById(R.id.tv_time)
    }

}