package com.demo.android.baseadapterex;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * Created by 58_004 on 2017/11/9.
 */

public class MyAdapter extends BaseAdapter {
    public Context context;
    public boolean check1[];
    ArrayList<String> pic_url ;
    ArrayList<String> name ;

    public  MyAdapter(Context context,ArrayList<String> name  , ArrayList<String> pic_url) {
        this.context = context;
        this.name = name;
        this.pic_url=pic_url;
        check1 = new boolean[name.size()];
    }

    @Override
    public int getCount() {

        return pic_url.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.myitem, null);
        }

            TextView tv = (TextView) convertView.findViewById(R.id.textView);
            Button btn = (Button) convertView.findViewById(R.id.button);
            ImageView img = (ImageView) convertView.findViewById(R.id.imageView);
            CheckBox cb = (CheckBox) convertView.findViewById(R.id.checkBox);

            cb.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    check1[position] = isChecked;
                }
            });
            cb.setChecked(check1[position]);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(context, str[position], Toast.LENGTH_SHORT).show();
                }
            });
            tv.setText("This is No " + name.get(position));

        Picasso.with(context).load(pic_url.get(position)).resize(50, 50).centerCrop().into(img);
        return convertView;
    }
}
