package com.shijc.wanandroidrx.ui.search.mvp;

import com.shijc.wanandroidrx.common.mvp.IPresenter;
import com.shijc.wanandroidrx.common.mvp.IView;
import com.shijc.wanandroidrx.ui.home.bean.ArticleModel;
import com.shijc.wanandroidrx.ui.home.bean.BannerModel;
import com.shijc.wanandroidrx.ui.search.bean.SearchHotKeyResult;

import java.util.List;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.search.mvp
 * @Description:
 * @date 2019/4/1 下午 5:13
 */
public class SearchContract {
    public interface View extends IView {
        void onRefresh(List<ArticleModel> datas );
        void onLoad(List<ArticleModel> datas);
        void onGetDataResult(List<SearchHotKeyResult.Data> datas);
    }

    public interface Presenter extends IPresenter {
        void getData(int page,String key);
        void getSearchHotKey();
    }


}
