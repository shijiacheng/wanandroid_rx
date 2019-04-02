package com.shijc.wanandroidrx.ui.systemtree;

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
import com.shijc.wanandroidrx.common.base.SimpleDividerItemDecoration;
import com.shijc.wanandroidrx.ui.home.bean.ArticleModel;
import com.shijc.wanandroidrx.ui.systemtree.adapter.SystemTreeListAdapter;
import com.shijc.wanandroidrx.ui.systemtree.mvp.SystemTreeListContract;
import com.shijc.wanandroidrx.ui.systemtree.mvp.SystemTreeListPresenter;
import com.shijc.wanandroidrx.utils.UIhelper;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.systemtree
 * @Description;
 * @date 2019/4/2 上午 8;15
 */
public class SystemTreeListFragment extends Fragment {
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_system_tree_list, container, false);
        context = getActivity();
        Bundle bundle = getArguments();
        if (bundle!=null){
            systemId = bundle.getInt("systemId");
        }
        presenter = new SystemTreeListPresenter();
        presenter.attachView(mView);
        initView(view);
        return view;
    }


    private XRecyclerView recyclerView; 

    private int systemId  = 0;

    private SystemTreeListPresenter presenter;
    private SystemTreeListAdapter adapter;

    private int page = 0;

    private List<ArticleModel> mDatas = new ArrayList<ArticleModel>();


    private void initView(View view ){

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(context));
        adapter = new SystemTreeListAdapter(context,mDatas);
        recyclerView.setAdapter(adapter);

        adapter.setListener(new SystemTreeListAdapter.ClickListener() {
            @Override
            public void onItemClick(@NotNull ArticleModel item, int position, @NotNull View view) {
                UIhelper.openWebView(context,item.getLink());
            }
        });
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener(){
            @Override
            public void onRefresh() {
                page = 0;
                presenter.getData(page,systemId);
            }

            @Override
            public void onLoadMore() {
                page ++;
                presenter.getData(page,systemId);
            }


        });
        recyclerView.refresh();


    }

    private SystemTreeListContract.View mView = new  SystemTreeListContract.View(){
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
