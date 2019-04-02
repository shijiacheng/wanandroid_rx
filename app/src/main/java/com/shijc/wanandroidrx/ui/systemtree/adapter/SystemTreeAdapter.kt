package com.shijc.wanandroidrx.ui.systemtree.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.flexbox.FlexboxLayout
import com.shijc.wanandroidrx.R
import com.shijc.wanandroidrx.ui.systemtree.bean.SystemTreeResult

/**
 * @Package com.shijc.wanandroidkotlin.ui.systemtree.adapter
 * @Description:
 * @author shijiacheng
 * @date 2019/2/14 上午 10:37
 * @version V1.0
 */
class SystemTreeAdapter(private val context:Context,private val data:List<SystemTreeResult.Data>) : RecyclerView.Adapter<SystemTreeAdapter.SystemTreeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SystemTreeViewHolder {
        return SystemTreeViewHolder(LayoutInflater.from(context).inflate(R.layout.item_system_tree,parent,false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: SystemTreeViewHolder, position: Int) {

        holder.tvTitle.text = data[position].name
        holder.flexboxLayout.removeAllViews()
        for(item in data[position].children){
            var textView = TextView(context)
            textView.text= item.name
            textView.setBackgroundResource(R.drawable.bg_shape_rectangle_gray)
            textView.setPadding(40,15,40,15)

            var params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
            params.setMargins(10,10,10,10)
            textView.layoutParams = params
            holder.flexboxLayout.addView(textView)
        }

        holder.itemView.setOnClickListener {
            if (listener!=null){
                listener!!.onItemClick(data[position],position,it)
            }
        }


    }

    inner class SystemTreeViewHolder(view: View):RecyclerView.ViewHolder(view){
        var flexboxLayout: FlexboxLayout = view.findViewById(R.id.fbl_container)
        var tvTitle:TextView = view.findViewById(R.id.tv_title)

    }


    var listener:ClickListener? = null

    interface ClickListener{
        fun onItemClick(item:SystemTreeResult.Data,position:Int,view: View)
    }

}