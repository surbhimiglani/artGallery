package com.example.surbhimiglani.myartgallery.ViewList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.example.surbhimiglani.myartgallery.A1;
import com.example.surbhimiglani.myartgallery.A2;
import com.example.surbhimiglani.myartgallery.A3;
import com.example.surbhimiglani.myartgallery.A4;
import com.example.surbhimiglani.myartgallery.A5;
import com.example.surbhimiglani.myartgallery.Layout_0;
import com.example.surbhimiglani.myartgallery.R;
import com.example.surbhimiglani.myartgallery.layout2;
import com.example.surbhimiglani.myartgallery.listTypes.listTypes;
import java.util.ArrayList;



public class lvcustomAdapter extends BaseAdapter {

    Context c;
    ArrayList<listTypes> lvtypes;
    LayoutInflater inflater;
    listTypes lt;
    public lvcustomAdapter(Context c,ArrayList<listTypes> lvtypes){
        this.c=c;
        this.lvtypes=lvtypes;

    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (inflater == null) {
            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {

            convertView = inflater.inflate(R.layout.model, parent, false);

        }

        lvHolder hold = new lvHolder(convertView);
        hold.nameTxt.setText(lvtypes.get(position).getName());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int itemposition = position;
                getItem(position);
                switch (itemposition) {
                    case 0:
                        Intent b = new Intent(c, Layout_0.class);
                        b.putExtra("ARTtypes", getItem(position).toString());
                        c.startActivity(b);
                        break;

                    case 1:
                        Intent a1 = new Intent(c, A1.class);
                        a1.putExtra("ARTtypes", getItem(position).toString());
                        c.startActivity(a1);
                        break;
                    case 2:
                        Intent a2 = new Intent(c, A2.class);
                        a2.putExtra("ARTtypes", getItem(position).toString());
                        c.startActivity(a2);
                        break;
                    case 3:
                        Intent a3 = new Intent(c , A3.class);
                        a3.putExtra("ARTtypes", getItem(position).toString());
                        c.startActivity(a3);
                        break;
                    case 4:
                        Intent a4 = new Intent(c , A4.class);
                        a4.putExtra("ARTtypes", getItem(position).toString());
                        c.startActivity(a4);
                        break;
                    case 5:
                        Intent a5 = new Intent(c , A5.class);
                        a5.putExtra("ARTtypes", getItem(position).toString());
                        c.startActivity(a5);
                        break;
                    case 6:
                        Toast.makeText(c , "Sorry u can add only 5 types", Toast.LENGTH_LONG).show();
                }
            }
        });
        hold.setLongClickListener(new MyLongClickListener() {
            @Override
            public void onItemLongClick() {
                lt = (listTypes) getItem(position);


            }
        });
        return convertView;
    }


    public int getSelectedItemID()
    {
        return lt.getId();
    }

    public String getSelectedItemName()
    {
        return lt.getName();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return lvtypes.get(position);
    }

    @Override
    public int getCount() {
        return lvtypes.size();
    }

}
