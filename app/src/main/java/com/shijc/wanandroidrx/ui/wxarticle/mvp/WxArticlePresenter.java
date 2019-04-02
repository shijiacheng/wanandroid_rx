package com.shijc.wanandroidrx.ui.wxarticle.mvp;

import com.shijc.wanandroidrx.api.ApiService;
import com.shijc.wanandroidrx.common.mvp.IView;
import com.shijc.wanandroidrx.http.ApiStore;
import com.shijc.wanandroidrx.ui.wxarticle.bean.WxArticleTitleResult;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.wxarticle.mvp
 * @Description:
 * @date 2019/4/2 上午 8:00
 */
public class WxArticlePresenter implements WxArticleContract.Presenter {



    private WxArticleContract.View mView;

    @Override
    public void getWxTitle() {
        ApiStore.createApi(ApiService.class).getWxTitles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WxArticleTitleResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WxArticleTitleResult result) {
                        mView.onGetWxTitleResult(result);
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
        mView = (WxArticleContract.View) view;
    }

    @Override
    public void detachView() {

    }
}
