package com.zh.loveme.widget;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.animation.Animation;
import android.widget.TextView;

/**
 * @Description: 类说明，描述
 * @Author: zh浩
 * @CreateDate: AnimTextView$ 17:40$
 * @Version: 1.0
 */
public class AnimTextView  extends WrapLayout{
    private Context mContext;
    public AnimTextView(Context context) {
        super(context);
        mContext = context;

    }

    public AnimTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }
    public void setText(String text, final Animation animation, int duration) {
        setGravity(Gravity.LEFT);
        setVerticalSpacing(30);
        int time = 0;
        if (text != null && !text.isEmpty()) {
            char[] characters = text.toCharArray();
            for (char c : characters) {
                final TextView t = new TextView(mContext);
                t.setTextColor(Color.BLACK);
                //遍历传入的字符串的每个字符，生成一个TextView，并设置它的动画
                t.setText(String.valueOf(c));
                t.setTextSize(24);
                Handler h = new Handler();
                //每隔duration时间，播放下一个TextView的动画
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        addView(t);
                        Log.e("Text", "setText: "+t.getText().toString() );
                        t.setAnimation(animation);
                    }
                }, time);
                time += duration;
            }
        }
    }

}
