package com.shijc.wanandroidrx.ui.systemtree.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.flexbox.FlexboxLayout;
import com.shijc.wanandroidrx.R;
import com.shijc.wanandroidrx.ui.systemtree.bean.SystemTreeResult;
import com.shijc.wanandroidrx.utils.TimeUtils;

import java.util.List;

public class SystemTreeAdapter extends RecyclerView.Adapter<SystemTreeAdapter.SystemTreeViewHolder> {


    private Context context;
    private List<SystemTreeResult.Data> data;

    public SystemTreeAdapter(Context context, List<SystemTreeResult.Data> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public SystemTreeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SystemTreeViewHolder(LayoutInflater.from(context).inflate(R.layout.item_system_tree,parent,false));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onBindViewHolder(@NonNull SystemTreeViewHolder holder, final int position) {
        holder.tvTitle.setText(data.get(position).getName());
        holder.flexboxLayout.removeAllViews();
        for(SystemTreeResult.Children item : data.get(position).getChildren()){
            TextView textView = new TextView(context);
            textView.setText(item.getName());
            textView.setBackgroundResource(R.drawable.bg_shape_rectangle_gray);
            textView.setPadding(40,15,40,15);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(10,10,10,10);
            textView.setLayoutParams(params);
            holder.flexboxLayout.addView(textView);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.onItemClick(data.get(position),position,v);
                }
            }
        });
    }


    class SystemTreeViewHolder extends RecyclerView.ViewHolder {

        FlexboxLayout flexboxLayout;
        TextView tvTitle;

        public SystemTreeViewHolder(View itemView) {
            super(itemView);
            flexboxLayout = itemView.findViewById(R.id.fbl_container);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }
    }


    private ClickListener listener;

    public void setListener(ClickListener listener) {
        this.listener = listener;
    }

    public interface ClickListener{
        void onItemClick(SystemTreeResult.Data item,int position,View view );
    }
}
