package com.shijc.wanandroidrx.ui.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import com.shijc.wanandroidrx.common.mvp.BaseActivity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.flexbox.FlexboxLayout;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.shijc.wanandroidrx.R;
import com.shijc.wanandroidrx.ui.home.bean.ArticleModel;
import com.shijc.wanandroidrx.ui.search.adapter.SearchAdapter;
import com.shijc.wanandroidrx.ui.search.bean.SearchHotKeyResult;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;
import com.shijc.wanandroidrx.common.mvp.BaseActivity;
import com.shijc.wanandroidrx.ui.search.mvp.SearchContract;
import com.shijc.wanandroidrx.ui.search.mvp.SearchPresenter;
import com.shijc.wanandroidrx.utils.SoftKeyboardUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.search
 * @Description;
 * @date 2019/4/2 上午 8;14
 */
public class SearchActivity extends BaseActivity {


    private Toolbar toolbar; 
    private XRecyclerView recyclerView; 
    private FlexboxLayout flexContainer; 
    private LinearLayout llHotKey; 
    private LinearLayout llResult;

    private SearchPresenter presenter;
    private  SearchAdapter adapter;

    private  SearchView searchView;

    private int page;
    private String key;
    //    private var searchKeys = ArrayList<SystemTreeResult.Data>()
    private List<ArticleModel> mDatas = new ArrayList<ArticleModel>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        presenter = new SearchPresenter();
        presenter.attachView(mView);
        initView();
        presenter.getSearchHotKey();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView = (SearchView) item.getActionView();
        searchView.setSubmitButtonEnabled(true);

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                showHotKey();
                key = null;
                return false;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query) {
                key = query;
                showSearchResult();
                recyclerView.refresh();
                SoftKeyboardUtils.hide(SearchActivity.this);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }


        });
        return super.onCreateOptionsMenu(menu);
    }


    private void initView(){
        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        llHotKey = findViewById(R.id.ll_hotkey);
        llResult = findViewById(R.id.ll_result);
        flexContainer = findViewById(R.id.fbl_container);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SearchAdapter(this,mDatas);
        recyclerView.setAdapter(adapter);
        recyclerView.setLoadingListener(new  XRecyclerView.LoadingListener(){
            @Override
            public void onRefresh() {
                showSearchResult();
                page = 0;
                presenter.getData(page,key);
            }

            @Override
            public void onLoadMore() {
                page ++;
                presenter.getData(page,key);
            }


        });
        showHotKey();

    }

    private void showHotKey(){
        llHotKey.setVisibility(View.VISIBLE);
        llResult.setVisibility(View.GONE);
    }

    private void showSearchResult(){
        llHotKey.setVisibility(View.GONE);
        llResult.setVisibility(View.VISIBLE);
    }

    private SearchContract.View mView = new SearchContract.View(){
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

        @Override
        public void onGetDataResult(List<SearchHotKeyResult.Data> datas) {
            flexContainer.removeAllViews();
            for(final SearchHotKeyResult.Data item : datas){
                TextView textView = new TextView(SearchActivity.this);
                textView.setText(item.getName());
                textView.setBackgroundResource(R.drawable.bg_shape_rectangle_gray);
                textView.setPadding(40,15,40,15);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        key = item.getName();
                        searchView.setQuery(key,true);
                        searchView.setIconified(false);
                        SoftKeyboardUtils.hide(SearchActivity.this);
                    }
                });

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(10,10,10,10);
                textView.setLayoutParams(params);
                flexContainer.addView(textView);
            }
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
