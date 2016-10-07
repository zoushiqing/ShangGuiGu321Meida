package com.huake.huakemedia.pager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.huake.huakemedia.R;
import com.huake.huakemedia.activity.VideoPlayerActivity;
import com.huake.huakemedia.base.BasePager;
import com.huake.huakemedia.base.SuperBaseAdapter;
import com.huake.huakemedia.domain.MediaItem;
import com.huake.huakemedia.domain.NetMediaItem;
import com.huake.huakemedia.utils.CommonUtils;
import com.huake.huakemedia.utils.Constants;
import com.huake.huakemedia.view.XListView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static android.view.View.inflate;

/**
 * 作者：杨光福 on 2016/7/16 11:48
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：网络视频页面
 */
public class NetVideoPager extends BasePager implements AdapterView.OnItemClickListener {

    @InjectView(R.id.videopager_list)
    XListView mVideopagerList;
    @InjectView(R.id.net_videopager_pb_load)
    ProgressBar mNetVideopagerPbLoad;
    @InjectView(R.id.net_videopager_tv_load)
    TextView mNetVideopagerTvLoad;
    //请求的数据
    private List<NetMediaItem.TrailersBean> mTrailersBean;
    private boolean isLoadMore=false;
    private NetVideoPagerAdapter mNetVideoPagerAdapter;
    public static final int LOADDATA=0;
    public static final int LOADMORE=1;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case LOADDATA:
                    //如果有数据
                    if (mTrailersBean != null && mTrailersBean.size() > 0) {
                        mNetVideoPagerAdapter = new NetVideoPagerAdapter(mTrailersBean,mContext);
                        mVideopagerList.setAdapter(mNetVideoPagerAdapter);
                        loadFinish();
                    } else {
                        mNetVideopagerTvLoad.setVisibility(View.VISIBLE);
                    }
                    break;
                case LOADMORE:
                    mTrailersBean.addAll(mTrailersBean);
                    mNetVideoPagerAdapter.notifyDataSetChanged();
                    loadFinish();
                    break;
            }

            //最终加载完成，进度条肯定要消失
            mNetVideopagerPbLoad.setVisibility(View.GONE);
        }
    };

    public NetVideoPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        View inflate = inflate(mContext, R.layout.netvideo_pager, null);
        ButterKnife.inject(this, inflate);
        mVideopagerList.setOnItemClickListener(this);
        mVideopagerList.setPullLoadEnable(true);
        mVideopagerList.setPullRefreshEnable(true);
        mVideopagerList.setXListViewListener(new MyXListViewListener());

        return inflate;
    }

    @Override
    public void initData() {
        getDataFromNet();
    }

    private void getDataFromNet() {

        RequestParams params = new RequestParams(Constants.NET_URL);
        x.http().get(params, new Callback.CommonCallback<String>() {


            @Override
            public void onSuccess(String result) {
                //请求成功就有数据啊
                parseJson(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(mContext, "网络错误", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
            }
        });
    }

    private void parseJson(String result ) {
        Gson gson = new Gson();
        //解析的实体类一定要不要写错，崩溃都没有提示
        NetMediaItem netMediaItem = gson.fromJson(result, NetMediaItem.class);
        mTrailersBean = netMediaItem.getTrailers();
        mHandler.sendEmptyMessage(LOADDATA);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //因为播放界面的实体类不一样，自己转换一下
        Intent intent = new Intent(mContext, VideoPlayerActivity.class);

        ArrayList<MediaItem> mediaItem=convertToMediatem(mTrailersBean);
        Bundle bundle = new Bundle();
        bundle.putSerializable("media",mediaItem);
        intent.putExtras(bundle);
        intent.putExtra("position",position-1);//减去一是因为请求头占了一个条目

        mContext.startActivity(intent);

    }

    private ArrayList<MediaItem> convertToMediatem(List<NetMediaItem.TrailersBean> trailersBean) {
        ArrayList<MediaItem> mediaItems = new ArrayList<>();
        for (NetMediaItem.TrailersBean trailer : trailersBean) {
            MediaItem mediaItem = new MediaItem();
            mediaItem.setData(trailer.getUrl());
            mediaItem.setName(trailer.getMovieName());
            mediaItems.add(mediaItem);
        }
        return mediaItems;
    }

    class MyXListViewListener implements XListView.IXListViewListener {

        @Override
        public void onRefresh() {
            getDataFromNet();
        }

        @Override
        public void onLoadMore() {
            getMoreData();
        }
    }

    private void getMoreData() {
        mHandler.sendEmptyMessage(LOADMORE);
    }

    class NetVideoPagerAdapter extends SuperBaseAdapter<NetMediaItem.TrailersBean> {


        public NetVideoPagerAdapter(List<NetMediaItem.TrailersBean> mdatas, Context context) {
            super(mdatas, context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.item_net_video_pager, null);
                viewHolder=new ViewHolder(convertView);
            }else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            NetMediaItem.TrailersBean trailersBean = mTrailersBean.get(position);
            viewHolder.mItemNetVideopagerTvTitle.setText(trailersBean.getMovieName());
            viewHolder.mItemNetVideopagerTvDesc.setText(trailersBean.getSummary());
            Glide.with(mContext).load(trailersBean.getCoverImg()).into(viewHolder.mItemNetVideopagerImg);
            return convertView;
        }

    }
    class ViewHolder {
        @InjectView(R.id.item_net_videopager_img)
        ImageView mItemNetVideopagerImg;
        @InjectView(R.id.item_net_videopager_tv_title)
        TextView mItemNetVideopagerTvTitle;
        @InjectView(R.id.item_net_videopager_tv_desc)
        TextView mItemNetVideopagerTvDesc;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
            view.setTag(this);
        }
    }
    private void loadFinish() {
        mVideopagerList.stopRefresh();
        mVideopagerList.stopLoadMore();
        mVideopagerList.setRefreshTime("更新时间:"+ CommonUtils.getSystemTime());
    }




}
