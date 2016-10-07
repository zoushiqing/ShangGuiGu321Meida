package com.huake.huakemedia.adapter;

/*
 * @创建者     兰昱
 * @创建时间  2016/10/3 15:55
 * @描述	      
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.huake.huakemedia.R;
import com.huake.huakemedia.domain.MediaItem;
import com.huake.huakemedia.utils.CommonUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class VideoAudioAdapter extends BaseAdapter{

    private List<MediaItem> mMediaItems;
    private Context mContext;
    private boolean misVideo;

    public VideoAudioAdapter(Context context,List<MediaItem> mediaItems,boolean isVideo) {
        mMediaItems = mediaItems;
        mContext=context;
        misVideo=isVideo;
    }

    @Override
    public int getCount() {
        return mMediaItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mMediaItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_video_pager, null);
            viewHolder = new ViewHolder(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        MediaItem mediaItem = mMediaItems.get(position);
        viewHolder.mItemVideopagerTvName.setText(mediaItem.getName());
        viewHolder.mItemVideopagerTvDuration.setText(CommonUtils.timeToString((int) mediaItem.getDuration()));
        viewHolder.mItemVideopagerTvSize.setText(android.text.format.Formatter.formatFileSize(mContext, mediaItem.getSize()));
        if (!misVideo) {
            viewHolder.mItemVideopagerImg.setImageResource(R.drawable.music_default_bg);
        }
        return convertView;
    }

    class ViewHolder {
        @InjectView(R.id.item_videopager_img)
        ImageView mItemVideopagerImg;
        @InjectView(R.id.item_videopager_tv_name)
        TextView mItemVideopagerTvName;
        @InjectView(R.id.item_videopager_tv_duration)
        TextView mItemVideopagerTvDuration;
        @InjectView(R.id.item_videopager_tv_size)
        TextView mItemVideopagerTvSize;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
            view.setTag(this);
        }
    }
}
