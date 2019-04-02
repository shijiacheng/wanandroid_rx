package com.shijc.wanandroidrx.common.mvp;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.common.mvp
 * @Description:
 * @date 2019/4/1 下午 3:46
 */
public interface IView {
    void error(int code,String msg);
    void failure(String msg);
}
