package com.shijc.wanandroidrx.ui.search.adapter;

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
import com.shijc.wanandroidrx.ui.home.bean.ArticleModel;
import com.shijc.wanandroidrx.ui.project.adapter.ProjectListAdapter;
import com.shijc.wanandroidrx.utils.TimeUtils;
import com.shijc.wanandroidrx.utils.UIhelper;

import java.util.List;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.search.adapter
 * @Description:
 * @date 2019/4/3 下午 1:25
 */
public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {


    private Context context;
    private List<ArticleModel> data;

    public ArticleAdapter(Context context, List<ArticleModel> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ArticleViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rv_article,parent,false));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, final int position) {
        final ArticleModel item = data.get(position);

        holder.tv_content.setText(item.getTitle());
        holder.tvClassify.setText(item.getSuperChapterName());
        holder.tvAuthor.setText(item.getAuthor());
        holder.tvTime.setText(TimeUtils.long2String(item.getPublishTime(),TimeUtils.FORMAT_TYPE_1));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIhelper.openWebView(context,item.getLink());
            }
        });
    }


    class ArticleViewHolder extends RecyclerView.ViewHolder {

        TextView tvAuthor;
        TextView tvTime;
        TextView tv_content;
        TextView tvClassify;

        public ArticleViewHolder(View itemView) {
            super(itemView);
            tvAuthor = itemView.findViewById(R.id.tv_author);
            tv_content = itemView.findViewById(R.id.tv_content);
            tvClassify = itemView.findViewById(R.id.tv_classify);
            tvTime = itemView.findViewById(R.id.tv_time);
        }
    }




}
