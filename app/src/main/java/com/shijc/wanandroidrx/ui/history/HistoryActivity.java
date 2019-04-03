package com.shijc.wanandroidrx.ui.history;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.shijc.wanandroidrx.R;
import com.shijc.wanandroidrx.common.base.SimpleDividerItemDecoration;
import com.shijc.wanandroidrx.common.mvp.BaseActivity;
import com.shijc.wanandroidrx.ui.history.mvp.HistoryContract;
import com.shijc.wanandroidrx.ui.history.mvp.HistoryPresenter;
import com.shijc.wanandroidrx.ui.home.bean.ArticleModel;
import com.shijc.wanandroidrx.ui.search.adapter.ArticleAdapter;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends BaseActivity {

    private Toolbar tool_bar;
    private XRecyclerView recycler_view;

    private HistoryPresenter presenter;
    private ArticleAdapter adapter;
    private Context context;
    private int page = 0;

    private List<ArticleModel> mDatas = new ArrayList<ArticleModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        context = this;

        presenter = new HistoryPresenter();
        presenter.attachView(view);

        initView();
        initData();
    }

    private void initView(){
        recycler_view = findViewById(R.id.recycler_view);
        tool_bar = findViewById(R.id.tool_bar);
        setSupportActionBar(tool_bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setTitle("浏览历史");
    }

    private void initData(){
        recycler_view.setLayoutManager(new LinearLayoutManager(context));
        recycler_view.addItemDecoration(new SimpleDividerItemDecoration(context));
        adapter = new ArticleAdapter(context,mDatas);
        recycler_view.setAdapter(adapter);

        recycler_view.setLoadingListener(new XRecyclerView.LoadingListener(){
            @Override
            public void onRefresh() {
                page = 0;
                presenter.getHistory(page);
            }

            @Override
            public void onLoadMore() {
                page ++;
                presenter.getHistory(page);
            }


        });
        recycler_view.refresh();
    }


    private HistoryContract.View view = new HistoryContract.View() {
        @Override
        public void onRefresh(List<ArticleModel> datas) {
            recycler_view.refreshComplete();
            recycler_view.loadMoreComplete();
            mDatas.clear();
            mDatas.addAll(datas);
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onLoad(List<ArticleModel> datas) {
            recycler_view.refreshComplete();
            recycler_view.loadMoreComplete();
            mDatas.addAll(datas);
            adapter.notifyDataSetChanged();
        }

        @Override
        public void error(int code, String msg) {

        }

        @Override
        public void failure(String msg) {

        }
    };


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
