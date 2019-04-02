package com.shijc.wanandroidrx.ui.website.mvp;

import com.shijc.wanandroidrx.common.mvp.IPresenter;
import com.shijc.wanandroidrx.common.mvp.IView;
import com.shijc.wanandroidrx.ui.todo.bean.TodoResult;
import com.shijc.wanandroidrx.ui.website.bean.WebsiteReuslt;

import java.util.List;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.website.mvp
 * @Description:
 * @date 2019/4/2 上午 7:56
 */
public class WebsiteContract {

    public interface View extends IView {
        void onGetReuslt(List<WebsiteReuslt.Data> datas);
    }

    public interface Presenter extends IPresenter {
        void getData();
    }

}
