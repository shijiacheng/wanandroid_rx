package com.shijc.wanandroidrx.ui.home.bean

/**
 * @Package com.shijc.wanandroidkotlin.ui.home.bean
 * @Description:
 * @author shijiacheng
 * @date 2019/2/15 下午 2:13
 * @version V1.0
 */
data class BannerModel(
    val `data`: List<Data>,
    val errorCode: Int,
    val errorMsg: String
){
    data class Data(
        val desc: String,
        val id: Int,
        val imagePath: String,
        val isVisible: Int,
        val order: Int,
        val title: String,
        val type: Int,
        val url: String
    )
}