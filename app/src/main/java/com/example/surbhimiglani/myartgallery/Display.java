package com.example.surbhimiglani.myartgallery;

import android.graphics.Point;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.ShareActionProvider;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;


public class Display extends AppCompatActivity {

    Toolbar toolbar2;
    private String jString;
    ShareActionProvider shareActionProvider;
    Menu menu;
    File imageFile;
    Uri imageUri;
    private String path;
    private final int THUMBSIZE=300;
    TextView textView2;
    BUG image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        sendBroadcast(new android.content.Intent("com.example.surbhimiglani.bugfinder1"));
        image=new BUG();
        path = getIntent().getExtras().getString("IMAGE");
        textView2=(TextView) findViewById(R.id.textView2);
        ImageView imageView4 = (ImageView)findViewById(R.id.imageView4);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            jString = extras.getString("IMAGE");
        }
        android.view.Display display = getWindowManager().getDefaultDisplay();
        image=getMyImage(jString);
        if(image!=null)
            textView2.setText(image.toString());
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        if(image.getPath()!=null)
            imageView4.setImageBitmap(ImageResizer
                    .decodeSampledBitmapFromFile(image.getPath(), width, height));
    }

    private BUG getMyImage(String image) {
        try {
            JSONObject job = new JSONObject(image);
            return (new BUG(job.getString("title"),
                    job.getString("path")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
