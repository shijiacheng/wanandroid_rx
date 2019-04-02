package com.shijc.wanandroidrx.ui.home.bean;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.home.bean
 * @Description:
 * @date 2019/4/2 下午 7:20
 */
public class HomeModel {
    private ArticleResult articleResult;
    private BannerModel bannerModel;

    public HomeModel(ArticleResult articleResult, BannerModel bannerModel) {
        this.articleResult = articleResult;
        this.bannerModel = bannerModel;
    }

    public ArticleResult getArticleResult() {
        return articleResult;
    }

    public void setArticleResult(ArticleResult articleResult) {
        this.articleResult = articleResult;
    }

    public BannerModel getBannerModel() {
        return bannerModel;
    }

    public void setBannerModel(BannerModel bannerModel) {
        this.bannerModel = bannerModel;
    }
}
