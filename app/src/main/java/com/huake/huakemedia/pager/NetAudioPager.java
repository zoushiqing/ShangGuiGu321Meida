package com.huake.huakemedia.pager;

import android.content.Context;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.huake.huakemedia.R;
import com.huake.huakemedia.adapter.NetAudioAdapter;
import com.huake.huakemedia.base.BasePager;
import com.huake.huakemedia.domain.NetAudioItem;
import com.huake.huakemedia.utils.Constants;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static android.view.View.inflate;

/**
 * 作者：杨光福 on 2016/7/16 11:48
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：网络音频页面
 */
public class NetAudioPager extends BasePager {


    @InjectView(R.id.net_audio_pager_list)
    ListView mNetAudioPagerList;
    @InjectView(R.id.net_audio_pager_pb_load)
    ProgressBar mNetAudioPagerPbLoad;
    @InjectView(R.id.net_audio_pager_tv_load)
    TextView mNetAudioPagerTvLoad;
    private List<NetAudioItem.ListBean> mdatas;
    public NetAudioPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        View inflate = inflate(mContext, R.layout.netaudio_pager, null);
        ButterKnife.inject(this, inflate);
        return inflate;
    }

    @Override
    public void initData() {
        getDataFromNet();
    }
    private void getDataFromNet() {

        RequestParams params = new RequestParams(Constants.ALL_RES_URL);
        x.http().get(params, new Callback.CommonCallback<String>() {


            @Override
            public void onSuccess(String result) {
                //请求成功就有数据啊
                parseJson(result);
                mNetAudioPagerPbLoad.setVisibility(View.GONE);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(mContext, "网络错误", Toast.LENGTH_SHORT).show();
                mNetAudioPagerPbLoad.setVisibility(View.GONE);
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
        NetAudioItem netAudioItem = gson.fromJson(result, NetAudioItem.class);
        mdatas = netAudioItem.getList();
        if (mdatas == null||mdatas.size()==0) {
            mNetAudioPagerTvLoad.setVisibility(View.VISIBLE);
        }else {
            mNetAudioPagerTvLoad.setVisibility(View.GONE);
            mNetAudioPagerList.setAdapter(new NetAudioAdapter(mdatas,mContext));
        }

    }



}
