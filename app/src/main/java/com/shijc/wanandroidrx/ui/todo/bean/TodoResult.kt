package com.shijc.wanandroidrx.ui.todo.bean

/**
 * @Package com.shijc.wanandroidkotlin.ui.todo.bean
 * @Description:
 * @author shijiacheng
 * @date 2019/2/26 上午 9:45
 * @version V1.0
 */
data class TodoResult(
    val `data`: Data,
    val errorCode: Int,
    val errorMsg: String
){
    data class Data(
        val doneList: List<Done>,
        val todoList: List<Done>,
        val type: Int
    )

    data class Done(
        val date: Long,
        val todoList: List<Todo>
    )

    data class Todo(
        val completeDate: Long,
        val completeDateStr: String,
        val content: String,
        val date: Long,
        val dateStr: String,
        val id: Int,
        val priority: Int,
        val status: Int,
        val title: String,
        val type: Int,
        val userId: Int
    )
}



