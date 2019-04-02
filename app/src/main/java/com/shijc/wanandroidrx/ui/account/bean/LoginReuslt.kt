package com.shijc.wanandroidrx.ui.account.bean

/**
 * @Package com.shijc.wanandroidkotlin.ui.account
 * @Description:
 * @author shijiacheng
 * @date 2019/2/25 上午 10:20
 * @version V1.0
 */

data class LoginReuslt(
    val `data`: Data,
    val errorCode: Int,
    val errorMsg: String
){
    data class Data(
        val email: String,
        val icon: String,
        val id: Int,
        val password: String,
        val token: String,
        val type: Int,
        val username: String
    )
}

