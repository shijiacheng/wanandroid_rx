package com.shijc.wanandroidrx.ui.collection.mvp;

import com.shijc.wanandroidrx.common.mvp.IPresenter;
import com.shijc.wanandroidrx.common.mvp.IView;
import com.shijc.wanandroidrx.ui.home.bean.ArticleModel;

import java.util.List;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.collection.mvp
 * @Description:
 * @date 2019/4/1 下午 4:48
 */
public class CollectionContract {

    public interface View extends IView {
        void onRefresh(List<ArticleModel> datas);
        void onLoad(List<ArticleModel> datas);
    }

    public interface Presenter extends IPresenter {
        void getData(int page);
    }

}
