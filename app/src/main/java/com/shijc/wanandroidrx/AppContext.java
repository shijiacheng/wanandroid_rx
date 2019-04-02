package com.shijc.wanandroidrx;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import com.shijc.wanandroidrx.common.base.Preference;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx
 * @Description:
 * @date 2019/4/1 下午 4:37
 */
public class AppContext extends Application {

    private static AppContext instance;

    public static AppContext getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Preference.getInstance(this,"config");
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

}
