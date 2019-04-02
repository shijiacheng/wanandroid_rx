package com.shijc.wanandroidrx.ui.systemtree.mvp;

import com.shijc.wanandroidrx.api.ApiService;
import com.shijc.wanandroidrx.common.mvp.IView;
import com.shijc.wanandroidrx.http.ApiStore;
import com.shijc.wanandroidrx.ui.systemtree.bean.SystemTreeResult;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.systemtree.mvp
 * @Description:
 * @date 2019/4/1 下午 5:19
 */
public class SystemTreePresenter implements SystemTreeContract.Presenter  {
    @Override
    public void getData() {
        ApiStore.createApi(ApiService.class).getSystemTree()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SystemTreeResult>(){
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SystemTreeResult systemTreeResult) {
                        mView.onGetDataResult(systemTreeResult.getData());
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
        mView = (SystemTreeContract.View) view;
    }

    @Override
    public void detachView() {

    }



    private SystemTreeContract.View mView;

}
