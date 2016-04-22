package com.paxw.gpstest;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class mainActivity extends Activity {
    private ViewPager viewPager;
    private IndicatorView indicatorView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new GuidePagerAdapter());
        indicatorView = (IndicatorView) findViewById(R.id.indicatorView);
//        indicatorView.setIndicatorStyle(0xffffff,0x);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                indicatorView.setPointloc(position+positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
                indicatorView.setPointloc(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
    class GuidePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // 向ViewPager中添加一个ImageView
            ImageView iv = new ImageView(mainActivity.this);
            if (position%2==0)
                iv.setBackgroundColor(Color.GRAY);
            else
                iv.setBackgroundColor(Color.BLUE);
            container.addView(iv);

            // 把添加的ImageView返回回去
            return iv;
        }
    }




}
