package com.shijc.wanandroidrx.common.mvp;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.common.mvp
 * @Description:
 * @date 2019/4/1 下午 3:45
 */
public interface IPresenter {
    //在Activity创建时提供一个view对象（view即是Activity）
    void attachView(IView view);
    //在Activity销毁时清除view对象，防止内存泄露
    void detachView();
}
