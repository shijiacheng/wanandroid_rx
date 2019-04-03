package com.shijc.wanandroidrx.ui.collection.adapter;

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

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.collection.adapter
 * @Description:
 * @date 2019/4/2 下午 8:00
 */
public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.CollectionViewHolder> {

    private Context context;
    private List<ArticleModel> data;

    public CollectionAdapter(Context context, List<ArticleModel> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public CollectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CollectionViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rv_article,parent,false));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionViewHolder holder, final int position) {
        if (holder instanceof CollectionViewHolder) {
            holder.tvAuthor.setText(context.getString(R.string.collect_author,data.get(position).getAuthor()));
            holder.tvTime.setText(context.getString(R.string.collect_time,TimeUtils.long2String(data.get(position).getPublishTime(), TimeUtils.FORMAT_TYPE_1)));
            holder.tvContent.setText(data.get(position).getTitle());
            holder.tvClassify.setText(context.getString(R.string.collect_sort,data.get(position).getChapterName()));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIhelper.openWebView(context,data.get(position).getLink());
                }
            });
        }
    }


    class CollectionViewHolder extends RecyclerView.ViewHolder {

        TextView tvAuthor;
        TextView tvTime;
        TextView tvContent;
        TextView tvClassify;

        public CollectionViewHolder(View itemView) {
            super(itemView);
            tvAuthor = itemView.findViewById(R.id.tv_author);
             tvTime = itemView.findViewById(R.id.tv_time);
             tvContent = itemView.findViewById(R.id.tv_content);
             tvClassify = itemView.findViewById(R.id.tv_classify);
        }
    }
}
