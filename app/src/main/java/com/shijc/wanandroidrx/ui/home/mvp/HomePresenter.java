package com.shijc.wanandroidrx.ui.home.mvp;

import com.shijc.wanandroidrx.api.ApiService;
import com.shijc.wanandroidrx.common.mvp.IView;
import com.shijc.wanandroidrx.http.ApiStore;
import com.shijc.wanandroidrx.ui.home.bean.ArticleResult;
import com.shijc.wanandroidrx.ui.home.bean.BannerModel;
import com.shijc.wanandroidrx.ui.home.bean.HomeModel;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.home.mvp
 * @Description:
 * @date 2019/4/1 下午 4:54
 */
public class HomePresenter implements HomeContract.Presenter {


    private HomeContract.View mView;

    private Observable<ArticleResult> articleObservable(int page){
        Observable<ArticleResult> observable1 = ApiStore.createApi(ApiService.class)
                .getArticleList(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        return observable1;
    }

    private Observable<BannerModel> bannerObservable(){
        Observable<BannerModel> observable2 = ApiStore.createApi(ApiService.class)
                .getBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        return observable2;
    }

    @Override
    public void attachView(IView view) {
        mView = (HomeContract.View) view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void refresh(int page) {


        Observable.zip(articleObservable(page), bannerObservable(),
                new BiFunction<ArticleResult, BannerModel, HomeModel>() {
                    @Override
                    public HomeModel apply(ArticleResult articleResult, BannerModel bannerModel) throws Exception {
                        HomeModel homeModel = new HomeModel(articleResult, bannerModel);
                        return homeModel;
                    }
                }).subscribe(new Observer<HomeModel>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HomeModel homeModel) {
                mView.onRefresh(homeModel.getArticleResult().getData().getDatas());
                mView.onGetBannerResult(homeModel.getBannerModel());
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
    public void load(final int page) {
        ApiStore.createApi(ApiService.class)
                .getArticleList(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArticleResult>(){
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ArticleResult result) {
                        if (page == 0){
                            mView.onRefresh(result.getData().getDatas());
                        }else{
                            mView.onLoad(result.getData().getDatas());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.failure(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
