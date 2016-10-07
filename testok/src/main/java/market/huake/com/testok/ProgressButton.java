package market.huake.com.testok;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.widget.Button;

/*
 * @创建者     兰昱
 * @创建时间  2016/9/12 19:10
 * @描述	      用来显示进度条的主题
 */
public class ProgressButton extends Button {

    //对于进度条，需要知道最大值，进度值
    private long maxProgress;
    private long currProgress;
    //设置是否使用进度条功能
    private boolean isProgress;

    public ProgressButton(Context context) {
        super(context);
    }

    public ProgressButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setMaxProgress(long maxProgress) {
        this.maxProgress = maxProgress;
    }

    public void setCurrProgress(long currProgress) {
        this.currProgress = currProgress;
        //每当设置完进程后，当然要重绘制呀
        invalidate();
    }

    public void setIsProgress(boolean progress) {
        isProgress = progress;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (isProgress) {
            ColorDrawable colorDrawable = new ColorDrawable(Color.BLUE);
            int  right= (int) ((currProgress*1.0f/maxProgress)*getMeasuredWidth()+0.5f);
            colorDrawable.setBounds(0,0,right,getBottom());
            colorDrawable.draw(canvas);
        }

        super.onDraw(canvas);

    }
}
