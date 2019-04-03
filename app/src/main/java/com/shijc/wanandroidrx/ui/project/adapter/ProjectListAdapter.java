package com.shijc.wanandroidrx.ui.project.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.shijc.wanandroidrx.R;
import com.shijc.wanandroidrx.ui.collection.adapter.CollectionAdapter;
import com.shijc.wanandroidrx.ui.home.bean.ArticleModel;
import com.shijc.wanandroidrx.utils.TimeUtils;
import com.shijc.wanandroidrx.utils.UIhelper;

import java.util.List;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.project.adapter
 * @Description:
 * @date 2019/4/3 上午 8:41
 */
public class ProjectListAdapter extends RecyclerView.Adapter<ProjectListAdapter.ProjectListViewHolder> {

    private Context context;
    private List<ArticleModel> data;

    public ProjectListAdapter(Context context, List<ArticleModel> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ProjectListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProjectListViewHolder(LayoutInflater.from(context).inflate(R.layout.item_project_list,parent,false));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectListViewHolder holder, final int position) {
        if (holder instanceof ProjectListViewHolder) {
            final ArticleModel item = data.get(position);

            Glide.with(context).load(item.getEnvelopePic()).asBitmap().into(holder.ivCover);
            holder.tvTitle.setText(item.getTitle());
            holder.tvDesc.setText(item.getDesc());
            holder.tvAuthor.setText(item.getAuthor());
            holder.tvTime.setText(TimeUtils.long2String(item.getPublishTime(),TimeUtils.FORMAT_TYPE_1));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIhelper.openWebView(context,item.getLink());
                }
            });
        }
    }


    class ProjectListViewHolder extends RecyclerView.ViewHolder {

        ImageView ivCover;
        TextView tvAuthor;
        TextView tvTime;
        TextView tvTitle;
        TextView tvDesc;

        public ProjectListViewHolder(View itemView) {
            super(itemView);
             ivCover = itemView.findViewById(R.id.iv_cover);
             tvTitle = itemView.findViewById(R.id.tv_title);
             tvDesc = itemView.findViewById(R.id.tv_desc);
             tvAuthor = itemView.findViewById(R.id.tv_author);
             tvTime = itemView.findViewById(R.id.tv_time);
        }
    }


}
