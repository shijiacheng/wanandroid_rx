package com.shijc.wanandroidrx.widget.treeview;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class TreeNode<T extends LayoutItemType> implements Cloneable {
    private T content;
    private TreeNode parent;
    private List<TreeNode> childList;
    private boolean isExpand;
    //the tree high
    private int height = UNDEFINE;
    private int rootHeight = 0;
    private boolean selected = false;

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getRelativeHeight() {
        return parent.getHeight() + 1;
    }

    public void setRootHeight(int rootHeight) {
        this.rootHeight = rootHeight;
    }

    private static final int UNDEFINE = -1;

    public TreeNode(@NonNull T content) {
        this.content = content;
    }

    public int getHeight() {
        if (isRoot())
            height = rootHeight;
        else if (height == UNDEFINE)
            height = parent.getHeight() + 1;
        return height;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public boolean isLeaf() {
        return childList == null || childList.isEmpty();
    }

    public void setContent(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    public List<TreeNode> getChildList() {
        return childList;
    }

    public void setChildList(List<TreeNode> childList) {
        this.childList = childList;
    }

    public TreeNode addChild(TreeNode node) {
        if (childList == null)
            childList = new ArrayList<>();
        childList.add(node);
        node.parent = this;
        return this;
    }

    public boolean toggle() {
        isExpand = !isExpand;
        return isExpand;
    }

    public void collapse() {
        if (!isExpand)
            isExpand = false;
    }

    public void expand() {
        if (isExpand)
            isExpand = true;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public TreeNode getParent() {
        return parent;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "content=" + this.content +
                ", parent=" + (parent == null ? "null" : parent.getContent().toString()) +
                ", childList=" + (childList == null ? "null" : childList.toString()) +
                ", isExpand=" + isExpand +
                '}';
    }

    @Override
    protected TreeNode<T> clone() throws CloneNotSupportedException {
        TreeNode<T> clone = new TreeNode<>(this.content);
        clone.isExpand = this.isExpand;
        return clone;
    }
}
