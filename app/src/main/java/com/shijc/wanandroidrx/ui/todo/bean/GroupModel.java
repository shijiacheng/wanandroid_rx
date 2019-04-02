package com.shijc.wanandroidrx.ui.todo.bean;

import com.shijc.wanandroidrx.R;
import com.shijc.wanandroidrx.widget.treeview.LayoutItemType;

import java.io.Serializable;
import java.util.List;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package cn.com.lezhixing.xui.model
 * @Description:
 * @date 2019/1/28 下午 2:19
 */
public class GroupModel implements LayoutItemType, Serializable {
    private List<GroupModel> groups;
    private List<ChildModel> users;
    private String id;
    private String name;
    private GroupModel parent;

    public List<GroupModel> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupModel> groups) {
        this.groups = groups;
    }

    public List<ChildModel> getUsers() {
        return users;
    }

    public void setUsers(List<ChildModel> users) {
        this.users = users;
    }

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
        return R.layout.item_todo_group;
    }

    public boolean isLeaf(){
        return groups == null;
    }

    public GroupModel getParent() {
        return parent;
    }

    public void setParent(GroupModel parent) {
        this.parent = parent;
    }
}
