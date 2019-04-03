package com.shijc.wanandroidrx.ui.collection;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.shijc.wanandroidrx.R;
import com.shijc.wanandroidrx.common.base.SimpleDividerItemDecoration;
import com.shijc.wanandroidrx.common.mvp.BaseActivity;
import com.shijc.wanandroidrx.ui.collection.adapter.CollectionAdapter;
import com.shijc.wanandroidrx.ui.collection.mvp.CollectionContract;
import com.shijc.wanandroidrx.ui.collection.mvp.CollectionPresenter;
import com.shijc.wanandroidrx.ui.home.bean.ArticleModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.collection
 * @Description: 我的收藏页面
 * @date 2019/4/2 上午 8:13
 */
public class CollectionActivity extends BaseActivity {

    private CollectionPresenter presenter;
    private CollectionAdapter adapter;
    private List<ArticleModel> mDatas = new ArrayList<ArticleModel>();
    private int page = 0;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        presenter = new CollectionPresenter();
        presenter.attachView(mView);

        initView();
        initData();
    }

    private void initView(){
        tool_bar = findViewById(R.id.tool_bar);
        recycler_view = findViewById(R.id.recycler_view);
        fab_add = findViewById(R.id.fab_add);
    }

    private Toolbar tool_bar;
    private XRecyclerView recycler_view;
    private FloatingActionButton fab_add;

    private void initData(){
        setSupportActionBar(tool_bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用

        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        recycler_view.addItemDecoration(new SimpleDividerItemDecoration(this));
        adapter = new CollectionAdapter(this,mDatas);
        recycler_view.setAdapter(adapter);
        recycler_view.setLoadingListener(new  XRecyclerView.LoadingListener(){
            @Override
            public void onRefresh() {
                page = 0;
                presenter.getData(page);
            }

            @Override
            public void onLoadMore() {
                page++;
                presenter.getData(page);
            }


        });

        recycler_view.setRefreshProgressStyle(ProgressStyle.BallPulse);
        recycler_view.setLoadingMoreProgressStyle(ProgressStyle.BallPulse);
        recycler_view.refresh();

        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
            }
        });
    }

    private CollectionContract.View mView = new CollectionContract.View(){


        @Override
        public void error(int code, String msg) {
            Toast.makeText(CollectionActivity.this,msg, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void failure(String msg) {
            Toast.makeText(CollectionActivity.this,msg, Toast.LENGTH_SHORT).show();
        }

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


    private void showCustomDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_collect_add);
        dialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        final EditText et_title = dialog.findViewById(R.id.et_title);
        final EditText et_author = dialog.findViewById(R.id.et_author);
        final EditText et_link = dialog.findViewById(R.id.et_link);


        (dialog.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        ((Button) dialog.findViewById(R.id.bt_save)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }
}
