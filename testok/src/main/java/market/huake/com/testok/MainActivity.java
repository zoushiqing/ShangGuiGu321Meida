package market.huake.com.testok;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_get;
    private Button btn_post;
    private TextView tv_result;
    private Button btn_json;
    private ProgressButton mViewById;
    private ProgressButton mDownButton;
    private Button mBt_circle;
    private CircleProgressView mView;


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    System.out.println("收到啦");
                    String obj = (String) msg.obj;
                    System.out.println(obj);
                    tv_result.setText(obj);
                    break;
                default:
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化ok
        init();
        initView();
        initCircle();



    }

    private void initCircle() {
        mView.setIcon(R.drawable.ic_pause);
        mView.setCurrentProgress(0);
        mView.setEnable(true);
        mView.setNote("下载");
        mView.setMax(360);
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("进来啦");
                new AsyncTask<Void, Integer, Void>() {
                    int len=0;
                    @Override
                    protected Void doInBackground(Void... params) {

                        while (true){
                            SystemClock.sleep(100);
                            len++;
                            System.out.println("增加"+len);
                            publishProgress(len);
                            if (len>360){
                                break;
                            }
                        }
                        return null;
                    }

                    @Override
                    protected void onProgressUpdate(Integer... values) {
                        Integer value = values[0];
                        mView.setCurrentProgress(value);
                        int a= (int) (value*100f/360);
                        mView.setNote(a+"%");
                        super.onProgressUpdate(values);
                    }
                }.execute();
            }
        });
    }


    private void initView() {
        btn_get = (Button) findViewById(R.id.btn_get);
        btn_post = (Button) findViewById(R.id.btn_post);
        btn_json = (Button) findViewById(R.id.btn_json);
        mDownButton = (ProgressButton) findViewById(R.id.progressButton1);
        tv_result = (TextView) findViewById(R.id.tv_result);
        mBt_circle = (Button) findViewById(R.id.bt_circle);
        mView = (CircleProgressView) findViewById(R.id.circle_progress_bt);
        mViewById = (ProgressButton) findViewById(R.id.progressButton);
        //设置点击事件
        btn_get.setOnClickListener(this);
        btn_post.setOnClickListener(this);
        btn_json.setOnClickListener(this);
        mDownButton.setOnClickListener(this);
        mBt_circle.setOnClickListener(this);
        mDownButton.setIsProgress(true);
        mViewById.setMaxProgress(100);
        mViewById.setIsProgress(true);
        mViewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Down() {
                }.execute();
            }
        });
    }

    private void init() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }

    class Down extends AsyncTask<Void, Integer, Void> {

        int progress;

        @Override
        protected Void doInBackground(Void... params) {

            //第二次秒用while循环
            while (true) {
                SystemClock.sleep(100);
                progress++;
                publishProgress(progress);

                if (progress >= 100) {
                    break;
                }
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Integer value = values[0];
            mView.setCurrentProgress(value);
            mView.setNote(progress + "%");
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_get:
                getDataFromByGet();
                break;
            case R.id.btn_post:
                getDataFromByPost();
                break;
            case R.id.bt_circle:
                mView.setEnable(true);
                mView.setNote("下载");
                mView.setMax(360);
                mView.setIcon(R.drawable.ic_download);
                new Down().execute();
                break;
            case R.id.btn_json:
                parseJson();
                break;
            case R.id.progressButton1:

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        download1();
                    }
                }).start();
                break;
            default:
                break;

        }
    }


    private void downLoad() {


        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //创建一个Request
        final Request request = new Request.Builder()
                .url(Constants.downUrl2)
                .build();
        //new call
        Call call = mOkHttpClient.newCall(request);
        //请求加入调度
        try {
            Response response = call.execute();

//            StringBuffer stringBuffer = new StringBuffer();
            String path = getCacheDir().getAbsolutePath();
//            stringBuffer.append(File.separator);

//            File downFile = new File(path);
//            if (!downFile.exists()||!downFile.isDirectory()){
//                downFile.mkdirs();
//            }
//            String downpath=downFile.getAbsolutePath();

            File file = new File(path, "heima.apk");

            InputStream inputStream = response.body().byteStream();
            FileOutputStream out = null;
            out = new FileOutputStream(file, true);


            byte[] buffer = new byte[1024];
            int len = -1;

            while ((len = inputStream.read(buffer)) != -1) {
                out.write(buffer, 0, len);
                System.out.println("当前大小"+len);
            }
            inputStream.close();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("错了");
        }

       /* call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {

                String cacheDir = getCacheDir().getAbsolutePath() + "/";
                InputStream inputStream = response.body().byteStream();
                String string = response.body().string();
                tv_result.setText(string);
                FileOutputStream out = null;
                File saveFile = new File(cacheDir);
                if (!saveFile.exists() || saveFile.isDirectory()) {
                    saveFile.mkdir();
                }
                out = new FileOutputStream(saveFile, true);

                byte[] bytes = new byte[1024];
                int len = -1;
                while ((len = inputStream.read(bytes)) != -1) {
                    out.write(bytes, 0, len);
                    System.out.println("已经下载" + len);
                }
                out.close();
                inputStream.close();
            }
        });*/
    }

    private void download1() {
        OkHttpUtils//
                .get()//
                .url(Constants.downUrl2)//
                .build()//
                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), "haha.apk")//
                {

                    @Override
                    public void onBefore(Request request, int id) {
                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {

                        System.out.println(progress);
                        mDownButton.setMaxProgress(total);
                        mDownButton.setCurrProgress((long) progress);
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        System.out.println("下载失败");
                    }

                    @Override
                    public void onResponse(File file, int id) {

                        System.out.println("下载成功" + file.getAbsolutePath());
                    }
                });

    }

    private void parseJson() {
        //        final HomeBean[] homeBean = new HomeBean[1];
        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //创建一个Request
        final Request request = new Request.Builder()
                .url(Constants.homeyoucan)
                .build();
        //new call
        Call call = mOkHttpClient.newCall(request);
        //请求加入调度
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //                String jsonvalue = response.body().string();
                //                System.out.println(jsonvalue);
                //                Gson gson = new Gson();
                //                HomeBean homeBean = gson.fromJson(jsonvalue, HomeBean.class);
                //                //                String s = homeBean.getPicture().get(0);
                //                //                System.out.println("猜猜是啥"+s);
                tv_result.setText(response.body().string());

            }
        });
    }

    private void getDataFromByPost() {
        OkHttpUtils
                .post()
                .url(Constants.homeyoucan)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        System.out.println("访问失败");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        tv_result.setText(response);
                    }
                });
    }

    private void getDataFromByGet() {

        OkHttpUtils
                .get()
                .url(Constants.homewucan).addParams("index", "0")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        System.out.println("访问失败");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        System.out.println("为啥不对啊");
                        System.out.println(response);
                        tv_result.setText(response);
                    }


                });
    }

}
