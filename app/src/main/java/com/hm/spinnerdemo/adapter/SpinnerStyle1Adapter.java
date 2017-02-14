package com.hm.spinnerdemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hm.spinnerdemo.R;
import com.hm.spinnerdemo.model.Book;

import java.util.List;

/**
 * Created by dumingwei on 2017/2/14.
 */
public class SpinnerStyle1Adapter extends BaseAdapter {

    private List<Book> bookList;
    private Context context;

    public SpinnerStyle1Adapter(Context context, List<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
    }

    @Override
    public int getCount() {

        return bookList.size();
    }

    @Override
    public Object getItem(int position) {
        return bookList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Book book = bookList.get(position);
        View view;
        Holder holder;
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_style_1, parent, false);
            holder = new Holder();
            holder.textView = (TextView) view.findViewById(R.id.text_view);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (Holder) view.getTag();
        }
        holder.textView.setText(book.getName());

        return view;
    }

    static class Holder {

        private TextView textView;
    }
}
