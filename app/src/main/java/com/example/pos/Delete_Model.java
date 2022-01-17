package com.example.pos;

public class Delete_Model {

    private String sname;
    private String ssubject;
    private int id;

    //constructor
    public Delete_Model(String sname, String ssubject){
        this.sname = sname;
        this.ssubject = ssubject;
    }

    //getter and setter


    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSsubject() {
        return ssubject;
    }

    public void setSsubject(String ssubject) {
        this.ssubject = ssubject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }




}
