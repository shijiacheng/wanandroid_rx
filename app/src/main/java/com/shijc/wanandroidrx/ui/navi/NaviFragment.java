package com.shijc.wanandroidrx.ui.navi;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.shijc.wanandroidrx.R;
import com.shijc.wanandroidrx.common.base.SimpleDividerItemDecoration;
import com.shijc.wanandroidrx.ui.navi.adapter.NaviAdapter;
import com.shijc.wanandroidrx.ui.navi.bean.NaviResult;
import com.shijc.wanandroidrx.ui.navi.mvp.NaviContract;
import com.shijc.wanandroidrx.ui.navi.mvp.NaviPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.navi
 * @Description:
 * @date 2019/4/2 上午 8:18
 */
public class NaviFragment extends Fragment {
    private Context context;
    private XRecyclerView recyclerView;

    private NaviPresenter presenter;
    private NaviAdapter adapter;

    private List<NaviResult.Data> mDatas = new ArrayList<NaviResult.Data>();



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navi, container, false);
        context = getActivity();
        presenter = new NaviPresenter();
        presenter.attachView(mView);
        initView(view);
        return view;
    }



    private void initView(View view){

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(context));
        adapter = new NaviAdapter(context,mDatas);
        recyclerView.setAdapter(adapter);
        recyclerView.setLoadingMoreEnabled(false);
        recyclerView.setLoadingListener(new  XRecyclerView.LoadingListener(){
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

    private NaviContract.View mView = new NaviContract.View(){


        @Override
        public void error(int code, String msg) {

        }

        @Override
        public void failure(String msg) {

        }

        @Override
        public void onGetDataResult(List<NaviResult.Data> datas) {
            mDatas.clear();
            mDatas.addAll(datas);
            adapter.notifyDataSetChanged();
            recyclerView.refreshComplete();
        }


    };
}
