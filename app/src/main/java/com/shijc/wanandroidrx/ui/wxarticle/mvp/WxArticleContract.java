package com.shijc.wanandroidrx.ui.wxarticle.mvp;

import com.shijc.wanandroidrx.common.mvp.IPresenter;
import com.shijc.wanandroidrx.common.mvp.IView;
import com.shijc.wanandroidrx.ui.systemtree.bean.SystemTreeResult;
import com.shijc.wanandroidrx.ui.wxarticle.bean.WxArticleTitleResult;

import java.util.List;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.wxarticle.mvp
 * @Description:
 * @date 2019/4/2 上午 8:00
 */
public class WxArticleContract {

    public interface View extends IView {
        void onGetWxTitleResult(WxArticleTitleResult data);
    }

    public interface Presenter extends IPresenter {
        void getWxTitle();
    }

}
