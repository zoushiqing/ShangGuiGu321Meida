package com.huake.huakemedia.activity;

/*
 * @创建者     兰昱
 * @创建时间  2016/10/6 19:13
 * @描述	      
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.huake.huakemedia.R;
import com.huake.huakemedia.base.SuperBaseAdapter;
import com.huake.huakemedia.domain.SearchBean;
import com.huake.huakemedia.utils.Constants;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    @InjectView(R.id.activity_search_et_input)
    EditText mActivitySearchEtInput;
    @InjectView(R.id.activity_search_img_xunfei)
    ImageView mActivitySearchImgXunfei;
    @InjectView(R.id.activity_search_tv_search)
    TextView mActivitySearchTvSearch;
    @InjectView(R.id.activity_search_listview)
    ListView mActivitySearchList;
    @InjectView(R.id.activity_search_progressBar)
    ProgressBar mActivitySearchProgressBar;
    @InjectView(R.id.activity_search_tvinfo)
    TextView mActivitySearchTvinfo;

    private String mSearchContent;
    private ArrayList<SearchBean.ItemsBean> mdatas;
    // 用HashMap存储听写结果
    private HashMap<String, String> mIatResults = new LinkedHashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.inject(this);
        initListener();
        initData();
    }

    private void initData() {
        //        mActivitySearchList.setAdapter();
    }

    private void initListener() {
        mActivitySearchImgXunfei.setOnClickListener(this);
        mActivitySearchTvSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //讯飞
            case R.id.activity_search_img_xunfei:
                showDialog();
                break;
            //搜索
            case R.id.activity_search_tv_search: //隐藏输入框
                InputMethodManager systemService = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                systemService.hideSoftInputFromWindow(v.getWindowToken(), 0);
                searchText();
                break;
        }
    }


    private void searchText() {

        mSearchContent = mActivitySearchEtInput.getText().toString().trim();
        if (!TextUtils.isEmpty(mSearchContent)) {
            searchFromNet(mSearchContent);
        }
    }

    private void searchFromNet(String content) {

        mActivitySearchProgressBar.setVisibility(View.VISIBLE);
        RequestParams params = new RequestParams(Constants.SEARCH_URL + content);
        x.http().get(params, new Callback.CommonCallback<String>() {


            @Override
            public void onSuccess(String result) {
                //请求成功就有数据啊
                parseJson(result);
                mActivitySearchProgressBar.setVisibility(View.GONE);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(SearchActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
            }
        });

    }

    private void parseJson(String result) {
        Gson gson = new Gson();
        SearchBean searchBean = gson.fromJson(result, SearchBean.class);
        String flag = searchBean.getFlag();
        //有数据
        if (flag.equalsIgnoreCase("ok")) {
            mActivitySearchTvinfo.setVisibility(View.GONE);
            //开始解析吧
            mdatas = (ArrayList<SearchBean.ItemsBean>) searchBean.getItems();
            System.out.println(mdatas.get(0).toString());
            showData();
        } else {
            mActivitySearchTvinfo.setVisibility(View.VISIBLE);
        }
    }

    private void showData() {
        if (mdatas != null && mdatas.size() > 0) {
            SearchPagerAdapter superBaseAdapter = new SearchPagerAdapter(mdatas, SearchActivity.this);
            mActivitySearchList.setAdapter(superBaseAdapter);
        }
    }

    class SearchPagerAdapter extends SuperBaseAdapter<SearchBean.ItemsBean> {


        public SearchPagerAdapter(List<SearchBean.ItemsBean> mdatas, Context context) {
            super(mdatas, context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.item_net_video_pager, null);
                viewHolder = new ViewHolder(convertView);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            SearchBean.ItemsBean itemsBean = mdatas.get(position);
            viewHolder.mItemNetVideopagerTvTitle.setText(itemsBean.getItemTitle());
            viewHolder.mItemNetVideopagerTvDesc.setText(itemsBean.getKeywords());
            Glide.with(mContext).load(itemsBean.getItemImage().getImgUrl1()).into(viewHolder.mItemNetVideopagerImg);
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

    private void showDialog() {
        //1.创建RecognizerDialog对象
        RecognizerDialog mDialog = new RecognizerDialog(this, new MyInitListener());
        //2.设置accent、 language等参数
        mDialog.setParameter(SpeechConstant.LANGUAGE, "zh_cn");//中文
        mDialog.setParameter(SpeechConstant.ACCENT, "mandarin");//普通话
        //若要将UI控件用于语义理解，必须添加以下参数设置，设置之后onResult回调返回将是语义理解
        //3.设置回调接口
        mDialog.setListener(new MyRecognizerDialogListener());
        //4.显示dialog，接收语音输入
        mDialog.show();
    }

    class MyRecognizerDialogListener implements RecognizerDialogListener {

        /**
         * @param recognizerResult
         * @param b                是否说话结束
         */
        @Override
        public void onResult(RecognizerResult recognizerResult, boolean b) {
            String result = recognizerResult.getResultString();
            String text =com.huake.huakemedia.utils.JsonParser.parseIatResult(result);
            //解析好的
            String sn = null;
            // 读取json结果中的sn字段
            try {
                JSONObject resultJson = new JSONObject(recognizerResult.getResultString());
                sn = resultJson.optString("sn");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            mIatResults.put(sn, text);

            StringBuffer resultBuffer = new StringBuffer();//拼成一句
            for (String key : mIatResults.keySet()) {
                resultBuffer.append(mIatResults.get(key));
            }

            mActivitySearchEtInput.setText(resultBuffer.toString());
            mActivitySearchEtInput.setSelection(mActivitySearchEtInput.length());

        }

        /**
         * 出错了
         *
         * @param speechError
         */
        @Override
        public void onError(SpeechError speechError) {
            Log.e("MainActivity", "onError ==" + speechError.getMessage());

        }
    }

    class MyInitListener implements InitListener {

        @Override
        public void onInit(int i) {
            if (i != ErrorCode.SUCCESS) {
                Toast.makeText(SearchActivity.this, "初始化失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
