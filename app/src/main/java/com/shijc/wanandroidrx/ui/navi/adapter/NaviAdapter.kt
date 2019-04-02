package com.shijc.wanandroidrx.ui.navi.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.flexbox.FlexboxLayout
import com.shijc.wanandroidrx.R
import com.shijc.wanandroidrx.ui.navi.bean.NaviResult
import com.shijc.wanandroidrx.utils.UIhelper

/**
 * @Package com.shijc.wanandroidkotlin.ui.systemtree.adapter
 * @Description:
 * @author shijiacheng
 * @date 2019/2/14 上午 10:37
 * @version V1.0
 */
class NaviAdapter(private val context:Context, private val data:List<NaviResult.Data>) : RecyclerView.Adapter<NaviAdapter.NaviViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NaviViewHolder {
        return NaviViewHolder(LayoutInflater.from(context).inflate(R.layout.item_navi,parent,false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: NaviViewHolder, position: Int) {

        holder.tvTitle.text = data[position].name
        holder.flexboxLayout.removeAllViews()
        for(item in data[position].articles){
            var textView = TextView(context)
            textView.text= item.title
            textView.setBackgroundResource(R.drawable.bg_shape_rectangle_gray)
            textView.setPadding(40,15,40,15)

            var params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
            params.setMargins(10,10,10,10)
            textView.layoutParams = params
            textView.setOnClickListener {
                UIhelper.openWebView(context,item.link)
            }
            holder.flexboxLayout.addView(textView)
        }

    }

    inner class NaviViewHolder(view: View):RecyclerView.ViewHolder(view){
        var flexboxLayout: FlexboxLayout = view.findViewById(R.id.fbl_container)
        var tvTitle:TextView = view.findViewById(R.id.tv_title)
    }


}