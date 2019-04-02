package com.shijc.wanandroidrx.ui.project;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.shijc.wanandroidrx.R;
import com.shijc.wanandroidrx.common.base.SimpleDividerItemDecoration;
import com.shijc.wanandroidrx.ui.home.bean.ArticleModel;
import com.shijc.wanandroidrx.ui.project.adapter.ProjectListAdapter;
import com.shijc.wanandroidrx.ui.project.mvp.ProjectListContract;
import com.shijc.wanandroidrx.ui.project.mvp.ProjectListPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.project
 * @Description:
 * @date 2019/4/2 上午 8:14
 */
public class ProjectListFragment extends Fragment {


    private Context context;
    private XRecyclerView recyclerView;

    private int projectId = 0;

    private ProjectListPresenter presenter;
    private ProjectListAdapter adapter;

    private int page= 0;

    private List<ArticleModel> mDatas = new ArrayList<ArticleModel>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project_list, container, false);
        context = getActivity();
        Bundle bundle = getArguments();
        if (bundle!=null){
            projectId = bundle.getInt("projectId");
        }
        presenter = new ProjectListPresenter();
        presenter.attachView(mView);
        initView(view);
        return view;
    }



    private void initView(View view){

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(context));
        adapter = new ProjectListAdapter(context,mDatas);
        recyclerView.setAdapter(adapter);
        recyclerView.setLoadingListener(new  XRecyclerView.LoadingListener(){
            @Override
            public void onRefresh() {
                page = 0;
                presenter.getProject(page,projectId);
            }

            @Override
            public void onLoadMore() {
                page = 0;
                presenter.getProject(page,projectId);
            }



        });
        recyclerView.refresh();


    }

    private ProjectListContract.View mView = new ProjectListContract.View(){
        @Override
        public void error(int code, String msg) {

        }

        @Override
        public void failure(String msg) {

        }

        @Override
        public void onRefresh(List<ArticleModel> datas) {
            recyclerView.refreshComplete();
            recyclerView.loadMoreComplete();
            mDatas.clear();
            mDatas.addAll(datas);
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onLoad(List<ArticleModel> datas) {
            recyclerView.refreshComplete();
            recyclerView.loadMoreComplete();
            mDatas.addAll(datas);
            adapter.notifyDataSetChanged();
        }



    };
}
