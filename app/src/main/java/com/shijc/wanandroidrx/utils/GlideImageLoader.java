package com.shijc.wanandroidrx.utils;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidkotlin.utils
 * @Description:
 * @date 2019/2/13 下午 5:07
 */
public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
        Glide.with(context.getApplicationContext())
                .load(path)
                .into(imageView);
    }

}