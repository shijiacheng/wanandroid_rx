package com.shijc.wanandroidrx.ui.project;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.shijc.wanandroidrx.R;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentManager;
import com.shijc.wanandroidrx.ui.project.bean.ProjectTitleResult;
import com.shijc.wanandroidrx.ui.project.mvp.ProjectContract;
import com.shijc.wanandroidrx.ui.project.mvp.ProjectPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.project
 * @Description:
 * @date 2019/4/2 上午 8:14
 */
public class ProjectFragment extends Fragment {


    private ViewPager viewPager;
    private TabLayout tabLayout;

    private MyPagerAdapter adapter;

    private ProjectPresenter presenter;

    private List<ProjectTitleResult.Data> titles = new ArrayList<ProjectTitleResult.Data>();
    private List<Fragment> fragments  = new ArrayList<Fragment>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project, container, false);
        presenter = new ProjectPresenter();
        presenter.attachView(mView);
        initView(view);
        presenter.getProjectTitle();
        return view;
    }


    private void initView(View view) {

        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.view_pager);
        adapter = new MyPagerAdapter(getChildFragmentManager(),fragments,titles);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }

    class MyPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments;
        private List<ProjectTitleResult.Data> titles;

        public MyPagerAdapter(FragmentManager fm ,
                List<Fragment> fragments,
                List<ProjectTitleResult.Data> titles){
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


    private ProjectContract.View mView = new ProjectContract.View(){

        @Override
        public void error(int code, String msg) {

        }

        @Override
        public void failure(String msg) {

        }

        @Override
        public void onGetProjectTitleResult(ProjectTitleResult data) {
            titles.clear();
            titles.addAll(data.getData());
            for (ProjectTitleResult.Data item : data.getData()){
                Fragment fragment = new ProjectListFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("projectId",item.getId());
                fragment.setArguments(bundle);
                fragments.add(fragment);
            }
            adapter.notifyDataSetChanged();
        }



    };
}
