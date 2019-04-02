package com.shijc.wanandroidrx.ui.todo.bean;

import com.shijc.wanandroidrx.R;
import com.shijc.wanandroidrx.widget.treeview.LayoutItemType;

import java.io.Serializable;

public class ChildModel implements Serializable, LayoutItemType {
    private String id;
    private String name;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_todo_child;
    }

}
