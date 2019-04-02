package com.shijc.wanandroidrx.ui.website;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import com.google.android.flexbox.FlexboxLayout;
import com.shijc.wanandroidrx.common.mvp.BaseActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.shijc.wanandroidrx.R;
import com.shijc.wanandroidrx.common.mvp.BaseActivity;
import com.shijc.wanandroidrx.ui.website.bean.WebsiteReuslt;
import com.shijc.wanandroidrx.ui.website.mvp.WebsiteContract;
import com.shijc.wanandroidrx.ui.website.mvp.WebsitePresenter;
import com.shijc.wanandroidrx.utils.UIhelper;

import java.util.List;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.website
 * @Description:
 * @date 2019/4/2 上午 8:15
 */
public class WebsiteActivity extends BaseActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website);

        presenter = new WebsitePresenter();
        presenter.attachView(mView);

        initView();
        initData();
    }



    private WebsitePresenter presenter;
    private Toolbar tool_bar;
    private FlexboxLayout fbl_container;


    private void initView(){
        tool_bar = findViewById(R.id.tool_bar);
        fbl_container = findViewById(R.id.fbl_container);

        setSupportActionBar(tool_bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用

    }

    private void initData(){



        presenter.getData();
    }

    private WebsiteContract.View mView = new WebsiteContract.View(){
        @Override
        public void error(int code, String msg) {

        }

        @Override
        public void failure(String msg) {

        }

        @Override
        public void onGetReuslt(List<WebsiteReuslt.Data> datas) {
            fbl_container.removeAllViews();
            for (WebsiteReuslt.Data it : datas){
                TextView textView = new TextView(WebsiteActivity.this);
                textView.setText(it.getName());
                textView.setBackgroundResource(R.drawable.bg_shape_rectangle_gray);
                textView.setPadding(40,15,40,15);

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(20,10,20,10);
                textView.setLayoutParams(params);
                final String link = it.getLink();
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UIhelper.openWebView(WebsiteActivity.this,link);
                    }
                });
                fbl_container.addView(textView);
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
