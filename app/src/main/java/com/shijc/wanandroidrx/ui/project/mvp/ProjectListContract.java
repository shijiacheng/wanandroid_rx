package com.shijc.wanandroidrx.ui.project.mvp;

import com.shijc.wanandroidrx.common.mvp.IPresenter;
import com.shijc.wanandroidrx.common.mvp.IView;
import com.shijc.wanandroidrx.ui.home.bean.ArticleModel;
import com.shijc.wanandroidrx.ui.home.bean.BannerModel;

import java.util.List;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.project.mvp
 * @Description:
 * @date 2019/4/1 下午 5:01
 */
public class ProjectListContract {

    public interface View extends IView {
        void onRefresh(List<ArticleModel> datas );
        void onLoad(List<ArticleModel> datas);
    }

    public interface Presenter extends IPresenter {
        void getProject(int page,int id);
    }


}
