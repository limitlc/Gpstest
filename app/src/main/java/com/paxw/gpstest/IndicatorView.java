package com.paxw.gpstest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by lichuang on 2016/1/25.
 */
public class IndicatorView extends RelativeLayout{
    /**
     * 点的半径
     */
    private int pointR;
    /**
     * 点的颜色
     */
    private int pointColor;
    /**
     * 选中的颜色
     */
    private int pointColorS;
    /**
     * 左侧的margin
     */
    private int leftMargin;
    private int pointSize = 4;
    private LinearLayout staticlayout;
    private Circle sView;

    public IndicatorView(Context context) {
        super(context);
    }


    private void init() {
        this.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, pointR));
        staticlayout = new LinearLayout(this.getContext());
        staticlayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        staticlayout.setOrientation(LinearLayout.HORIZONTAL);
        //设置默认的颜色;
        //动态的view
        sView = new Circle(this.getContext(),pointColorS,pointR);
        sView.setLayoutParams(new LayoutParams(pointR,pointR));
        //把默认的静态的点添加进去
       for (int i = 0; i < pointSize;i++){
           Circle view = new Circle(this.getContext(),pointColor,pointR);
           LinearLayout.LayoutParams params = new  LinearLayout.LayoutParams(pointR,pointR);
           if(i != 0)
               params.leftMargin = pointR;
           view.setLayoutParams(params);
           staticlayout.addView(view);
       }
        this.addView(staticlayout);
        this.addView(sView);

    }

    public IndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     *
     * @param defaultColor  默认点的颜色
     * @param selectColor   选择的点的颜色
     * @param diameter      点的直径值为dip
     */
    public void setIndicatorStyle(int defaultColor , int selectColor ,int diameter ){
        this.pointColor = defaultColor;
        this.pointColorS = selectColor;
        pointR= dip2px(this.getContext(),diameter);

    }







    /**
     * 传入滑动的比例来画位置
     *
     * @param loc
     */
    public void setPointloc(float loc){
        RelativeLayout.LayoutParams params = new  RelativeLayout.LayoutParams(pointR,pointR);
        params.leftMargin = (int) (loc*leftMargin*2);
        sView.setLayoutParams(params);

    }
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    class Circle extends View {
        private int dx;
        private Paint paint;
        public Circle(Context context,int color,int dx) {
            super(context);
            paint = new Paint();
            paint.setColor(color);
            paint.setAntiAlias(true);
            this.dx = dx;
        }
        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawCircle(dx*0.5F,dx*0.5F,dx*0.5f,paint);
        }
    }

}
