package com.hm.spinnerdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hm.spinnerdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PopWindowActivity extends AppCompatActivity {

    //打开关闭弹出框
    protected final int OPEN_POP = 0;
    protected final int HIDE_POP = 1;

    @BindView(R.id.btn_show_pop)
    Button btnShowPop;
    private MyPopWindow popWindow;
    private TextView textAlbum;
    private TextView textTakePhoto;
    private TextView textCancel;

    public static void launch(Context context) {
        Intent intent = new Intent(context, PopWindowActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_window);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_show_pop, R.id.btn_show_pop_bottom})
    public void onClick(View view) {
        initPop();
        switch (view.getId()) {
            case R.id.btn_show_pop:
                popWindow.showAsDropDown(btnShowPop);
                break;
            case R.id.btn_show_pop_bottom:
                popWindow.showAtLocation(btnShowPop, Gravity.BOTTOM, 0, 0);
                break;
            default:
                break;
        }

    }

    private void initPop() {
        if (popWindow == null) {
            View convertView = LayoutInflater.from(this).inflate(R.layout.pop_select_image, null);
            textAlbum = (TextView) convertView.findViewById(R.id.text_album);
            textTakePhoto = (TextView) convertView.findViewById(R.id.text_take_photo);
            textCancel = (TextView) convertView.findViewById(R.id.text_cancel);
            textCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popWindow.dismiss();
                }
            });
            popWindow = new MyPopWindow(convertView,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT, true);
            popWindow.setAnimationStyle(R.style.Pop_anim);
            popWindow.setFocusable(true);
            popWindow.setBackgroundDrawable(new ColorDrawable());
        }
    }

    class MyPopWindow extends PopupWindow {

        public MyPopWindow(View contentView, int width, int height, boolean focusable) {
            super(contentView, width, height, focusable);
        }

        @Override
        public void showAsDropDown(View anchor) {
            setWindow(OPEN_POP);
            super.showAsDropDown(anchor);
        }

        @Override
        public void showAtLocation(View parent, int gravity, int x, int y) {
            setWindow(OPEN_POP);
            super.showAtLocation(parent, gravity, x, y);
        }

        @Override
        public void dismiss() {
            setWindow(HIDE_POP);
            super.dismiss();
        }
    }

    public void setWindow(int type) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        if (type == OPEN_POP) {
            lp.alpha = 0.7f;
        } else {
            lp.alpha = 1.0f;
        }
        getWindow().setAttributes(lp);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

}
