package com.shijc.wanandroidrx;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;
import com.shijc.wanandroidrx.api.ApiService;
import com.shijc.wanandroidrx.common.base.MessageEvent;
import com.shijc.wanandroidrx.common.base.Preference;
import com.shijc.wanandroidrx.common.mvp.BaseActivity;
import com.shijc.wanandroidrx.http.ApiStore;
import com.shijc.wanandroidrx.ui.account.LoginActivity;
import com.shijc.wanandroidrx.ui.account.bean.LoginResult;
import com.shijc.wanandroidrx.ui.collection.CollectionActivity;
import com.shijc.wanandroidrx.ui.home.HomeFragment;
import com.shijc.wanandroidrx.ui.navi.NaviFragment;
import com.shijc.wanandroidrx.ui.project.ProjectFragment;
import com.shijc.wanandroidrx.ui.search.SearchActivity;
import com.shijc.wanandroidrx.ui.systemtree.SystemTreeFragment;
import com.shijc.wanandroidrx.ui.todo.TodoActivity;
import com.shijc.wanandroidrx.ui.website.WebsiteActivity;
import com.shijc.wanandroidrx.ui.wxarticle.WxArticleFragment;
import com.shijc.wanandroidrx.utils.BottomNavigationViewHelper;
import com.shijc.wanandroidrx.utils.StringUtils;
import com.shijc.wanandroidrx.utils.Utils;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx
 * @Description:
 * @date 2019/4/1 下午 3:48
 */
public class MainActivity extends BaseActivity {

    private BottomNavigationView navigation;
    private Toolbar toolbar;
    private ViewGroup llUser;
    private ViewGroup ll_collection;
    private ViewGroup ll_website;
    private ViewGroup ll_todo;
    private TextView tvUser;
    private ImageView ivAvatar;
    private DrawerLayout drawerLayout;

    private Fragment homeFragment;
    private Fragment systemTreeFragment;
    private  Fragment wxArticleFragment;
    private  Fragment naviFragment;
    private  Fragment projectFragment;

    private String userNamePref;
    private String passWordPref;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        Utils.init(this);

        initView();
        initData();

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_search:
                        startActivity(new Intent(MainActivity.this, SearchActivity.class));
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);//指定Toolbar上的视图文件
        return true;
    }

    private void initView(){
        drawerLayout = findViewById(R.id.id_drawer_layout);
        llUser = findViewById(R.id.ll_user);
        tvUser = findViewById(R.id.tv_username);
        toolbar = findViewById(R.id.tool_bar);
        ivAvatar = findViewById(R.id.iv_avatar);
        navigation = findViewById(R.id.bottom_navigation);
        ll_collection = findViewById(R.id.ll_collection);
        ll_website = findViewById(R.id.ll_website);
        ll_todo = findViewById(R.id.ll_todo);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        homeFragment = new HomeFragment();
        transaction.replace(R.id.view_container, homeFragment);
        mContent = homeFragment;
        transaction.commit();

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);
    }


    private void initData(){
        userNamePref = (String) Preference.getData("userName","");
        passWordPref = (String) Preference.getData("passWord","");
        if (!StringUtils.isEmpty(userNamePref)){
            tvUser.setText(userNamePref);
            autoLogin(userNamePref,passWordPref);
        }else{
            llUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
                }
            });
        }
        ll_collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CollectionActivity.class));
            }
        });
        ll_website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,WebsiteActivity.class));
            }
        });
        ll_todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,TodoActivity.class));
            }
        });
    }

    private Fragment mContent;

    private void switchContent(Fragment to) {
        if (mContent != to) {
            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction();
            if (!to.isAdded()) { // 先判断是否被add过
                transaction.hide(mContent).add(R.id.view_container, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(mContent).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
            mContent = to;
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener(){
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.bottom_navigation_home:
                    toolbar.setVisibility( View.VISIBLE);
                    toolbar.setTitle("首页");
                    if (homeFragment == null) {
                        homeFragment = new HomeFragment();
                    }
                    switchContent(homeFragment);
                    break;

                case R.id.bottom_navigation_systemtree:
                    toolbar.setVisibility( View.VISIBLE);
                    toolbar.setTitle("体系");
                    if (systemTreeFragment == null) {
                        systemTreeFragment = new SystemTreeFragment();
                    }
                    switchContent(systemTreeFragment);
                    break;
                case R.id.bottom_navigation_wxarticle:
                    toolbar.setVisibility( View.GONE);
                    toolbar.setTitle( "公众号");
                    if (wxArticleFragment == null) {
                        wxArticleFragment = new WxArticleFragment();
                    }
                    switchContent(wxArticleFragment);
                    break;

                case R.id.bottom_navigation_navi:
                        toolbar.setVisibility( View.VISIBLE);
                    toolbar.setTitle("导航");
                    if (naviFragment == null) {
                        naviFragment = new NaviFragment();
                    }
                    switchContent(naviFragment);
                        break;

                    case R.id.bottom_navigation_project:
                            toolbar.setVisibility( View.GONE);
                    toolbar.setTitle("项目");
                    if (projectFragment == null) {
                        projectFragment = new ProjectFragment();
                    }
                    switchContent(projectFragment);
                            break;
            }
            return true;
        }
    };


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(Intent.ACTION_MAIN);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addCategory(Intent.CATEGORY_HOME);
        startActivity(i);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        switch (event.getMsgWhat()){
            case 1:
                tvUser.setText( event.getObj().toString());
                 break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void autoLogin(final String username , String password) {
        ApiStore.createApi(ApiService.class).login(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginResult loginReuslt) {
                        EventBus.getDefault().post(new MessageEvent(1,username));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
