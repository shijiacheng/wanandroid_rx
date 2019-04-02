package com.shijc.wanandroidrx.ui.systemtree;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.shijc.wanandroidrx.R;
import com.shijc.wanandroidrx.common.base.SimpleDividerItemDecoration;
import com.shijc.wanandroidrx.ui.systemtree.adapter.SystemTreeAdapter;
import com.shijc.wanandroidrx.ui.systemtree.bean.SystemTreeResult;
import com.shijc.wanandroidrx.ui.systemtree.bean.SystemTreeTitleModel;
import com.shijc.wanandroidrx.ui.systemtree.mvp.SystemTreeContract;
import com.shijc.wanandroidrx.ui.systemtree.mvp.SystemTreePresenter;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.systemtree
 * @Description;
 * @date 2019/4/2 上午 8;15
 */
public class SystemTreeFragment extends Fragment {
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_system_tree, container, false);
        context = getActivity();
        presenter = new SystemTreePresenter();
        presenter.attachView(mView);
        initView(view);
        return view;
    }


    private XRecyclerView recyclerView; 

    private SystemTreePresenter presenter;
    private SystemTreeAdapter adapter;

    private List<SystemTreeResult.Data> datas = new ArrayList<SystemTreeResult.Data>();


    private void initView(View view){


        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(context));
        adapter = new SystemTreeAdapter(context,datas);
        recyclerView.setAdapter(adapter);
        adapter.setListener(new SystemTreeAdapter.ClickListener() {
            @Override
            public void onItemClick(@NotNull SystemTreeResult.Data item, int position, @NotNull View view) {
                Intent intent = new Intent(context,SystemTreeListActivity.class);
                Bundle bundle = new Bundle();
                List<SystemTreeTitleModel> list = new ArrayList<>();
                for (SystemTreeResult.Children i : item.getChildren()){
                    list.add(new SystemTreeTitleModel(i.getId(),i.getName()));
                }
                bundle.putSerializable("treeData", (Serializable) list);
                bundle.putString("title",item.getName());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


        recyclerView.setLoadingMoreEnabled(false);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener(){
            @Override
            public void onRefresh() {
                presenter.getData();
            }

            @Override
            public void onLoadMore() {

            }

        });
        recyclerView.refresh();

    }

    private SystemTreeContract.View mView = new SystemTreeContract.View(){
        @Override
        public void error(int code, String msg) {

        }

        @Override
        public void failure(String msg) {

        }

        @Override
        public void onGetDataResult(List<SystemTreeResult.Data> data) {
            datas.clear();
            datas.addAll(data);
            adapter.notifyDataSetChanged();
            recyclerView.refreshComplete();
        }



    };
}
