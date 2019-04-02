package com.shijc.wanandroidrx.ui.navi.mvp;

import com.shijc.wanandroidrx.common.mvp.IPresenter;
import com.shijc.wanandroidrx.common.mvp.IView;
import com.shijc.wanandroidrx.ui.home.bean.ArticleModel;
import com.shijc.wanandroidrx.ui.home.bean.BannerModel;
import com.shijc.wanandroidrx.ui.navi.bean.NaviResult;

import java.util.List;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.navi.mvp
 * @Description:
 * @date 2019/4/1 下午 5:01
 */
public class NaviContract {

    public interface View extends IView {
        void onGetDataResult(List<NaviResult.Data> datas);
    }

    public interface Presenter extends IPresenter {
        void getData();
    }

}
