package com.huake.huakemedia.adapter;

/*
 * @创建者     兰昱
 * @创建时间  2016/10/7 17:44
 * @描述	      
 */
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.huake.huakemedia.R;
import com.huake.huakemedia.base.SuperBaseAdapter;
import com.huake.huakemedia.domain.NetAudioItem;
import com.huake.huakemedia.utils.CommonUtils;

import org.xutils.common.util.DensityUtil;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import pl.droidsonroids.gif.GifImageView;

public class NetAudioAdapter extends SuperBaseAdapter<NetAudioItem.ListBean> {

    private static final int TYPE_VIDEO = 0;//视频  注意元素一定要从0开始
    private static final int TYPE_IMAGE = 1;//图片
    private static final int TYPE_TEXT = 2;//文字
    private static final int TYPE_GIF = 3;//动态图
    private static final int TYPE_AD = 4;//软件推广

    //请求数据
    private List<NetAudioItem.ListBean> mdatas;
    private Context mContext;

    public NetAudioAdapter(List<NetAudioItem.ListBean> datas, Context context) {
        super(datas, context);
        mdatas = datas;
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        String typeString = mdatas.get(position).getType();
        int type = -1;
        if ("video".equals(typeString)) {
            type = TYPE_VIDEO;
        } else if ("image".equals(typeString)) {
            type = TYPE_IMAGE;
        } else if ("text".equals(typeString)) {
            type = TYPE_TEXT;
        } else if ("gif".equals(typeString)) {
            type = TYPE_GIF;
        } else {
            type = TYPE_AD;//广播
        }
        return type;
    }

    @Override
    public int getViewTypeCount() {
        return 5;//5种类型
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //getView绝对是个大文章
        int itemViewType = getItemViewType(position);
        ViewHolder viewholder;
        if (convertView == null) {
            //不同类型的view要用不同的布局
            viewholder=new ViewHolder();
            convertView = initConvertView(convertView, itemViewType, viewholder);
            //初始化公共布局必须在下面，因为上一个方法才去拿了布局，坑爹啊，架构比较乱
            initCommonView(convertView, itemViewType, viewholder);
            //按照老子的习惯，还是习惯先初始化公共视图
            convertView.setTag(viewholder);
        } else {
            viewholder = (ViewHolder) convertView.getTag();
        }
        //绑定数据
        //根据位置得到数据,绑定数据
        NetAudioItem.ListBean listBean = mdatas.get(position);
        bindData(itemViewType,listBean,viewholder);

        return convertView;
    }

    private void initCommonView(View convertView, int itemViewType, ViewHolder viewHolder) {
        switch (itemViewType) {
            case TYPE_VIDEO://视频
            case TYPE_IMAGE://图片
            case TYPE_TEXT://文字
            case TYPE_GIF://gif
                //加载除开广告部分的公共部分视图
                //user info
                viewHolder.iv_headpic = (ImageView) convertView.findViewById(R.id.iv_headpic);
                viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
                viewHolder.tv_time_refresh = (TextView) convertView.findViewById(R.id.tv_time_refresh);
                viewHolder.iv_right_more = (ImageView) convertView.findViewById(R.id.iv_right_more);
                //bottom
                viewHolder.iv_video_kind = (ImageView) convertView.findViewById(R.id.iv_video_kind);
                viewHolder.tv_video_kind_text = (TextView) convertView.findViewById(R.id.tv_video_kind_text);
                viewHolder.tv_shenhe_ding_number = (TextView) convertView.findViewById(R.id.tv_shenhe_ding_number);
                viewHolder.tv_shenhe_cai_number = (TextView) convertView.findViewById(R.id.tv_shenhe_cai_number);
                viewHolder.tv_posts_number = (TextView) convertView.findViewById(R.id.tv_posts_number);
                viewHolder.ll_download = (LinearLayout) convertView.findViewById(R.id.ll_download);

                break;
        }


        //中间公共部分 -所有的都有
        viewHolder.tv_context = (TextView) convertView.findViewById(R.id.tv_context);
    }

    private void bindData(int itemViewType, NetAudioItem.ListBean mediaItem, ViewHolder viewHolder) {
        switch (itemViewType) {
            case TYPE_VIDEO:
                //视频
                bindData(viewHolder, mediaItem);
                //第一个参数是视频播放地址，第二个参数是显示封面的地址，第三参数是标题
                viewHolder.jcv_videoplayer.setUp(mediaItem.getVideo().getVideo().get(0), mediaItem.getVideo().getThumbnail().get(0), null);
                viewHolder.tv_play_nums.setText(mediaItem.getVideo().getPlaycount() + "次播放");
                viewHolder.tv_video_duration.setText(CommonUtils.timeToString(mediaItem.getVideo().getDuration() * 1000) + "");

                break;
            case TYPE_IMAGE://图片
                bindData(viewHolder, mediaItem);
                viewHolder.iv_image_icon.setImageResource(R.drawable.bg_item);
                //限制高度最大为一个定值
                int  height = mediaItem.getImage().getHeight()<= DensityUtil.getScreenHeight()*0.75?mediaItem.getImage().getHeight(): (int) (DensityUtil.getScreenHeight() * 0.75);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DensityUtil.getScreenWidth(),height);
                viewHolder.iv_image_icon.setLayoutParams(params);
                if(mediaItem.getImage() != null &&  mediaItem.getImage().getBig()!= null&&mediaItem.getImage().getBig().size() >0){
                    //                    x.image().bind(viewHolder.iv_image_icon, mediaItem.getImage().getBig().get(0));
                    Glide.with(mContext).load(mediaItem.getImage().getBig().get(0)).placeholder(R.drawable.bg_item).error(R.drawable.bg_item).diskCacheStrategy(DiskCacheStrategy.ALL).into(viewHolder.iv_image_icon);
                }
                break;
            case TYPE_TEXT://文字
                bindData(viewHolder, mediaItem);
                break;
            case TYPE_GIF://gif
                bindData(viewHolder, mediaItem);
                System.out.println("mediaItem.getGif().getImages().get(0)" + mediaItem.getGif().getImages().get(0));
                Glide.with(mContext).load(mediaItem.getGif().getImages().get(0)).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(viewHolder.iv_image_gif);

                break;
            case TYPE_AD://软件广告
                break;
        }

        if (mediaItem.getText() != null) {
            viewHolder.tv_context.setText(mediaItem.getText());
        }
    }
    //初始化公共数据
    private void bindData(ViewHolder viewHolder, NetAudioItem.ListBean  mediaItem) {
        if(mediaItem.getU()!=null&& mediaItem.getU().getHeader()!=null&&mediaItem.getU().getHeader().get(0)!=null){
//            x.image().bind(viewHolder.iv_headpic, mediaItem.getU().getHeader().get(0));
            Glide.with(mContext).load(mediaItem.getU().getHeader().get(0)).into(viewHolder.iv_headpic);
        }
        if(mediaItem.getU() != null&&mediaItem.getU().getName()!= null){
            viewHolder.tv_name.setText(mediaItem.getU().getName()+"");
        }

        viewHolder.tv_time_refresh.setText(mediaItem.getPasstime());

        //设置标签
        List<NetAudioItem.ListBean.TagsBean> tagsEntities = mediaItem.getTags();
        if (tagsEntities != null && tagsEntities.size() > 0) {
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < tagsEntities.size(); i++) {
                buffer.append(tagsEntities.get(i).getName() + " ");
            }
            viewHolder.tv_video_kind_text.setText(buffer.toString());
        }
        //设置点赞，踩,转发
        viewHolder.tv_shenhe_ding_number.setText(mediaItem.getUp());
        viewHolder.tv_shenhe_cai_number.setText(mediaItem.getDown() + "");
        viewHolder.tv_posts_number.setText(mediaItem.getForward()+"");

    }

    private View initConvertView(View convertView, int itemViewType, ViewHolder viewHolder) {
        //根据不同的类型初始化不同的布局文件
        switch (itemViewType) {
            case TYPE_VIDEO://视频
                convertView = View.inflate(mContext, R.layout.all_video_item, null);
                //在这里实例化特有的,确实仅仅是中间特有的
                viewHolder.tv_play_nums = (TextView) convertView.findViewById(R.id.tv_play_nums);
                viewHolder.tv_video_duration = (TextView) convertView.findViewById(R.id.tv_video_duration);
                viewHolder.iv_commant = (ImageView) convertView.findViewById(R.id.iv_commant);
                viewHolder.tv_commant_context = (TextView) convertView.findViewById(R.id.tv_commant_context);
                viewHolder.jcv_videoplayer = (JCVideoPlayer) convertView.findViewById(R.id.jcv_videoplayer);
                break;
            case TYPE_IMAGE://图片
                convertView = View.inflate(mContext, R.layout.all_image_item, null);
                viewHolder.iv_image_icon = (ImageView) convertView.findViewById(R.id.iv_image_icon);
                break;
            case TYPE_TEXT://文字
                convertView = View.inflate(mContext, R.layout.all_text_item, null);
                break;
            case TYPE_GIF://gif
                convertView = View.inflate(mContext, R.layout.all_gif_item, null);
                viewHolder.iv_image_gif = (GifImageView) convertView.findViewById(R.id.iv_image_gif);
                break;
            case TYPE_AD://软件广告
                convertView = View.inflate(mContext, R.layout.all_ad_item, null);
                viewHolder.btn_install = (Button) convertView.findViewById(R.id.btn_install);
                viewHolder.iv_image_icon = (ImageView) convertView.findViewById(R.id.iv_image_icon);
                break;

        }
        return convertView;
    }


    static class ViewHolder {
        //user_info
        ImageView iv_headpic;
        TextView tv_name;
        TextView tv_time_refresh;
        ImageView iv_right_more;
        //bottom
        ImageView iv_video_kind;
        TextView tv_video_kind_text;
        TextView tv_shenhe_ding_number;
        TextView tv_shenhe_cai_number;
        TextView tv_posts_number;
        LinearLayout ll_download;

        //中间公共部分 -所有的都有
        TextView tv_context;


        //Video
        //        TextView tv_context;
        TextView tv_play_nums;
        TextView tv_video_duration;
        ImageView iv_commant;
        TextView tv_commant_context;
        JCVideoPlayer jcv_videoplayer;

        //Image
        ImageView iv_image_icon;
        //        TextView tv_context;

        //Text
        //        TextView tv_context;

        //Gif
        GifImageView iv_image_gif;
        //        TextView tv_context;

        //软件推广
        Button btn_install;
        //        TextView iv_image_icon;
        //TextView tv_context;


    }

}
