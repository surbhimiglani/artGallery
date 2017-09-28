package com.example.surbhimiglani.myartgallery;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import com.example.surbhimiglani.myartgallery.listAdapter.lvDatabase;
import com.google.gson.Gson;

public class Layout_0 extends AppCompatActivity {

        ImageView imageView2;
        int REQUEST_CODE_GALLERY = 999;
        ArrayList<BUG> images=new ArrayList<>();
        DAOB helper;
        IMadapter imadapter;
        DBhelper dbhelp;
        EditText editText2;
        Toolbar mtoolbar;
        BUG image;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_layout_0);

            Button button3 = (Button) findViewById(R.id.button3);
            editText2 = (EditText) findViewById(R.id.editText2);
            final ListView listView = (ListView) findViewById(R.id.listView);
            initDB();
            dbhelp=new DBhelper(this);

            imadapter=new IMadapter(this,images);
            listView.setAdapter(imadapter);
            mtoolbar=(Toolbar) findViewById(R.id.toolbar);

            Bundle bundle=getIntent().getExtras();
            if(bundle!=null){
                mtoolbar.setTitle(bundle.getString("ARTtypes"));
            }

            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ActivityCompat.requestPermissions(
                            Layout_0.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_CODE_GALLERY
                    );
                }
            });

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 image = (BUG) listView.getItemAtPosition(i);
                 Intent intent = new Intent(getBaseContext(), Display.class);
                 intent.putExtra("IMAGE", (new Gson()).toJson(image));
                 startActivity(intent);
             }
         });

        }

    private void initDB() {
        helper = new DAOB(this);
        //        add images from database to images ArrayList
        for (BUG mi : helper.getImages()) {
            images.add(mi);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CODE_GALLERY) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent k = new Intent(Intent.ACTION_PICK,  android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                k.setType("image/*");
                startActivityForResult(k, REQUEST_CODE_GALLERY);
            } else {
                Toast.makeText(getApplicationContext(), "you dont have permission to access this image", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();

            BUG image=new BUG();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver()
                    .query(uri, filePathColumn, null, null,
                            null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);

            String picturePath = cursor.getString(columnIndex);

            cursor.close();

            image.setTitle(editText2.getText().toString().trim());
            image.setPath(picturePath);

            helper.addImage(image);

        }
     }
  }

