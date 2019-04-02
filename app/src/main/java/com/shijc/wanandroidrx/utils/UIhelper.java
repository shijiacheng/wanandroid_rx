package com.shijc.wanandroidrx.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.shijc.wanandroidrx.ui.WebviewActivity;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.utils
 * @Description:
 * @date 2019/4/2 上午 8:37
 */
public class UIhelper {

    public static void openWebView(Context context , String url){
        Intent intent = new Intent(context, WebviewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("url",url);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
