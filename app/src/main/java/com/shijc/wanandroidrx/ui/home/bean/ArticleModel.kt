package com.shijc.wanandroidrx.ui.home.bean

/**
 * @Package com.shijc.wanandroidkotlin.ui.home.bean
 * @Description:
 * @author shijiacheng
 * @date 2019/2/15 上午 10:11
 * @version V1.0
 */
data class ArticleModel(

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