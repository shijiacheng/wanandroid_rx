package com.shijc.wanandroidrx.ui.todo.adapter;

import android.view.View;
import android.widget.TextView;
import com.shijc.wanandroidrx.R;
import com.shijc.wanandroidrx.ui.todo.bean.ChildModel;
import com.shijc.wanandroidrx.widget.treeview.TreeNode;
import com.shijc.wanandroidrx.widget.treeview.TreeViewBinder;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.todo.adapter
 * @Description:
 * @date 2019/4/2 下午 6:29
 */
public class ChildViewBinder extends TreeViewBinder<ChildViewBinder.ChildViewHolder> {


    @Override
    public ChildViewHolder provideViewHolder(View itemView) {
        return new ChildViewHolder(itemView);
    }

    @Override
    public void bindView(ChildViewHolder holder, int position, TreeNode node) {
        ChildModel item = (ChildModel) node.getContent();
        holder.text.setText(item.getName());
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_todo_child;
    }

    static class ChildViewHolder extends TreeViewBinder.ViewHolder{
        TextView text;

        ChildViewHolder(View rootView) {
            super(rootView);
            text = rootView.findViewById(R.id.view_item_child_tv);
        }
    }
}
