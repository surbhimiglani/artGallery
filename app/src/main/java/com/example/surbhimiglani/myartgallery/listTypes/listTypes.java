package com.example.surbhimiglani.myartgallery.listTypes;

/**
 * Created by Surbhi Miglani on 17-06-2017.
 */

public class listTypes {
    String name;
    int id;

    public listTypes(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public listTypes() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
