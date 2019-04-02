package com.shijc.wanandroidrx.ui.home.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.shijc.wanandroidrx.R
import com.shijc.wanandroidrx.ui.home.bean.ArticleModel
import com.shijc.wanandroidrx.ui.home.bean.BannerModel
import com.shijc.wanandroidrx.utils.GlideImageLoader
import com.shijc.wanandroidrx.utils.TimeUtils
import com.youth.banner.Banner


/**
 * @Package com.shijc.wanandroidkotlin.ui.home.adapter
 * @Description:
 * @author shijiacheng
 * @date 2019/2/13 下午 4:52
 * @version V1.0
 */
class HomeAdapter(
    private val context: Context,
    private val data: List<ArticleModel>

) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var banner: List<BannerModel.Data> = ArrayList<BannerModel.Data>()

    fun setBanner(banner: List<BannerModel.Data>){
        this.banner = banner
        notifyDataSetChanged()
    }

    private val BANNER = 0
    private val NORMAL = 1

    override fun getItemCount(): Int = data.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = when (viewType) {
        BANNER -> {
            BannerViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_home_banner, parent, false)
            )
        }
        else -> {
            RecyclerViewHolder(
                LayoutInflater.from(context)
                    .inflate(R.layout.item_rv_article, parent, false)
            )
        }

    }

    /**
     * 利用kotlin中的when代替Java中的if/else
     * 返回不同的item类型
     */
    override fun getItemViewType(position: Int): Int = when (position) {
        0 -> {
            BANNER
        }
        else -> {
            NORMAL
        }
    }

    /**
     * 根据不同的item类型绑定不同的数据
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            BANNER -> {
                if (holder is BannerViewHolder) {
                    //设置图片加载器
                    holder.banner.setImageLoader(GlideImageLoader())

                    if (banner.isEmpty()){
                        holder.banner.setImages(listOf(R.drawable.bg1))
                    }else{
                        //设置图片集合
                        val list = ArrayList<String>()
                        val titles = ArrayList<String>()
                        for (item in banner){
                            list.add(item.imagePath)
                            titles.add(item.title)
                        }
                        holder.banner.setImages(list)
                        holder.banner.setBannerTitles(titles)

                        holder.banner.setOnBannerListener {
                            if (listener!=null){
                                listener!!.onBannerClick(banner[it],it)
                            }
                        }
                    }
                    //banner设置方法全部调用完毕时最后调用
                    holder.banner.start()

                }
            }
            else -> {
                if (holder is RecyclerViewHolder) {
                    holder?.tvAuthor?.text = data[position-1].author
                    holder?.tvTime?.text = TimeUtils.long2String(data[position-1].publishTime, TimeUtils.FORMAT_TYPE_1)
                    holder?.tvContent?.text = data[position-1].title
                    holder?.tvClassify?.text = data[position-1].superChapterName

                    holder.itemView.setOnClickListener {
                        if (listener!=null){
                            listener!!.onItemClick(data[position-1],position-1,it)
                        }
                    }
                }
            }
        }
    }


    class BannerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var banner: Banner = view.findViewById(R.id.banner)
    }

    inner class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvAuthor: TextView = view.findViewById(R.id.tv_author)
        var tvTime: TextView = view.findViewById(R.id.tv_time)
        var tvContent: TextView = view.findViewById(R.id.tv_content)
        var tvClassify: TextView = view.findViewById(R.id.tv_classify)
    }

    var listener:ClickListener? = null

    interface ClickListener{
        fun onBannerClick(item: BannerModel.Data,position: Int);
        fun onItemClick(item: ArticleModel, position:Int, view: View)
    }
}