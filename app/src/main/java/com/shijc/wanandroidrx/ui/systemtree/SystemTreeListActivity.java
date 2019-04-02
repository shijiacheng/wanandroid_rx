package com.shijc.wanandroidrx.ui.systemtree;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import com.shijc.wanandroidrx.common.mvp.BaseActivity;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import com.shijc.wanandroidrx.R;
import com.shijc.wanandroidrx.ui.systemtree.bean.SystemTreeTitleModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.systemtree
 * @Description:
 * @date 2019/4/2 上午 8:15
 */
public class SystemTreeListActivity extends BaseActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_tree_list);
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            title = bundle.getString("title");
            titles = (List<SystemTreeTitleModel>) bundle.getSerializable("treeData");
        }
        initView();
    }


    private ViewPager viewPager;
    private TabLayout tabLayout;

    private List<SystemTreeTitleModel> titles = new ArrayList<SystemTreeTitleModel>();
    private String title;

    private Toolbar tool_bar;

    private void initView() {
        tool_bar = findViewById(R.id.tool_bar);
        setSupportActionBar(tool_bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setTitle(title);

        List<Fragment> fragments = new ArrayList<Fragment>();

        for (SystemTreeTitleModel item : titles){
            Fragment fragment = new SystemTreeListFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("systemId",item.getId());
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(),fragments,titles);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }
    

    class MyPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments;
        private List<SystemTreeTitleModel> titles;

        public MyPagerAdapter(FragmentManager fm ,
                              List<Fragment> fragments,
                              List<SystemTreeTitleModel> titles){
            super(fm);
            this.fragments =fragments;
            this.titles =titles;
        }


        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position).getTitle();
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
