package com.shijc.wanandroidrx.api;

import com.shijc.wanandroidrx.ui.account.bean.LoginReuslt;
import com.shijc.wanandroidrx.ui.home.bean.ArticleResult;
import com.shijc.wanandroidrx.ui.home.bean.BannerModel;
import com.shijc.wanandroidrx.ui.navi.bean.NaviResult;
import com.shijc.wanandroidrx.ui.project.bean.ProjectTitleResult;
import com.shijc.wanandroidrx.ui.search.bean.SearchHotKeyResult;
import com.shijc.wanandroidrx.ui.systemtree.bean.SystemTreeResult;
import com.shijc.wanandroidrx.ui.todo.bean.TodoResult;
import com.shijc.wanandroidrx.ui.website.bean.WebsiteReuslt;
import com.shijc.wanandroidrx.ui.wxarticle.bean.WxArticleTitleResult;
import io.reactivex.Observable;
import retrofit2.http.*;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.api
 * @Description:
 * @date 2019/4/2 下午 1:24
 */
public interface ApiService {

    /**
     * Retrofit注解
     * 请求方法
     * 注解代码	请求格式
     * @GET GET请求
     * @POST POST请求
     * @DELETE DELETE请求
     * @HEAD HEAD请求
     * @OPTIONS OPTIONS请求
     * @PATCH PATCH请求
     * 请求参数
     * 注解代码	说明
     * @Headers 添加请求头
     * @Path 替换路径
     * @Query 替代参数值，通常是结合get请求的
     * @FormUrlEncoded 用表单数据提交
     * @Field 替换参数值，是结合post请求的
     *
     */

    /**
     * 首页文章列表
     */
    @GET("article/list/{page}/json")
    Observable<ArticleResult> getArticleList(@Path("page") int page);

    /**
     * 主页Banner
     */
    @GET("banner/json")
    Observable<BannerModel> getBanner();


    /**
     * 知识体系
     */
    @GET("tree/json")
    Observable<SystemTreeResult> getSystemTree();

    /**
     * 知识体系详情
     */
    @GET("article/list/{page}/json")
    Observable<ArticleResult> getSystemTreeContent(@Path("page") int page, @Query("cid") int id);


    /**
     * 公众号名称
     */
    @GET("wxarticle/chapters/json")
    Observable<WxArticleTitleResult> getWxTitles();

    /**
     * 公众号文章
     */
    @GET("wxarticle/list/{id}/{page}/json")
    Observable<ArticleResult> getWxArticleContent(@Path("id") int id, @Path("page") int page);


    /**
     * 导航列表数据
     */
    @GET("navi/json")
    Observable<NaviResult> getNaviList();


    /**
     * 项目分类
     */
    @GET("project/tree/json")
    Observable<ProjectTitleResult> getProjectTree();

    /**
     * 项目列表
     */
    @GET("project/list/{page}/json")
    Observable<ArticleResult> getProjectList(@Path("page") int page,@Query("cid") int id);


    /**
     * 搜索热词
     */
    @GET("hotkey/json")
    Observable<SearchHotKeyResult> getSearchHotWord();


    /**
     * 搜索结果
     */
    @FormUrlEncoded
    @POST("article/query/{page}/json")
    Observable<ArticleResult> search(@Path("page") int page, @Field("k") String key);


    /**
     * 用户登录
     */
    @FormUrlEncoded
    @POST("user/login")
    Observable<LoginReuslt> login(@Field("username") String username,@Field("password") String password);


    /**
     * 收藏列表
     */
    @GET("lg/collect/list/{page}/json")
    Observable<ArticleResult> getCollectList(@Path("page") int page);


    /**
     * 网站收藏
     */
    @GET("lg/collect/usertools/json")
    Observable<WebsiteReuslt> getWebsiteList();


    /**
     * todo列表数据
     */
    @GET("lg/todo/list/{type}/json")
    Observable<TodoResult> getTodoList(@Path("type") int type);

    /*
        // 用户注册
        const val USER_REGISTER = "http://www.wanandroid.com/user/register";

        // 我的收藏-取消收藏
        const val CANCEL_COLLECTION = "http://www.wanandroid.com/lg/uncollect/";

        // 我的收藏-新增收藏
        const val ADD_COLLECTION = "http://www.wanandroid.com/lg/collect/add/json";
        // 取消网站收藏
        const val CANCEL_WEBSITE_COLLECTION = "http://www.wanandroid.com/lg/collect/deletetool/json";
        // 新增网站收藏
        const val ADD_WEBSITE_COLLECTION = "http://www.wanandroid.com/lg/collect/addtool/json";
        // 编辑网站收藏
        const val EDIT_WEBSITE_COLLECTION = "http://www.wanandroid.com/lg/collect/updatetool/json";

        // 新增todo数据
        const val ADD_TODO = "http://www.wanandroid.com/lg/todo/add/json";
        // 更新todo数据
        const val UPDATE_TODO = "http://www.wanandroid.com/lg/todo/update/";
        // 删除todo数据
        const val DELETE_TODO = "http://www.wanandroid.com/lg/todo/delete/";
        // 仅更新todo完成状态
        const val DONE_TODO = "http://www.wanandroid.com/lg/todo/done/";
     */

}
