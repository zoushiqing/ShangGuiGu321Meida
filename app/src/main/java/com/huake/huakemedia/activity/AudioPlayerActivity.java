package com.huake.huakemedia.activity;

/*
 * @创建者     兰昱
 * @创建时间  2016/10/3 18:10
 * @描述	      
 */

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.audiofx.Visualizer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.huake.huakemedia.IMusicPlayerService;
import com.huake.huakemedia.R;
import com.huake.huakemedia.domain.MediaItem;
import com.huake.huakemedia.service.MusicPlayerService;
import com.huake.huakemedia.utils.CacheUtils;
import com.huake.huakemedia.utils.CommonUtils;
import com.huake.huakemedia.utils.LyricUtils;
import com.huake.huakemedia.view.BaseVisualizerView;
import com.huake.huakemedia.view.ShowLyricView;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;

public class AudioPlayerActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    @InjectView(R.id.iv_icon)
    BaseVisualizerView mBaseVisualizerView;
    @InjectView(R.id.tv_artist)
    TextView mTvArtist;
    @InjectView(R.id.tv_name)
    TextView mTvName;
    @InjectView(R.id.tv_time)
    TextView mTvTime;
    @InjectView(R.id.seekbar_audio)
    SeekBar mSeekbarAudio;
    @InjectView(R.id.btn_audio_playmode)
    Button mBtnAudioPlaymode;
    @InjectView(R.id.btn_audio_pre)
    Button mBtnAudioPre;
    @InjectView(R.id.btn_audio_start_pause)
    Button mBtnAudioStartPause;
    @InjectView(R.id.btn_audio_next)
    Button mBtnAudioNext;
    @InjectView(R.id.btn_lyrc)
    Button mBtnLyrc;
    @InjectView(R.id.activiy_audio_tv_lyric)
    ShowLyricView mActiviyAudioTvLyric;

    //消息处理器标识
    private static final int PROGRESS = 1;
    private static final int SHOW_LYRIC = 2;

    private int mPosition;//直接通过文件位置查找歌曲，倒是省事啊
    private IMusicPlayerService mImusicPlayerService;//得到服务的引用
    private boolean mNotification;//用来判断是否来自通知栏，如果来自通知栏当然就不用从头播放了
    private ServiceConnection mConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //如果连接成功，就可以拿到inbinder啦
            mImusicPlayerService = IMusicPlayerService.Stub.asInterface(service);
            if (mImusicPlayerService != null) {
                try {
                    if (mNotification) {
                        //如果是通知栏就不用重新播放了
                        onEventMainThread(new MediaItem());
                    } else {
                        mImusicPlayerService.openAudio(mPosition);
                    }

                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * 当断开连接的时候回调这个方法
         * @param name
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {
            //连接失败置空
            if (mImusicPlayerService != null) {
                try {
                    mImusicPlayerService.stop();
                    mImusicPlayerService = null;
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case PROGRESS:
                    try {
                        mTvTime.setText(CommonUtils.timeToString(mImusicPlayerService.getCurrentPosition()) + "/"
                                + CommonUtils.timeToString(mImusicPlayerService.getDuration()));
                        mSeekbarAudio.setProgress(mImusicPlayerService.getCurrentPosition());
                        mHandler.removeMessages(PROGRESS);
                        mHandler.sendEmptyMessageDelayed(PROGRESS, 1000);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                case SHOW_LYRIC:
                    try {
                        //歌曲的位置相当于播放时刻
                        int currentPosition = mImusicPlayerService.getCurrentPosition();
                        mActiviyAudioTvLyric.setLyricPosition(currentPosition);
                        //歌词进度条当然也需要时刻刷新
                        mHandler.removeMessages(SHOW_LYRIC);
                        mHandler.sendEmptyMessage(SHOW_LYRIC);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
            }


        }
    };
    private Visualizer mVisualizer;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_player);
        ButterKnife.inject(this);
        EventBus.getDefault().register(this);
        initView();//处理一下播放动画
        initListener();
        initData();
        bindAndStartService();
    }

    private void bindAndStartService() {
        Intent intent = new Intent(this, MusicPlayerService.class);
        intent.setAction("com.huake.huakemedia_OPENAUDIO");
        //不至于实例化多个服务
        bindService(intent, mConn, Context.BIND_AUTO_CREATE);
        startService(intent);
    }

    private void initData() {
        mNotification = getIntent().getBooleanExtra("notification", false);
        if (!mNotification) {
            //如果不是通知栏，那就是原来列表栏目的数据，如果是通知栏，那位置也不需要变化嘛
            mPosition = getIntent().getIntExtra("position", 0);
        }
        //        MusicRecever musicRecever = new MusicRecever();
        //        IntentFilter musicfilter=new IntentFilter();
        //        musicfilter.addAction("audioplayer_music");
        //        registerReceiver(musicRecever,musicfilter);
        //新的通讯方式，eventbus

    }

    private void setBtnMode(int musicmode) {
        switch (musicmode) {
            case MusicPlayerService.REPEAT_NORMAL:
                mBtnAudioPlaymode.setBackgroundResource(R.drawable.btn_audio_playmode_normal_selector);
                break;
            case MusicPlayerService.REPEAT_ALL:
                mBtnAudioPlaymode.setBackgroundResource(R.drawable.btn_audio_playmode_all_selector);
                break;
            case MusicPlayerService.REPEAT_SINGLE:
                mBtnAudioPlaymode.setBackgroundResource(R.drawable.btn_audio_playmode_single_selector);
                break;
        }
    }

    private void initView() {
        //播放音律动画 现在不需要咯，用一个开源控件
//        mIvIcon.setBackgroundResource(R.drawable.animation_list);
//        AnimationDrawable animationDrawable = (AnimationDrawable) mIvIcon.getBackground();
//        animationDrawable.start();
    }
    //改写成eventbus

    public void onEventMainThread(MediaItem mediaItem) {
        try {

            mTvName.setText(mImusicPlayerService.getName());
            mTvArtist.setText(mImusicPlayerService.getArtist());
            mSeekbarAudio.setMax(mImusicPlayerService.getDuration());
            //设置按钮状态
            int playMode = mImusicPlayerService.getPlayMode();
            //解析歌词
            setBtnMode(playMode);
            //刷新时间和进度条
            mHandler.sendEmptyMessage(PROGRESS);
            showLyric();
            //显示音律
            setupVisualizerFxAndUi();

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    private void initListener() {
        mBtnAudioNext.setOnClickListener(this);
        mBtnAudioPre.setOnClickListener(this);
        mBtnAudioPlaymode.setOnClickListener(this);
        mBtnAudioStartPause.setOnClickListener(this);
        mBtnLyrc.setOnClickListener(this);
        mSeekbarAudio.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_audio_next:
                try {
                    mBtnAudioStartPause.setBackgroundResource(R.drawable.btn_audio_pause_selector);
                    mImusicPlayerService.next();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_audio_pre:
                try {
                    mBtnAudioStartPause.setBackgroundResource(R.drawable.btn_audio_pause_selector);
                    mImusicPlayerService.pre();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_audio_playmode:
                setPlayMode();
                break;
            case R.id.btn_audio_start_pause://开始暂停
                try {
                    if (mImusicPlayerService.isPlaying()) {
                        mImusicPlayerService.pause();
                        mBtnAudioStartPause.setBackgroundResource(R.drawable.btn_audio_start_selector);

                    } else {
                        mBtnAudioStartPause.setBackgroundResource(R.drawable.btn_audio_pause_selector);
                        mImusicPlayerService.start();
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_lyrc:
                break;
        }
    }

    private void setPlayMode() {

        try {
            int playMode = mImusicPlayerService.getPlayMode();
            //放入缓存
            CacheUtils.putInt(this, "musicmode", playMode);
            switch (playMode) {
                case MusicPlayerService.REPEAT_NORMAL:
                    mImusicPlayerService.setPlayMode(MusicPlayerService.REPEAT_ALL);
                    mBtnAudioPlaymode.setBackgroundResource(R.drawable.btn_audio_playmode_all_selector);
                    Toast.makeText(this, "列表循环", Toast.LENGTH_SHORT).show();
                    break;
                case MusicPlayerService.REPEAT_ALL:
                    mImusicPlayerService.setPlayMode(MusicPlayerService.REPEAT_SINGLE);
                    mBtnAudioPlaymode.setBackgroundResource(R.drawable.btn_audio_playmode_single_selector);
                    Toast.makeText(this, "单曲循环", Toast.LENGTH_SHORT).show();
                    break;
                case MusicPlayerService.REPEAT_SINGLE:
                    mImusicPlayerService.setPlayMode(MusicPlayerService.REPEAT_NORMAL);
                    mBtnAudioPlaymode.setBackgroundResource(R.drawable.btn_audio_playmode_normal_selector);
                    Toast.makeText(this, "顺序播放", Toast.LENGTH_SHORT).show();
                    break;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        //广播也要置为空，自己暂时还没有写
        if (mConn != null) {
            unbindService(mConn);
        }
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing() && mImusicPlayerService != null)
        {
            mVisualizer.release();
            try {
                mImusicPlayerService.pause();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        try {
            //只有用户自己的点击才生效，这句话太重要了
            if (fromUser) {
                mImusicPlayerService.seekTo(progress);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    //    class MusicRecever extends BroadcastReceiver{
    //
    //        @Override
    //        public void onReceive(Context context, Intent intent) {
    //            showData(new MediaItem());
    //        }
    //    }
    //解析歌词我
    private void showLyric() {
        LyricUtils lyricUtils = new LyricUtils();
        try {
            String audioPath = mImusicPlayerService.getAudioPath();
            //我们默认歌词和歌曲在一个文件夹下，并且歌词的格式为txt或者lrc格式
            audioPath = audioPath.substring(0, audioPath.lastIndexOf("."));
            File file = new File(audioPath + ".txt");
            if (!file.exists()) {
                file = new File(audioPath + ".lrc");
            }
            lyricUtils.readLyricFile(file);
            mActiviyAudioTvLyric.setLyrics(lyricUtils.getLyrics());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        //只有存在歌词我们才会发生
        if (lyricUtils.isExistsLyric()){
            mHandler.sendEmptyMessage(SHOW_LYRIC);
        }
    }

    private void setupVisualizerFxAndUi( )
    {

        int audioSessionid = 0;
        try {
            audioSessionid = mImusicPlayerService.getAudioSessionId();
            System.out.println("audioSessionid=="+audioSessionid);
            mVisualizer = new Visualizer(audioSessionid);
            // 参数内必须是2的位数
            mVisualizer.setCaptureSize(Visualizer.getCaptureSizeRange()[1]);
            // 设置允许波形表示，并且捕获它
            mBaseVisualizerView.setVisualizer(mVisualizer);
            mVisualizer.setEnabled(true);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }


}
