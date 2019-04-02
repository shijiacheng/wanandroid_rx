package com.shijc.wanandroidrx.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidkotlin.utils
 * @Description:
 * @date 2019/2/17 下午 12:23
 */
public class SoftKeyboardUtils {
    /**
     * 关闭软键盘
     *
     * @param activity 当前Activity
     */
    public static void hide(Activity activity) {
        if (activity == null) return;
        View view = activity.getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * 强制显示软键盘
     *
     * @param activity 当前Activity
     */
    public static void show(Activity activity) {
        if (activity == null) return;
        View view = activity.getWindow().peekDecorView();

        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            boolean isOpen = inputmanger.isActive();//isOpen若返回true，则表示输入法打开
            if (!isOpen) {
                inputmanger.showSoftInput(view, InputMethodManager.SHOW_FORCED);
            }
        }
    }

    /**
     * 输入法在窗口上已经显示，则隐藏，反之则显示
     *
     * @param activity 当前Activity
     */
    public static void toggle(Activity activity) {
        if (activity == null) return;
        View view = activity.getWindow().peekDecorView();

        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
