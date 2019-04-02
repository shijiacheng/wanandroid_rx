package com.shijc.wanandroidrx.ui.systemtree.mvp;

import com.shijc.wanandroidrx.common.mvp.IPresenter;
import com.shijc.wanandroidrx.common.mvp.IView;
import com.shijc.wanandroidrx.ui.project.bean.ProjectTitleResult;
import com.shijc.wanandroidrx.ui.systemtree.bean.SystemTreeResult;

import java.util.List;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.systemtree.mvp
 * @Description:
 * @date 2019/4/1 下午 5:19
 */
public class SystemTreeContract {

    public interface View extends IView {
        void onGetDataResult(List<SystemTreeResult.Data> data);
    }

    public interface Presenter extends IPresenter {
        void getData();
    }

}
