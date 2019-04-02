package com.shijc.wanandroidrx.ui.home.bean

/**
 * @Package com.shijc.wanandroidkotlin.ui.home.bean
 * @Description:
 * @author shijiacheng
 * @date 2019/2/15 下午 12:22
 * @version V1.0
 */
data class ArticleResult(
    val `data`: Data,
    val errorCode: Int,
    val errorMsg: String
){
    data  class Data(
        val curPage: Int,
        val datas: List<ArticleModel>,
        val offset: Int,
        val over: Boolean,
        val pageCount: Int,
        val size: Int,
        val total: Int
    )
}



