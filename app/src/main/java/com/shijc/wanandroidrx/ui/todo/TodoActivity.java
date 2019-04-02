package com.shijc.wanandroidrx.ui.todo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.*;
import com.shijc.wanandroidrx.common.mvp.BaseActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.shijc.wanandroidrx.R;
import com.shijc.wanandroidrx.ui.todo.adapter.ChildViewBinder;
import com.shijc.wanandroidrx.ui.todo.adapter.GroupViewBinder;
import com.shijc.wanandroidrx.ui.todo.bean.ChildModel;
import com.shijc.wanandroidrx.ui.todo.bean.GroupModel;
import com.shijc.wanandroidrx.ui.todo.bean.TodoResult;;
import com.shijc.wanandroidrx.ui.todo.mvp.TodoContract;
import com.shijc.wanandroidrx.ui.todo.mvp.TodoPresenter;
import com.shijc.wanandroidrx.utils.CollectionUtils;
import com.shijc.wanandroidrx.utils.TimeUtils;
import com.shijc.wanandroidrx.utils.ViewAnimation;
import com.shijc.wanandroidrx.widget.treeview.TreeNode;
import com.shijc.wanandroidrx.widget.treeview.TreeViewAdapter;
import com.shijc.wanandroidrx.widget.TodoAddDialog;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import com.shijc.wanandroidrx.common.base.SimpleDividerItemDecoration;
import com.shijc.wanandroidrx.common.mvp.BaseActivity;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.todo
 * @Description:
 * @date 2019/4/2 上午 8:15
 */
public class TodoActivity extends BaseActivity {




    private List<TreeNode> list = new ArrayList<TreeNode>();

    private TreeViewAdapter adapter;

    private TodoPresenter presenter;
    private int type = 0;


    private LinearLayout lyt_expand;
    private ImageButton bt_toggle;
    private Button bt_hide;
    private Button bt_save;
    private TextView tv_title;
    private RadioGroup radio_group;
    private FloatingActionButton fab_add;
    private RecyclerView recycler_view;
    private Toolbar tool_bar;



    private void initView(){
        lyt_expand = findViewById(R.id.lyt_expand);
        bt_toggle = findViewById(R.id.bt_toggle);
        bt_hide = findViewById(R.id.bt_hide);
        bt_save = findViewById(R.id.bt_save);
        tv_title = findViewById(R.id.tv_title);
        radio_group = findViewById(R.id.radio_group);
        fab_add = findViewById(R.id.fab_add);
        recycler_view = findViewById(R.id.recycler_view);
        tool_bar = findViewById(R.id.tool_bar);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        presenter = new TodoPresenter();
        presenter.attachView(mView);

        initView();

        lyt_expand.setVisibility(View.GONE);
        bt_toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleSection(bt_toggle);
            }
        });
        bt_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleSection(bt_toggle);
            }
        });
        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleSection(bt_toggle);
                type = radio_group.getCheckedRadioButtonId()-1;
                presenter.getData(type);
                tv_title.setText(((AppCompatRadioButton)(radio_group.getChildAt(type))).getText().toString());
            }
        });


        setSupportActionBar(tool_bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用



        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogFullscreen();
            }
        });

        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TreeViewAdapter(list, Arrays.asList(
                new GroupViewBinder(), new ChildViewBinder()));

        //添加自定义分割线
        recycler_view.addItemDecoration(new SimpleDividerItemDecoration(this));

        adapter.setOnTreeNodeListener(new TreeViewAdapter.OnTreeNodeListener(){
            @Override
            public boolean onClick(TreeNode node, RecyclerView.ViewHolder holder) {
                if (node.isLeaf() && node.getContent() instanceof ChildModel) {
                    ChildModel item = (ChildModel) node.getContent();
                }
                return false;
            }

            @Override
            public void onToggle(boolean isExpand, RecyclerView.ViewHolder holder) {

            }



        });
        recycler_view.setAdapter(adapter);
        recycler_view.setNestedScrollingEnabled(false);

        presenter.getData(type);
    }

    private void toggleSection(View view ){
        boolean show = toggleArrow(view);
        if (show){
            ViewAnimation.expand(lyt_expand);
        }else{
            ViewAnimation.collapse(lyt_expand);
        }
    }

    private boolean toggleArrow(View view ){
        if (view.getRotation() == 0) {
            view.animate().setDuration(200).rotation(180f);
            return true;
        } else {
            view.animate().setDuration(200).rotation(0f);
            return false;
        }
    }



    private TodoContract.View mView = new TodoContract.View(){
        @Override
        public void error(int code, String msg) {

        }

        @Override
        public void failure(String msg) {

        }

        @Override
        public void onGetReuslt(TodoResult.Data datas) {
            GroupModel group = new GroupModel();
            group.setName("root");
            List<GroupModel> groupsTitles = new ArrayList<GroupModel>();
            GroupModel todoGroupTitle = new GroupModel();
            todoGroupTitle.setName("待办清单");
            List<GroupModel> groups = new ArrayList<GroupModel>();

            for (TodoResult.Done it : datas.getTodoList()) {
                GroupModel todoGroup = new GroupModel();
                todoGroup.setName(TimeUtils.long2String(it.getDate(),TimeUtils.FORMAT_TYPE_1));
                List<ChildModel> todoChildren = new ArrayList<ChildModel>();

                for (TodoResult.Todo item:it.getTodoList()){
                    ChildModel todoChild = new ChildModel();
                    todoChild.setName(item.getTitle());
                    todoChildren.add(todoChild);
                }
                todoGroup.setUsers(todoChildren);
                groups.add(todoGroup);
            }

            todoGroupTitle.setGroups(groups);

            GroupModel doneGroupTitle = new GroupModel();
            doneGroupTitle.setName("已完成清单");
            List<GroupModel> groupsDone = new ArrayList<GroupModel>();
            for (TodoResult.Done it : datas.getDoneList()) {
                GroupModel doneGroup = new GroupModel();
                doneGroup.setName(TimeUtils.long2String(it.getDate(),TimeUtils.FORMAT_TYPE_1));
                List<ChildModel> doneChildren = new ArrayList<ChildModel>();

                for (TodoResult.Todo item:it.getTodoList()){
                    ChildModel doneChild = new ChildModel();
                    doneChild.setName(item.getTitle());
                    doneChildren.add(doneChild);
                }


                doneGroup.setUsers(doneChildren);
                groupsDone.add(doneGroup);
            }

            doneGroupTitle.setGroups(groupsDone);

            groupsTitles.add(todoGroupTitle);
            groupsTitles.add(doneGroupTitle);
;
            group.setGroups(groupsTitles);
            setGroupList(group);
        }


    };


    private void setGroupList(GroupModel group) {
        list.clear();

        if (group != null) {

            buildTreeNode(group);

            if (group.isLeaf()) {
                int rootHeight = 1;
                for (TreeNode node : list) {
                    node.setRootHeight(rootHeight);
                }
            }

            adapter.refresh(list);
        }


    }

    private void buildTreeNode(GroupModel group) {
        if (!CollectionUtils.isEmpty(group.getUsers())) {
            List<ChildModel> users = group.getUsers();
            for (ChildModel user : users) {
                TreeNode node = new TreeNode(user);
                list.add(node);
            }
        }
        if (!group.isLeaf()) {
            for (GroupModel groupItem : group.getGroups()) {
                groupItem.setParent(group);
                TreeNode node = new TreeNode(groupItem);
                list.add(node);
                buildTreeNode(groupItem, node);
            }
        }

    }

    private void buildTreeNode(GroupModel group , TreeNode parent) {
        if (!CollectionUtils.isEmpty(group.getUsers())) {
            List<ChildModel> users = group.getUsers();
            for (ChildModel user : users) {
                TreeNode node = new TreeNode(user);
                parent.addChild(node);
            }
        }
        if (!group.isLeaf()) {
            for (GroupModel groupItem : group.getGroups()) {
                groupItem.setParent(group);
                TreeNode node = new TreeNode(groupItem);
                parent.addChild(node);
                buildTreeNode(groupItem, node);
            }
        }

    }


    private void showDialogFullscreen() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        TodoAddDialog newFragment = new TodoAddDialog();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.add(android.R.id.content, newFragment).addToBackStack(null).commit();
        newFragment.setOnCallbackResult(new TodoAddDialog.CallbackResult() {
            @Override
            public void sendResult(@NotNull Object obj) {

            }
        });
    }






    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
