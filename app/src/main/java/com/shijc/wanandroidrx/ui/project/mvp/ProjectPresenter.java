package com.shijc.wanandroidrx.ui.project.mvp;

import com.shijc.wanandroidrx.api.ApiService;
import com.shijc.wanandroidrx.common.mvp.IView;
import com.shijc.wanandroidrx.http.ApiStore;
import com.shijc.wanandroidrx.ui.project.bean.ProjectTitleResult;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.project.mvp
 * @Description:
 * @date 2019/4/1 下午 5:01
 */
public class ProjectPresenter implements ProjectContract.Presenter {
    private ProjectContract.View mView;

    @Override
    public void getProjectTitle() {
        ApiStore.createApi(ApiService.class).getProjectTree()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProjectTitleResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ProjectTitleResult projectTitleResult) {
                        mView.onGetProjectTitleResult(projectTitleResult);
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
        mView = (ProjectContract.View) view;
    }

    @Override
    public void detachView() {

    }
}
