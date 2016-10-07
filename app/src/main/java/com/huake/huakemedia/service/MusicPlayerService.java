package com.huake.huakemedia.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.huake.huakemedia.IMusicPlayerService;
import com.huake.huakemedia.R;
import com.huake.huakemedia.activity.AudioPlayerActivity;
import com.huake.huakemedia.domain.MediaItem;
import com.huake.huakemedia.utils.CacheUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;


/*
 * @创建者     兰昱
 * @创建时间  2016/10/3 18:16
 * @描述	      
 */
public class MusicPlayerService extends Service {

    private int mPosition;
    private List<MediaItem> mMediaItems = new ArrayList<MediaItem>();
    private MediaPlayer mMediaPlayer;
    private MediaItem mMediaItem;
    //四种播放模式
    public static final int REPEAT_NORMAL = 1;
    public static final int REPEAT_SINGLE = 2;
    public static final int REPEAT_ALL = 3;
    private int mPLaymode = REPEAT_NORMAL;
    private int mDuration;
    private NotificationManager mNotifiManger;

    @Override
    public void onCreate() {
        super.onCreate();
        //拿取本地数据
        getDataFromLocal();
        //拿到储存模式
        mPLaymode = CacheUtils.getInt(this, "musicmode");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        //一定要返回自己binder
        return mStub;
    }

    private void getDataFromLocal() {
        //服务默认是在主线程中进行，所以耗时操作应该放到子线程里去
        new Thread(new Runnable() {
            @Override
            public void run() {

                ContentResolver contentResolver = getContentResolver();
                Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                String[] objs = {
                        MediaStore.Audio.Media.DISPLAY_NAME,//视频文件在sdcard的名称
                        MediaStore.Audio.Media.DURATION,//视频总时长
                        MediaStore.Audio.Media.SIZE,//视频的文件大小
                        MediaStore.Audio.Media.DATA,//视频的绝对地址
                        MediaStore.Audio.Media.ARTIST,//歌曲的演唱者

                };
                Cursor cursor = contentResolver.query(uri, objs, null, null, null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        MediaItem mediaItem = new MediaItem();
                        String name = cursor.getString(0);//视频的名称
                        mediaItem.setName(name);

                        long duration = cursor.getLong(1);//视频的时长
                        mediaItem.setDuration(duration);

                        long size = cursor.getLong(2);//视频的文件大小
                        mediaItem.setSize(size);

                        String data = cursor.getString(3);//视频的播放地址
                        mediaItem.setData(data);

                        String artist = cursor.getString(4);//艺术家
                        mediaItem.setArtist(artist);

                        mMediaItems.add(mediaItem);//写在上面
                    }
                    cursor.close();
                }
            }
        }).start();

    }


    /**
     * 根据位置打开对应的音频文件,并且播放
     */
    private void openAudio(int position) {
        mPosition = position;
        //打开文件之后就要开始播放了
        if (mMediaItems != null && mMediaItems.size() > 0) {
            mMediaItem = mMediaItems.get(position);
            //启动系统的播放器吧
            if (mMediaPlayer != null) {
                //                mMediaPlayer.release();//两个方法重复啊
                mMediaPlayer.reset();
                mMediaPlayer = null;
            }

            mMediaPlayer = new MediaPlayer();
            //设置各种监听
            mMediaPlayer.setOnPreparedListener(new MyOnPreparedListener());
            mMediaPlayer.setOnCompletionListener(new MyOnCompletionListener());
            mMediaPlayer.setOnErrorListener(new MyOnErrorListener());
            //开始设置资源播放
            try {
                mMediaPlayer.setDataSource(mMediaItem.getData());
                //准备
                mMediaPlayer.prepareAsync();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            Toast.makeText(this, "no data baby", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 播放音乐
     */
    private void start() {
        mMediaPlayer.start();
        //播放的时候就可以显示通知栏了
        //看来只有这个版本以上才支持喔
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {

            mNotifiManger = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            //这个意图表示点击后调回音乐播放
            Intent intent = new Intent(this, AudioPlayerActivity.class);
            //解决bug冲突
            intent.putExtra("notification", true);//标识来自状态拦
            PendingIntent pending = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            //pendingintent表示携带数据的意图
            Notification notification = new Notification.Builder(this)
                    .setSmallIcon(R.drawable.notification_music_playing)
                    .setContentTitle("华科音乐")
                    .setContentText("正在播放" + getName())
                    .setContentIntent(pending)
                    .build();
            mNotifiManger.notify(1, notification);
        }
    }

    /**
     * 播暂停音乐
     */
    private void pause() {
        mMediaPlayer.pause();
        mNotifiManger.cancel(1);
    }

    /**
     * 停止
     */
    private void stop() {
        mMediaPlayer.stop();
    }

    /**
     * 得到当前的播放进度
     *
     * @return
     */
    private int getCurrentPosition() {

        return mMediaPlayer.getCurrentPosition();
    }


    /**
     * 得到当前音频的总时长
     *
     * @RETURN
     */
    private int getDuration() {
        if (mMediaPlayer != null) {
            return mMediaPlayer.getDuration();
        } else {
            return 0;
        }
        //            Toast.makeText(this, "为啥为空", Toast.LENGTH_SHORT).show();
    }

    /**
     * 得到歌曲名字
     *
     * @return
     */
    private String getName() {
        return mMediaItem.getName();
    }

    /**
     * 控制进度条
     *
     * @return
     */
    private void seekTo(int position) {
        mMediaPlayer.seekTo(position);

    }

    private String getArtist() {
        return mMediaItem.getArtist();
    }

    /**
     * 得到歌曲播放的路径
     *
     * @return
     */
    private String getAudioPath() {
        return mMediaItem.getData();
    }

    /**
     * 播放下一个视频
     */
    private void next() {
        mPosition++;
        if (mPosition > mMediaItems.size() - 1) {
            mPosition = 0;
        }
        openAudio(mPosition);
    }


    /**
     * 播放上一个视频
     */
    private void pre() {
        mPosition--;
        if (mPosition < 0) {
            mPosition = mMediaItems.size() - 1;
        }
        openAudio(mPosition);
    }

    /**
     * 设置播放模式
     *
     * @param playmode
     */
    private void setPlayMode(int playmode) {
        mPLaymode = playmode;
        CacheUtils.putInt(this, "musicmode", mPLaymode);
    }

    /**
     * 得到播放模式
     *
     * @return
     */
    private int getPlayMode() {
        return mPLaymode;
    }


    /**
     * 是否在播放音频
     *
     * @return
     */
    private boolean isPlaying() {
        return mMediaPlayer.isPlaying();
    }

    class MyOnErrorListener implements MediaPlayer.OnErrorListener {

        @Override
        public boolean onError(MediaPlayer mp, int what, int extra) {
            System.out.println("erro");
            return true;
        }

    }

    class MyOnCompletionListener implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mp) {
            //根据不同的播放模式，决定不同的情况

            switch (mPLaymode) {
                case REPEAT_NORMAL:
                    mPosition++;
                    if (mPosition > mMediaItems.size() - 1) {
                        mPosition = mMediaItems.size() - 1;

                    } else {
                        openAudio(mPosition);
                    }
                    break;
                case REPEAT_SINGLE:
                    openAudio(mPosition);
                    break;
                case REPEAT_ALL:
                    mPosition++;
                    if (mPosition > mMediaItems.size() - 1) {
                        mPosition = 0;
                    }
                    openAudio(mPosition);
                    break;
            }
        }
    }

    class MyOnPreparedListener implements MediaPlayer.OnPreparedListener {


        @Override
        public void onPrepared(MediaPlayer mp) {
            //获取时长在这里最好，如果在其他地方随意获取，很可能播放器并没有创建或者不在准备状态
            //1.那么只会拿到空数据，为了让播放器在正确的时间获取，可以用广播接收者
            //            notiChange();
            //2.还可以用新框架eventbus,放错类了，好坑爹
            EventBus.getDefault().post(mMediaItem);
            start();
        }
    }
    //通知广播用的，用evenyBus代替咯
    private void notiChange() {
        Intent intent = new Intent();
        intent.setAction("audioplayer_music");
        sendBroadcast(intent);
    }



    private IMusicPlayerService.Stub mStub = new IMusicPlayerService.Stub() {
        MusicPlayerService mPlayerService = MusicPlayerService.this;

        @Override
        public void openAudio(int position) throws RemoteException {
            mPlayerService.openAudio(position);
        }

        @Override
        public void start() throws RemoteException {
            mPlayerService.start();
        }

        @Override
        public void pause() throws RemoteException {
            mPlayerService.pause();
        }

        @Override
        public void stop() throws RemoteException {
            mPlayerService.stop();
        }

        @Override
        public int getCurrentPosition() throws RemoteException {
            return mPlayerService.getCurrentPosition();
        }

        @Override
        public int getDuration() throws RemoteException {
            return mPlayerService.getDuration();
        }

        @Override
        public String getArtist() throws RemoteException {
            return mPlayerService.getArtist();
        }

        @Override
        public String getName() throws RemoteException {
            return mPlayerService.getName();
        }

        @Override
        public String getAudioPath() throws RemoteException {
            return mPlayerService.getAudioPath();
        }

        @Override
        public void next() throws RemoteException {
            mPlayerService.next();
        }

        @Override
        public void pre() throws RemoteException {
            mPlayerService.pre();
        }

        @Override
        public void setPlayMode(int playmode) throws RemoteException {
            mPlayerService.setPlayMode(playmode);
        }

        @Override
        public int getPlayMode() throws RemoteException {
            return mPlayerService.getPlayMode();
        }

        @Override
        public boolean isPlaying() throws RemoteException {
            return mPlayerService.isPlaying();
        }

        @Override
        public void seekTo(int position) throws RemoteException {
            mPlayerService.seekTo(position);
        }

        @Override
        public int getAudioSessionId() throws RemoteException {
            return mMediaPlayer.getAudioSessionId();
        }
    };

}
