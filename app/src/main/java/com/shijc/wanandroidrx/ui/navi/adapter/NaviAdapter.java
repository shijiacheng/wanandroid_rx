package com.shijc.wanandroidrx.ui.navi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.google.android.flexbox.FlexboxLayout;
import com.shijc.wanandroidrx.R;
import com.shijc.wanandroidrx.ui.home.bean.ArticleModel;
import com.shijc.wanandroidrx.ui.navi.bean.NaviResult;
import com.shijc.wanandroidrx.ui.project.adapter.ProjectListAdapter;
import com.shijc.wanandroidrx.utils.TimeUtils;
import com.shijc.wanandroidrx.utils.UIhelper;

import java.util.List;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.navi.adapter
 * @Description:
 * @date 2019/4/3 上午 9:33
 */
public class NaviAdapter extends RecyclerView.Adapter<NaviAdapter.NaviViewHolder> {

    private Context context;
    private List<NaviResult.Data> data;

    public NaviAdapter(Context context, List<NaviResult.Data> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public NaviViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NaviViewHolder(LayoutInflater.from(context).inflate(R.layout.item_navi,parent,false));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onBindViewHolder(@NonNull NaviViewHolder holder, final int position) {

        holder.tvTitle.setText( data.get(position).getName());
        holder.flexboxLayout.removeAllViews();
        for(final NaviResult.Data.Article item : data.get(position).getArticles()){
            TextView textView = new TextView(context);
            textView.setText(item.getTitle());
            textView.setBackgroundResource(R.drawable.bg_shape_rectangle_gray);
            textView.setPadding(40,15,40,15);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(10,10,10,10);
            textView.setLayoutParams(params);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UIhelper.openWebView(context,item.getLink());
                }
            });
            holder.flexboxLayout.addView(textView);
        }
    }


    class NaviViewHolder extends RecyclerView.ViewHolder {

        FlexboxLayout flexboxLayout;
        TextView tvTitle;

        public NaviViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            flexboxLayout = itemView.findViewById(R.id.fbl_container);
        }
    }



}
