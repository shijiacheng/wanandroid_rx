package com.shijc.wanandroidrx.ui.account;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.shijc.wanandroidrx.R;
import com.shijc.wanandroidrx.common.mvp.BaseActivity;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui
 * @Description:
 * @date 2019/4/1 下午 4:19
 */
public class RegisterActivity extends BaseActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
}
