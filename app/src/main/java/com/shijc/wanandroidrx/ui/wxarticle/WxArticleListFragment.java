package com.shijc.wanandroidrx.ui.wxarticle;

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
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.shijc.wanandroidrx.R;
import com.shijc.wanandroidrx.ui.home.bean.ArticleModel;
import com.shijc.wanandroidrx.ui.wxarticle.adapter.WxArticleListAdapter;
import com.shijc.wanandroidrx.ui.wxarticle.mvp.WxArticleListContract;
import com.shijc.wanandroidrx.ui.wxarticle.mvp.WxArticleListPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.wxarticle
 * @Description;
 * @date 2019/4/2 上午 8;16
 */
public class WxArticleListFragment extends Fragment {
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wx_article_list, container, false);
        context = getActivity();
        Bundle bundle = getArguments();
        if (bundle!=null){
            wxId = bundle.getInt("wxId");
        }
        presenter = new WxArticleListPresenter();
        presenter.attachView(mView);
        initView(view);
        return view;
    }

    private XRecyclerView recyclerView;
    private int wxId = 0;

    private WxArticleListPresenter presenter;
    private WxArticleListAdapter adapter;

    private int page = 0;

    private List<ArticleModel> mDatas = new ArrayList<>();



    private void initView(View view){

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new WxArticleListAdapter(context,mDatas);
        recyclerView.setAdapter(adapter);
        recyclerView.setLoadingListener(new  XRecyclerView.LoadingListener(){
            @Override
            public void onRefresh() {
                page = 0;
                presenter.getWxArticle(page,wxId);
            }

            @Override
            public void onLoadMore() {
                page ++;
                presenter.getWxArticle(page,wxId);
            }



        });
        recyclerView.refresh();


    }

    private WxArticleListContract.View mView = new  WxArticleListContract.View(){
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
