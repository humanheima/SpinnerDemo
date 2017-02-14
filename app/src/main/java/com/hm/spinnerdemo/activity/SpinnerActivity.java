package com.hm.spinnerdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hm.spinnerdemo.R;
import com.hm.spinnerdemo.adapter.SpinnerStyle1Adapter;
import com.hm.spinnerdemo.model.Book;
import com.hm.spinnerdemo.widget.SpinnerPopWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpinnerActivity extends AppCompatActivity {

    @BindView(R.id.spinner_default)
    Spinner spinnerDefault;
    @BindView(R.id.spinner_style1)
    Spinner spinnerStyle1;
    @BindView(R.id.text_pop)
    TextView textPop;

    private SpinnerStyle1Adapter adapter;
    private SpinnerPopWindow spinnerPopWindow;
    private List<Book> bookList;

    public static void launch(Context context) {
        Intent intent = new Intent(context, SpinnerActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);
        ButterKnife.bind(this);
        defaultStyle();
        initData();
        style1();
        setTextImage(R.drawable.icon_down);
        textPop.setOnClickListener(clickListener);
        spinnerPopWindow = new SpinnerPopWindow(this, bookList, itemClickListener);
        spinnerPopWindow.setOnDismissListener(dismissListener);
    }

    private void initData() {
        bookList = new ArrayList<>();
        bookList.add(new Book(15.0, "c 语言"));
        bookList.add(new Book(15.0, "c ++"));
        bookList.add(new Book(15.0, "java"));
        bookList.add(new Book(15.0, "python"));
        bookList.add(new Book(15.0, "kotlin"));
    }


    private void setTextImage(int resId) {
        Drawable drawable = getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textPop.setCompoundDrawables(null, null, drawable, null);
    }

    private void defaultStyle() {
        spinnerDefault.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] languages = getResources().getStringArray(R.array.languages);
                Toast.makeText(SpinnerActivity.this, "你点击的是:" + languages[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void style1() {

        adapter = new SpinnerStyle1Adapter(this, bookList);
        spinnerStyle1.setAdapter(adapter);
        spinnerStyle1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              /*  String[] languages = getResources().getStringArray(R.array.languages);
                Toast.makeText(MainActivity.this, "你点击的是:" + languages[position], Toast.LENGTH_SHORT).show();*/
                Toast.makeText(SpinnerActivity.this, "你点击的是:" + bookList.get(position).getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.text_pop:
                    spinnerPopWindow.setWidth(textPop.getWidth());
                    spinnerPopWindow.showAsDropDown(textPop);
                    setTextImage(R.drawable.icon_up);
                    break;
                default:
                    break;
            }
        }
    };

    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            spinnerPopWindow.dismiss();
            textPop.setText(bookList.get(position).getName());
            Toast.makeText(SpinnerActivity.this, "点击了:" + bookList.get(position).getName(), Toast.LENGTH_SHORT).show();
        }
    };

    private PopupWindow.OnDismissListener dismissListener = new PopupWindow.OnDismissListener() {
        @Override
        public void onDismiss() {
            setTextImage(R.drawable.icon_down);
        }
    };
}
