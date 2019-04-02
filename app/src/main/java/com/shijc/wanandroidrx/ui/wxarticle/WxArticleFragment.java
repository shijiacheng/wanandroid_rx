package com.shijc.wanandroidrx.ui.wxarticle;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.shijc.wanandroidrx.R;
import com.shijc.wanandroidrx.ui.project.bean.ProjectTitleResult;
import com.shijc.wanandroidrx.ui.wxarticle.bean.WxArticleTitleResult;
import com.shijc.wanandroidrx.ui.wxarticle.mvp.WxArticleContract;
import com.shijc.wanandroidrx.ui.wxarticle.mvp.WxArticlePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.wxarticle
 * @Description:
 * @date 2019/4/2 上午 8:16
 */
public class WxArticleFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wx_article, container, false);
        presenter = new WxArticlePresenter();
        presenter.attachView(mView);
        initView(view);
        return view;
    }


    private ViewPager viewPager;
    private TabLayout tabLayout;
    private MyPagerAdapter adapter;

    private WxArticlePresenter presenter;

    private List<WxArticleTitleResult.Data> titles = new ArrayList<>();
    private List<Fragment> fragments  = new ArrayList<Fragment>();


    private void initView(View view) {
        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.view_pager);
        adapter = new MyPagerAdapter(getChildFragmentManager(),fragments,titles);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        presenter.getWxTitle();
    }

    class MyPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments;
        private List<WxArticleTitleResult.Data> titles;

        public MyPagerAdapter(FragmentManager fm ,
                              List<Fragment> fragments,
                              List<WxArticleTitleResult.Data> titles){
            super(fm);
            this.fragments =fragments;
            this.titles =titles;
        }


        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position).getName();
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }


    private WxArticleContract.View mView = new WxArticleContract.View(){
        @Override
        public void error(int code, String msg) {

        }

        @Override
        public void failure(String msg) {

        }

        @Override
        public void onGetWxTitleResult(WxArticleTitleResult data) {
            titles.clear();
            titles.addAll(data.getData());
            for (WxArticleTitleResult.Data item : data.getData()){
                WxArticleListFragment fragment = new WxArticleListFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("wxId",item.getId());
                fragment.setArguments(bundle);
                fragments.add(fragment);
            }
            adapter.notifyDataSetChanged();
        }

    };
}
