package com.example.surbhimiglani.myartgallery.ViewList;

import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;

import com.example.surbhimiglani.myartgallery.ViewList.MyLongClickListener;
import com.example.surbhimiglani.myartgallery.R;

/**
 * Created by Surbhi Miglani on 17-06-2017.
 */

public class lvHolder implements View.OnLongClickListener,View.OnCreateContextMenuListener {

    TextView nameTxt;
    MyLongClickListener longClickListener;

    public lvHolder(View v) {
        nameTxt = (TextView) v.findViewById(R.id.nameTxt);

        v.setOnLongClickListener(this);
        v.setOnCreateContextMenuListener(this);

    }

    @Override
    public boolean onLongClick(View v) {
        this.longClickListener.onItemLongClick();
        return false;
    }

    public void setLongClickListener(MyLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo contextMenuInfo) {

        menu.setHeaderTitle("Action : ");
        menu.add(0, 0, 0, "NEW");
        menu.add(0,1,0,"EDIT");
        menu.add(0,2,0,"DELETE");


    }
}
