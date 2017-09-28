package com.example.surbhimiglani.myartgallery;

/**
 * Created by Surbhi Miglani on 27-04-2017.
 */

public class BUG {
    private int ID;
    private String title;
    private byte[] image;
    private String path;


    public BUG() {

    }

    public BUG(String title, String path) {
        this.title = title;
        this.image = image;
        this.path = path;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getPath(){ return path;}

    public void setPath(String path){ this.path=path;}

}
