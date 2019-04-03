package com.shijc.wanandroidrx.ui.wxarticle.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.shijc.wanandroidrx.R;
import com.shijc.wanandroidrx.ui.home.bean.ArticleModel;
import com.shijc.wanandroidrx.utils.TimeUtils;
import com.shijc.wanandroidrx.utils.UIhelper;

import java.util.List;

public class WxArticleListAdapter extends RecyclerView.Adapter<WxArticleListAdapter.WxArticleListViewHolder> {

    private Context context;
    private List<ArticleModel> data;

    public WxArticleListAdapter(Context context, List<ArticleModel> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public WxArticleListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WxArticleListViewHolder(LayoutInflater.from(context).inflate(R.layout.item_wx_article,parent,false));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    @Override
    public void onBindViewHolder(@NonNull WxArticleListAdapter.WxArticleListViewHolder holder, final int position) {
        holder.tvTitle.setText(data.get(position).getTitle());
        holder.tvTime.setText(TimeUtils.long2String(data.get(position).getPublishTime(),TimeUtils.FORMAT_TYPE_1));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIhelper.openWebView(context,data.get(position).getLink());
            }
        });
    }


    class WxArticleListViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvTime;

        public WxArticleListViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvTime = itemView.findViewById(R.id.tv_time);
        }
    }

}
