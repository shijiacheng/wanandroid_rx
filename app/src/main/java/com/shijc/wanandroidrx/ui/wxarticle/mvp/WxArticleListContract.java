package com.shijc.wanandroidrx.ui.wxarticle.mvp;

import com.shijc.wanandroidrx.common.mvp.IPresenter;
import com.shijc.wanandroidrx.common.mvp.IView;
import com.shijc.wanandroidrx.ui.home.bean.ArticleModel;
import com.shijc.wanandroidrx.ui.website.bean.WebsiteReuslt;

import java.util.List;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.wxarticle.mvp
 * @Description:
 * @date 2019/4/2 上午 8:00
 */
public class WxArticleListContract {

    public interface View extends IView {
        void onRefresh(List<ArticleModel> datas);
        void onLoad(List<ArticleModel> datas);
    }

    public interface Presenter extends IPresenter {
        void getWxArticle(int page,int id);
    }

}
