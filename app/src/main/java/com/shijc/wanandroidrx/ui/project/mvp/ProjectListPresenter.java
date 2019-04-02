package com.shijc.wanandroidrx.ui.project.mvp;

import com.shijc.wanandroidrx.api.ApiService;
import com.shijc.wanandroidrx.common.mvp.IView;
import com.shijc.wanandroidrx.http.ApiStore;
import com.shijc.wanandroidrx.ui.home.bean.ArticleResult;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.project.mvp
 * @Description:
 * @date 2019/4/1 下午 5:07
 */
public class ProjectListPresenter implements ProjectListContract.Presenter {


    private ProjectListContract.View mView;

    @Override
    public void getProject(final int page, int id) {
        ApiStore.createApi(ApiService.class).getProjectList(page, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArticleResult>() {
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

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void attachView(IView view) {
        mView = (ProjectListContract.View) view;
    }

    @Override
    public void detachView() {

    }
}
