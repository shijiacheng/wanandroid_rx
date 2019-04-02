package com.shijc.wanandroidrx.ui.search.bean

/**
 * @Package com.shijc.wanandroidkotlin.ui.search.bean
 * @Description:
 * @author shijiacheng
 * @date 2019/2/17 上午 10:14
 * @version V1.0
 */
data class SearchHotKeyResult(
    val `data`: List<Data>,
    val errorCode: Int,
    val errorMsg: String
){
    data class Data(
        val id: Int,
        val link: String,
        val name: String,
        val order: Int,
        val visible: Int
    )
}

