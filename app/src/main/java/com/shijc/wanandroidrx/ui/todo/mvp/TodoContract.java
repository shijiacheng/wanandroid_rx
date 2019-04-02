package com.shijc.wanandroidrx.ui.todo.mvp;

import com.shijc.wanandroidrx.common.mvp.IPresenter;
import com.shijc.wanandroidrx.common.mvp.IView;
import com.shijc.wanandroidrx.ui.home.bean.ArticleModel;
import com.shijc.wanandroidrx.ui.home.bean.BannerModel;
import com.shijc.wanandroidrx.ui.todo.bean.TodoResult;

import java.util.List;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.todo.mvp
 * @Description:
 * @date 2019/4/2 上午 7:52
 */
public class TodoContract {
    public interface View extends IView {
        void onGetReuslt(TodoResult.Data datas);
    }

    public interface Presenter extends IPresenter {
        void getData(int type);
    }

}
