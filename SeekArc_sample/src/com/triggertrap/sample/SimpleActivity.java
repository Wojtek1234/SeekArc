/*******************************************************************************
 * The MIT License (MIT)
 *
 * Copyright (c) 2013 Triggertrap Ltd
 * Author Neil Davies
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 ******************************************************************************/
package com.triggertrap.sample;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.SeekBar;
import android.widget.TextView;

import com.triggertrap.seekarc.SeekArc;
import com.triggertrap.seekarc.SeekArc.OnSeekArcChangeListener;

/**
 * SimpleActivity.java
 *
 * @author Neil Davies
 */
public class SimpleActivity extends Activity {

    private SeekArc mSeekArc;
    private SeekBar animDuration;

    private TextView mSeekArcProgress;

    protected int getLayoutFile() {
        return R.layout.holo_sample;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutFile());

        mSeekArc = (SeekArc) findViewById(R.id.seekArc);
        mSeekArcProgress = (TextView) findViewById(R.id.seekArcProgress);
        animDuration = (SeekBar) findViewById(R.id.animDuration);


        mSeekArc.setOnSeekArcChangeListener(new OnSeekArcChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekArc seekArc) {
            }

            @Override
            public void onStartTrackingTouch(SeekArc seekArc) {
            }

            @Override
            public void onProgressChanged(SeekArc seekArc, int progress,
                                          boolean fromUser) {
                mSeekArcProgress.setText(String.valueOf(progress));
            }
        });


        findViewById(R.id.aniamte).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 100);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        mSeekArc.setProgress((int) animation.getAnimatedValue());
                    }
                });
                valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                valueAnimator.setDuration(animDuration.getProgress());
                valueAnimator.start();
            }
        });

    }

}
