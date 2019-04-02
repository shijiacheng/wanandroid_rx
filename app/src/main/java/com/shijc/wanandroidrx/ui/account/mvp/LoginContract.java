package com.shijc.wanandroidrx.ui.account.mvp;

import com.shijc.wanandroidrx.common.mvp.IPresenter;
import com.shijc.wanandroidrx.common.mvp.IView;
import com.shijc.wanandroidrx.ui.account.bean.LoginReuslt;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.account.mvp
 * @Description:
 * @date 2019/4/1 下午 4:12
 */
public class LoginContract {
    public interface View extends IView {
        void onLoginResult(LoginReuslt result);
    }

    public interface Presenter extends IPresenter {
        void login(String username,String password);
    }
}
