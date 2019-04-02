package com.shijc.wanandroidrx.common.interceptor;

import android.content.Context;
import android.util.Log;
import com.shijc.wanandroidrx.AppContext;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashSet;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidkotlin.common.interceptor
 * @Description:
 * @date 2019/2/25 下午 2:13
 */
public class AddCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        HashSet<String> preferences = (HashSet) AppContext.getInstance().getSharedPreferences("config",
                Context.MODE_PRIVATE).getStringSet("cookie", null);
        if (preferences != null) {
            for (String cookie : preferences) {
                builder.addHeader("Cookie", cookie);
                Log.v("OkHttp", "Adding Header: " + cookie); // This is done so I know which headers are being added; this interceptor is used after the normal logging of OkHttp
            }
        }
        return chain.proceed(builder.build());
    }
}
