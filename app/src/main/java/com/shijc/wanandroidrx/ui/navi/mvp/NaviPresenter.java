package com.shijc.wanandroidrx.ui.navi.mvp;

import com.shijc.wanandroidrx.api.ApiService;
import com.shijc.wanandroidrx.common.mvp.IView;
import com.shijc.wanandroidrx.http.ApiStore;
import com.shijc.wanandroidrx.ui.navi.bean.NaviResult;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.navi.mvp
 * @Description:
 * @date 2019/4/1 下午 5:01
 */
public class NaviPresenter implements NaviContract.Presenter{


    private NaviContract.View mView;

    @Override
    public void getData() {
        ApiStore.createApi(ApiService.class).getNaviList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NaviResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NaviResult naviResult) {
                        mView.onGetDataResult(naviResult.getData());
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
        mView = (NaviContract.View) view;
    }

    @Override
    public void detachView() {

    }
}
