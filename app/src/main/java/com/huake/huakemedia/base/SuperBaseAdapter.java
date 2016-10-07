package com.huake.huakemedia.base;

/*
 * @创建者     兰昱
 * @创建时间  2016/10/6 20:10
 * @描述	      
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public  class SuperBaseAdapter<ITEMS> extends BaseAdapter {
    
    public List<ITEMS> mdatas;
    public Context mContext;

    public SuperBaseAdapter(List<ITEMS> mdatas,Context context) {
        mContext=context;
        this.mdatas = mdatas;
    }
    @Override
    public int getCount() {
        if (mdatas != null) {
            return mdatas.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (mdatas != null) {
            return mdatas.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }


}


