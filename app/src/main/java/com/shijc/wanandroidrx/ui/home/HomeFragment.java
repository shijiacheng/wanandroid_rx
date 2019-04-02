package com.shijc.wanandroidrx.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.shijc.wanandroidrx.R;
import com.shijc.wanandroidrx.common.base.SimpleDividerItemDecoration;
import com.shijc.wanandroidrx.ui.home.adapter.HomeAdapter;
import com.shijc.wanandroidrx.ui.home.bean.ArticleModel;
import com.shijc.wanandroidrx.ui.home.bean.BannerModel;
import com.shijc.wanandroidrx.ui.home.mvp.HomeContract;
import com.shijc.wanandroidrx.ui.home.mvp.HomePresenter;
import com.shijc.wanandroidrx.utils.UIhelper;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.home
 * @Description:
 * @date 2019/4/2 上午 8:14
 */
public class HomeFragment extends Fragment {


    private XRecyclerView recyclerView;

    private HomePresenter presenter;
    private HomeAdapter adapter;

    private List<ArticleModel> mDatas = new ArrayList<ArticleModel>();

    private int page = 0;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        context = getActivity();
        presenter = new HomePresenter();
        presenter.attachView(mView);
        initView(view);
        return view;
    }

    private void initView(View view){

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(context));
        adapter = new HomeAdapter(context,mDatas);
        adapter.setListener(new HomeAdapter.ClickListener() {
            @Override
            public void onBannerClick(@NotNull BannerModel.Data item, int position) {
                UIhelper.openWebView(requireContext(),item.getUrl());
            }

            @Override
            public void onItemClick(@NotNull ArticleModel item, int position, @NotNull View view) {
                UIhelper.openWebView(requireContext(),item.getLink());
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener(){
            @Override
            public void onRefresh() {
                page = 0;
                presenter.refresh(page);
            }

            @Override
            public void onLoadMore() {
                page++;
                presenter.load(page);
            }


        });

        recyclerView.setRefreshProgressStyle(ProgressStyle.BallPulse);
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallPulse);
        recyclerView.refresh();


    }


    private HomeContract.View mView = new HomeContract.View(){


        @Override
        public void error(int code, String msg) {
            Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
        }

        @Override
        public void failure(String msg) {
            Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
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
        public void onGetBannerResult(BannerModel data) {
            adapter.setBanner(data.getData());
        }

    };
}
