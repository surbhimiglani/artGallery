package com.example.surbhimiglani.myartgallery;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.BoolRes;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;

import com.example.surbhimiglani.myartgallery.ViewList.lvcustomAdapter;
import com.example.surbhimiglani.myartgallery.listAdapter.lvDatabase;
import com.example.surbhimiglani.myartgallery.listAdapter.lvHelper;
import com.example.surbhimiglani.myartgallery.listAdapter.lvAdapter;
import com.example.surbhimiglani.myartgallery.listTypes.listTypes;

public class layout2 extends AppCompatActivity {


    EditText nameEditText;
    Button retrieveBtn, saveBtn,button;
    ArrayList<listTypes> lvtypes=new ArrayList<>();
    lvcustomAdapter adapter;
    ListView listView;
    final Boolean forUpdate=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout2);
        button=(Button) findViewById(R.id.button);
        listView = (ListView) findViewById(R.id.listView);
        adapter = new lvcustomAdapter(this, lvtypes);
        this.getListTypes();

        adapter=new lvcustomAdapter(this, lvtypes);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayDialog(false);
            }
        });
        registerForContextMenu(listView);

    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {

        CharSequence title=item.getTitle();
        if(title=="New")
        {       Log.i("New","1");
            displayDialog(!forUpdate);
            return true;
        }else  if(title=="Update")
        {
            displayDialog(forUpdate);
            return true;

        }else  if(title=="Delete")
        {
            delete();
            return true;
        }
        return super.onContextItemSelected(item);
    }

    public void displayDialog(Boolean forUpdate){
        Dialog d=new Dialog(this);
        d.setTitle("SQLite Data");
        d.setContentView(R.layout.dialog_layout);
        listView=(ListView) findViewById(R.id.listView);
        registerForContextMenu(listView);
        nameEditText= (EditText) d.findViewById(R.id.nameEditTxt);
        saveBtn= (Button) d.findViewById(R.id.saveBtn);
        retrieveBtn= (Button) d.findViewById(R.id.retrieveBtn);

        if(!forUpdate){
            saveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                save(nameEditText.getText().toString());
                }
            });

            retrieveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getListTypes();
                }
            });
        }

        else{

            nameEditText.setText(adapter.getSelectedItemName());

            saveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    update(nameEditText.getText().toString());
                 }
            });

            retrieveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getListTypes();
                }
            });
        }
        d.show();
    }

    private void save(String name){
        lvAdapter db=new lvAdapter(this);
        db.openDB();
        boolean saved=db.add(name);

        if(saved){
            nameEditText.setText("");
            getListTypes();
        }
        else{
            Toast.makeText(this,"Unable to save",Toast.LENGTH_SHORT).show();
        }
    }

    private void getListTypes() {
        lvtypes.clear();
        listView = (ListView) findViewById(R.id.listView);
        lvAdapter db = new lvAdapter(this);
        registerForContextMenu(listView);

        db.openDB();

        Cursor c = db.retrieve();
        listTypes lt = null;
        while (c.moveToNext()) {
            int id = c.getInt(0);
            String name = c.getString(1);
            lt = new listTypes();
            lt.setId(id);
            lt.setName(name);
            lvtypes.add(lt);
        }

        db.closeDB();
        listView.setAdapter(adapter);
    }

    public void update(String newName)
    {
        int id=adapter.getSelectedItemID();
        lvAdapter db=new lvAdapter(this);
        db.openDB();
        boolean updated=db.update(newName,id);
        db.closeDB();

        if(updated){
            nameEditText.setText(newName);
            getListTypes();
        }else{
            Toast.makeText(this,"Unable To Update",Toast.LENGTH_SHORT).show();
        }
    }

    public void delete()
    {
        int id=adapter.getSelectedItemID();
        lvAdapter db=new lvAdapter(this);
        db.openDB();
        boolean deleted=db.delete(id);
        db.closeDB();

        if(deleted){
            getListTypes();
        }else{
            Toast.makeText(this,"Unable To delete",Toast.LENGTH_SHORT).show();
        }
    }
}