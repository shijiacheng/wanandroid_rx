package com.shijc.wanandroidrx.ui.project.bean

/**
 * @Package com.shijc.wanandroidkotlin.ui.project.bean
 * @Description:
 * @author shijiacheng
 * @date 2019/2/16 上午 11:22
 * @version V1.0
 */
data class ProjectTitleResult(
    val `data`: List<Data>,
    val errorCode: Int,
    val errorMsg: String
){
    data class Data(
        val children: List<Any>,
        val courseId: Int,
        val id: Int,
        val name: String,
        val order: Int,
        val parentChapterId: Int,
        val userControlSetTop: Boolean,
        val visible: Int
    )
}

