package com.shijc.wanandroidrx.ui.account.mvp;

import com.shijc.wanandroidrx.common.mvp.IPresenter;
import com.shijc.wanandroidrx.common.mvp.IView;
import com.shijc.wanandroidrx.ui.account.bean.LoginResult;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.account.mvp
 * @Description:
 * @date 2019/4/1 下午 4:14
 */
public class RegisterContract {

    interface View extends IView {
        void onRegisterResult(LoginResult result);
    }

    interface Presenter extends IPresenter {
        void register(String username,String password);
    }
}
