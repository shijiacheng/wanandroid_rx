package com.shijc.wanandroidrx.ui.todo.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.shijc.wanandroidrx.R;
import com.shijc.wanandroidrx.ui.todo.bean.GroupModel;
import com.shijc.wanandroidrx.widget.treeview.TreeNode;
import com.shijc.wanandroidrx.widget.treeview.TreeViewBinder;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.todo.adapter
 * @Description:
 * @date 2019/4/2 下午 6:32
 */
public class GroupViewBinder extends TreeViewBinder<GroupViewBinder.GroupViewHolder> {


    @Override
    public GroupViewHolder provideViewHolder(View itemView) {
        return new GroupViewHolder(itemView);
    }

    @Override
    public void bindView(GroupViewHolder holder, int position, TreeNode node) {
        GroupModel item = (GroupModel) node.getContent();
        holder.image.setRotation(node.isExpand()?90:0);
        holder.text.setText(item.getName());
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_todo_group;
    }


    static class GroupViewHolder extends TreeViewBinder.ViewHolder{
        TextView text;
        ImageView image;

        GroupViewHolder(View rootView) {
            super(rootView);
            text = rootView.findViewById(R.id.view_item_group_tv);
            image = rootView.findViewById(R.id.view_item_group_iv);
        }
    }
}
