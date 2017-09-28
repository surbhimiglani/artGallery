package com.example.surbhimiglani.myartgallery;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Surbhi Miglani on 27-04-2017.
 */

public class IMadapter extends ArrayAdapter<BUG> {
    private final int THUMBSIZE=50;

    private static class ViewHolder{
        ImageView imageView3;
        TextView textView6;
    }

    public IMadapter(Context context, List<BUG> objects) {
        super(context, 0 , objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.icon, parent, false);
            viewHolder.imageView3=(ImageView) convertView.findViewById(R.id.imageView3);
            viewHolder.textView6=(TextView) convertView.findViewById(R.id.textView6);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        BUG image=getItem(position);
        viewHolder.textView6.setText(image.getTitle());
        viewHolder.imageView3.setImageBitmap(ThumbnailUtils
                .extractThumbnail(BitmapFactory.decodeFile(image.getPath()),
                        THUMBSIZE, THUMBSIZE));
        return convertView;
    }
}

