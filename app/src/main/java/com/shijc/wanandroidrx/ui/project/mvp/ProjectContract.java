package com.shijc.wanandroidrx.ui.project.mvp;

import com.shijc.wanandroidrx.common.mvp.IPresenter;
import com.shijc.wanandroidrx.common.mvp.IView;
import com.shijc.wanandroidrx.ui.navi.bean.NaviResult;
import com.shijc.wanandroidrx.ui.project.bean.ProjectTitleResult;

import java.util.List;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.project.mvp
 * @Description:
 * @date 2019/4/1 下午 5:01
 */
public class ProjectContract {

    public interface View extends IView {
        void onGetProjectTitleResult(ProjectTitleResult data);
    }

    public interface Presenter extends IPresenter {
        void getProjectTitle();
    }

}
