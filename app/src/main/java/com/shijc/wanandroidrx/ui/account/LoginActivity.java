package com.shijc.wanandroidrx.ui.account;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.shijc.wanandroidrx.R;
import com.shijc.wanandroidrx.common.base.MessageEvent;
import com.shijc.wanandroidrx.common.base.Preference;
import com.shijc.wanandroidrx.common.mvp.BaseActivity;
import com.shijc.wanandroidrx.ui.account.bean.LoginReuslt;
import com.shijc.wanandroidrx.ui.account.mvp.LoginContract;
import com.shijc.wanandroidrx.ui.account.mvp.LoginPresenter;
import org.greenrobot.eventbus.EventBus;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.account
 * @Description:
 * @date 2019/4/1 下午 4:20
 */
public class LoginActivity extends BaseActivity {

    private LoginPresenter mPresenter ;

    private String userNamePref;
    private String passWordPref;

    private Toolbar tool_bar;
    private Button btn_login;
    private TextInputEditText tie_username;
    private TextInputEditText tie_password;

    private void initView(){
        tool_bar = findViewById(R.id.tool_bar);
        btn_login = findViewById(R.id.btn_login);
        tie_username = findViewById(R.id.tie_username);
        tie_password = findViewById(R.id.tie_password);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

        mPresenter = new LoginPresenter();
        mPresenter.attachView(mView);

        setSupportActionBar(tool_bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用


        userNamePref = (String) Preference.getData("userName","");
        passWordPref = (String) Preference.getData("passWord","");

        initListener();
    }


    private void initListener() {

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = tie_username.getText().toString();
                String password = tie_password.getText().toString();
                mPresenter.login(username,password);
            }
        });
    }

    private LoginContract.View mView = new LoginContract.View(){


        @Override
        public void error(int code, String msg) {

        }

        @Override
        public void failure(String msg) {

        }

        @Override
        public void onLoginResult(LoginReuslt result) {
            if (result.getErrorCode() == 0){
                userNamePref = result.getData().getUsername();
                Preference.putData("userName",userNamePref);
                Preference.putData("passWord",tie_password.getText().toString());
                EventBus.getDefault().post(new MessageEvent(1,userNamePref));
                finish();
            }else{

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
