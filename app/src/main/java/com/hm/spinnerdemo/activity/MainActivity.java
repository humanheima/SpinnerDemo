package com.hm.spinnerdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.hm.spinnerdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_spinner)
    Button btnSpinner;
    @BindView(R.id.btn_popwindow)
    Button btnPopwindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_spinner, R.id.btn_popwindow})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_spinner:
                SpinnerActivity.launch(this);
                break;
            case R.id.btn_popwindow:
                PopWindowActivity.launch(this);
                break;
            default:
                break;
        }
    }
}
