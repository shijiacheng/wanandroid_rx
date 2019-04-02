package com.shijc.wanandroidrx.ui.systemtree.bean

/**
 * @Package com.shijc.wanandroidkotlin.ui.systemtree.bean
 * @Description:
 * @author shijiacheng
 * @date 2019/2/15 下午 3:36
 * @version V1.0
 */
data class SystemTreeResult(
    val `data`: List<Data>,
    val errorCode: Int,
    val errorMsg: String
){
    data class Data(
        val children: List<Children>,
        val courseId: Int,
        val id: Int,
        val name: String,
        val order: Int,
        val parentChapterId: Int,
        val userControlSetTop: Boolean,
        val visible: Int
    )

    data class Children(
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



