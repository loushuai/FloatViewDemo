package com.example.loushuai.floatlayout;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
    private static final int    FADE_OUT = 1;

    private LinearLayout   mLayout;
    private LayoutInflater mInflate;
    private Boolean mShowing;
    private View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mInflate = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = mInflate.inflate(R.layout.activity_main, null);

        mLayout = new LinearLayout( this );
        mLayout.setOrientation(LinearLayout.VERTICAL);
        setContentView(mLayout);
        mLayout.addView(view);

        mShowing = false;

        Button btnShow = (Button) findViewById(R.id.button_show);
        btnShow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showText1();
            }
        });

        Button btnHide = (Button) findViewById(R.id.button_hide);
        btnHide.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                hideText();
            }
        });

        Button btnAutoHide = (Button) findViewById(R.id.button_autohide);
        btnAutoHide.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showTest2(3000);
            }
        });
    }

    private void showText1() {
        if (mShowing) {
            return;
        }

        mView =  mInflate.inflate(R.layout.float_layout_1, null);
        mLayout.addView(mView);

        mShowing = true;
    }

    private void hideText() {
        if (!mShowing) {
            return;
        }

        mLayout.removeView(mView);

        mShowing = false;
    }

    private void showTest2(int timeout) {
        if (mShowing) {
            return;
        }

        mView =  mInflate.inflate(R.layout.float_layout_2, null);
        mLayout.addView(mView);

        mShowing = true;

        Message msg = mHandler.obtainMessage(FADE_OUT);
        if (timeout != 0) {
            mHandler.removeMessages(FADE_OUT);
            mHandler.sendMessageDelayed(msg, timeout);
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case FADE_OUT:
                    hideText();
                    break;
            }
        }
    };
}
