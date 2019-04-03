package com.shijc.wanandroidrx.ui.home.mvp;

import com.shijc.wanandroidrx.common.mvp.IPresenter;
import com.shijc.wanandroidrx.common.mvp.IView;
import com.shijc.wanandroidrx.ui.home.bean.ArticleModel;
import com.shijc.wanandroidrx.ui.home.bean.BannerModel;

import java.util.List;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.home.mvp
 * @Description:
 * @date 2019/4/1 下午 4:54
 */
public class HomeContract {
    public interface View extends IView {
        void onRefresh(List<ArticleModel> datas );
        void onLoad(List<ArticleModel> datas);
        void onGetBannerResult(BannerModel data);
    }

    public interface Presenter extends IPresenter {
        void refresh(int page);
        void load(int page);
    }

}
