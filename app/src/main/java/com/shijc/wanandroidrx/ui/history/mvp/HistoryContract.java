package com.shijc.wanandroidrx.ui.history.mvp;

import com.shijc.wanandroidrx.common.mvp.IPresenter;
import com.shijc.wanandroidrx.common.mvp.IView;
import com.shijc.wanandroidrx.ui.home.bean.ArticleModel;

import java.util.List;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.history.mvp
 * @Description:
 * @date 2019/4/2 上午 8:00
 */
public class HistoryContract {

    public interface View extends IView {
        void onRefresh(List<ArticleModel> datas);
        void onLoad(List<ArticleModel> datas);
    }

    public interface Presenter extends IPresenter {
        void getHistory(int page);
    }

}
