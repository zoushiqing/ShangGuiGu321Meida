package com.huake.huakemedia.view;

/*
 * @创建者     兰昱
 * @创建时间  2016/9/28 12:46
 * @描述	      
 */

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.huake.huakemedia.R;

public class TitleBar extends LinearLayout implements View.OnClickListener {

    private View tv_search;
    private View rl_game;
    private View iv_record;
    private Context mContext;

    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    /**
     * 当在布局文件使用该类的时候，Android系统通过这个构造方法实例化该类
     *
     * @param context
     * @param attrs
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //得到孩子的实例
        tv_search = getChildAt(1);
        rl_game = getChildAt(2);
        iv_record = getChildAt(3);

        //设置点击事件
        tv_search.setOnClickListener(this);
        rl_game.setOnClickListener(this);
        iv_record.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.titlebar_tv_search://搜索
                Toast.makeText(mContext, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.titlebar_rl://游戏
                Toast.makeText(mContext, "游戏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.titlebar_img_recorod://播放历史
                Toast.makeText(mContext, "播放历史", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
