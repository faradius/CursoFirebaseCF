package com.example.cursofirebasecf.Ejemplo3_RecyclerViewCRUD;

public class Grupo {
    public int id;
    public String groupname;

    public Grupo() {
        super();
    }

    public Grupo(int id, String groupnanme) {
        this.id = id;
        this.groupname = groupnanme;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }
}
