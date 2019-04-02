package com.shijc.wanandroidrx.ui.todo.mvp;

import com.shijc.wanandroidrx.api.ApiService;
import com.shijc.wanandroidrx.common.mvp.IView;
import com.shijc.wanandroidrx.http.ApiStore;
import com.shijc.wanandroidrx.ui.todo.bean.TodoResult;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.todo.mvp
 * @Description:
 * @date 2019/4/2 上午 7:52
 */
public class TodoPresenter implements TodoContract.Presenter {


    private TodoContract.View mView;

    @Override
    public void getData(int type) {
        ApiStore.createApi(ApiService.class).getTodoList(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TodoResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TodoResult todoResult) {
                        mView.onGetReuslt(todoResult.getData());
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
        mView = (TodoContract.View) view;
    }

    @Override
    public void detachView() {

    }
}
