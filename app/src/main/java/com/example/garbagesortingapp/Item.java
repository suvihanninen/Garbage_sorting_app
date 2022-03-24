package com.example.garbagesortingapp;

public class Item {

    private String garbage = null;
    private String where = null;



    //constructor for a item
    public Item(String garbage, String where) {
       this.garbage = garbage;
       this.where = where;
    }

    public String toString() {
        return oneLine(garbage  + "should be placed in: " + where);
    }

    public String getGarbage() {
        return garbage;
    }

    public void setGarbage(String garbage) {
        this.garbage = garbage;
    }

    public String getWhat() {
        return garbage;
    }


    public String getWhere() {
        return where;
    }


    public void setWhere(String where) {
        this.where = where;
    }

    public String oneLine(String pre) {
        return pre+garbage;
    }

}
