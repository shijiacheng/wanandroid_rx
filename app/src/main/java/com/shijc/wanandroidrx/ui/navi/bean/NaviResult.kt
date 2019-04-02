package com.shijc.wanandroidrx.ui.navi.bean

/**
 * @Package com.shijc.wanandroidkotlin.ui.navi.bean
 * @Description:
 * @author shijiacheng
 * @date 2019/2/16 下午 12:37
 * @version V1.0
 */
data class NaviResult(
    val `data`: List<Data>,
    val errorCode: Int,
    val errorMsg: String
){
    data class Data(
        val articles: List<Article>,
        val cid: Int,
        val name: String
    )

    data class Article(
        val apkLink: String,
        val author: String,
        val chapterId: Int,
        val chapterName: String,
        val collect: Boolean,
        val courseId: Int,
        val desc: String,
        val envelopePic: String,
        val fresh: Boolean,
        val id: Int,
        val link: String,
        val niceDate: String,
        val origin: String,
        val projectLink: String,
        val publishTime: Long,
        val superChapterId: Int,
        val superChapterName: String,
        val tags: List<Any>,
        val title: String,
        val type: Int,
        val userId: Int,
        val visible: Int,
        val zan: Int
    )
}

