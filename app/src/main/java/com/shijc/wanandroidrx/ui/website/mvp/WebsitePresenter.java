package com.shijc.wanandroidrx.ui.website.mvp;

import com.shijc.wanandroidrx.api.ApiService;
import com.shijc.wanandroidrx.common.mvp.IView;
import com.shijc.wanandroidrx.http.ApiStore;
import com.shijc.wanandroidrx.ui.website.bean.WebsiteReuslt;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.website.mvp
 * @Description:
 * @date 2019/4/2 上午 7:56
 */
public class WebsitePresenter implements WebsiteContract.Presenter {

    private WebsiteContract.View mView;

    @Override
    public void getData() {
        ApiStore.createApi(ApiService.class).getWebsiteList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WebsiteReuslt>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WebsiteReuslt websiteReuslt) {
                        mView.onGetReuslt(websiteReuslt.getData());
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
        mView = (WebsiteContract.View) view;
    }

    @Override
    public void detachView() {

    }


}
