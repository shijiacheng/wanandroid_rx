package com.shijc.wanandroidrx.ui.website.bean

data class WebsiteReuslt(
    val `data`: List<Data>,
    val errorCode: Int,
    val errorMsg: String
){
    data class Data(
        val desc: String,
        val icon: String,
        val id: Int,
        val link: String,
        val name: String,
        val order: Int,
        val userId: Int,
        val visible: Int
    )
}

