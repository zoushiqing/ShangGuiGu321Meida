package market.huake.com.testok;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/*
 * @创建者     兰昱
 * @创建时间  2016/9/15 15:46
 * @描述	      自定义的下载进度条，继承线性布局，why?
 */
public class CircleProgressView extends LinearLayout {

    @InjectView(R.id.circleProgressView_iv_icon)
    ImageView mCircleProgressViewIvIcon;
    @InjectView(R.id.circleProgressView_tv_note)
    TextView mCircleProgressViewTvNote;
    //与水平布局类似
    public long max=100;
    public long currentProgress;
    public  boolean isEnable=false;

    public void setMax(long max) {
        this.max = max;
    }

    public void setCurrentProgress(long currentProgress) {
        this.currentProgress = currentProgress;
        invalidate();
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }
    //线性布局还有设置图标和文字
    public void setIcon(int resid){
        mCircleProgressViewIvIcon.setImageResource(resid);
    }
    public void setNote(String content){
        mCircleProgressViewTvNote.setText(content);
    }

    public CircleProgressView(Context context) {
        this(context, null);
    }

    public CircleProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View inflate = View.inflate(context, R.layout.inflate_circleprogressview, this);
        ButterKnife.inject(this, inflate);
    }

    //操作是通过分发，好奇怪
    //另外一种绘制？

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

        //画笔属性
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        paint.setAntiAlias(true);// 消除锯齿
        //画一个椭圆
        RectF rectF = new RectF(mCircleProgressViewIvIcon.getLeft(),mCircleProgressViewIvIcon.getTop()
                ,mCircleProgressViewIvIcon.getRight(),mCircleProgressViewIvIcon.getBottom());

        float sweep= currentProgress*360.f/max;
        canvas.drawArc(rectF,-90,sweep,false,paint);
    }
}
