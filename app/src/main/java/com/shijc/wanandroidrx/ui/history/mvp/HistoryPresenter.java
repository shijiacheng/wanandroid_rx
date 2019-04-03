package com.shijc.wanandroidrx.ui.history.mvp;

import android.util.Log;
import com.shijc.wanandroidrx.common.mvp.IView;
import com.shijc.wanandroidrx.ui.home.bean.ArticleModel;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import org.litepal.LitePal;

import java.util.List;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.history.mvp
 * @Description:
 * @date 2019/4/3 下午 1:19
 */
public class HistoryPresenter implements HistoryContract.Presenter {

    private HistoryContract.View mView;
    private static int LIMIT = 15;

    @Override
    public void getHistory(final int page) {

        Observable.create(new ObservableOnSubscribe<List<ArticleModel>>() {
            @Override
            public void subscribe(ObservableEmitter<List<ArticleModel>> emitter) throws Exception {
                List<ArticleModel> newsList = LitePal
                        .limit(LIMIT).offset(page*LIMIT)
                        .find(ArticleModel.class);
                emitter.onNext(newsList);
            }
        }).subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<List<ArticleModel>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<ArticleModel> articleModels) {
                if (page == 0){
                    mView.onRefresh(articleModels);
                }else{
                    mView.onLoad(articleModels);
                }
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
        mView = (HistoryContract.View) view;
    }

    @Override
    public void detachView() {

    }
}
