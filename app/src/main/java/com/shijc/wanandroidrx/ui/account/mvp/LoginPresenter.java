package com.shijc.wanandroidrx.ui.account.mvp;

import com.shijc.wanandroidrx.api.ApiService;
import com.shijc.wanandroidrx.common.mvp.IView;
import com.shijc.wanandroidrx.http.ApiStore;
import com.shijc.wanandroidrx.ui.account.bean.LoginReuslt;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.account.mvp
 * @Description:
 * @date 2019/4/1 下午 4:14
 */
public class LoginPresenter implements LoginContract.Presenter{
    private LoginContract.View mView = null;

    @Override
    public void login(String username, String password) {
        ApiStore.createApi(ApiService.class).login(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginReuslt>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginReuslt loginReuslt) {
                        mView.onLoginResult(loginReuslt);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void attachView(IView view) {
        mView = (LoginContract.View) view;
    }

    @Override
    public void detachView() {

    }
}
