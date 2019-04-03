package com.shijc.wanandroidrx.ui.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.shijc.wanandroidrx.R;
import com.shijc.wanandroidrx.ui.home.bean.ArticleModel;
import com.shijc.wanandroidrx.ui.home.bean.BannerModel;
import com.shijc.wanandroidrx.utils.GlideImageLoader;
import com.shijc.wanandroidrx.utils.TimeUtils;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shijiacheng
 * @version V1.0
 * @Package com.shijc.wanandroidrx.ui.home.adapter
 * @Description:
 * @date 2019/4/3 上午 8:48
 */
public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {




    private Context context;
    private List<ArticleModel> data;

    public HomeAdapter(Context context, List<ArticleModel> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == BANNER){
            return new BannerViewHolder(LayoutInflater.from(context).inflate(R.layout.item_home_banner, parent, false));
        }else {
            return new RecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rv_article, parent, false));
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        if (getItemViewType(position) == BANNER){
            if (holder instanceof BannerViewHolder) {
                //设置图片加载器
                ((BannerViewHolder) holder).banner.setImageLoader(new GlideImageLoader());

                if (banner.isEmpty()){
                    List<Integer> l = new ArrayList<Integer>();
                    l.add(R.drawable.bg1);
                    ((BannerViewHolder) holder).banner.setImages(l);
                }else{
                    //设置图片集合
                    List<String> list = new ArrayList<String>();
                    List<String> titles = new ArrayList<String>();
                    for (BannerModel.Data item : banner){
                        list.add(item.getImagePath());
                        titles.add(item.getTitle());
                    }
                    ((BannerViewHolder) holder).banner.setImages(list);
                    ((BannerViewHolder) holder).banner.setBannerTitles(titles);

                    ((BannerViewHolder) holder).banner.setOnBannerListener(new OnBannerListener() {
                        @Override
                        public void OnBannerClick(int position) {
                            if (listener!=null){
                                listener.onBannerClick(banner.get(position),position);
                            }
                        }
                    });
                }
                //banner设置方法全部调用完毕时最后调用
                ((BannerViewHolder) holder).banner.start();

            }
        }else {
            if (holder instanceof RecyclerViewHolder) {
                ((RecyclerViewHolder) holder).tvAuthor.setText(data.get(position-1).getAuthor());
                ((RecyclerViewHolder) holder).tvTime.setText(TimeUtils.long2String(data.get(position-1).getPublishTime(), TimeUtils.FORMAT_TYPE_1));
                ((RecyclerViewHolder) holder).tvContent.setText(data.get(position-1).getTitle());
                ((RecyclerViewHolder) holder).tvClassify.setText(data.get(position-1).getSuperChapterName());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener!=null){
                            listener.onItemClick(data.get(position-1),position-1,v);
                        }
                    }
                });
            }
        }

    }


    class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView tvAuthor;
        TextView tvTime;
        TextView tvContent;
        TextView tvClassify;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            tvAuthor = itemView.findViewById(R.id.tv_author);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvContent = itemView.findViewById(R.id.tv_content);
            tvClassify = itemView.findViewById(R.id.tv_classify);
        }
    }


    class BannerViewHolder extends RecyclerView.ViewHolder {

        Banner banner;

        public BannerViewHolder(View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
        }
    }







    private List<BannerModel.Data> banner = new ArrayList<BannerModel.Data>();

    public void setBanner(List<BannerModel.Data> banner){
        this.banner = banner;
        notifyDataSetChanged();
    }

    private static int BANNER = 0;
    private static int NORMAL = 1;


    @Override
    public int getItemViewType(int position) {

        if (position == 0){
            return BANNER;
        }else {
            return NORMAL;
        }

    }



    private ClickListener listener;

    public void setListener(ClickListener listener) {
        this.listener = listener;
    }

    public interface ClickListener{
        void onBannerClick(BannerModel.Data item ,int position);
        void onItemClick(ArticleModel item, int position, View view );
    }
}
