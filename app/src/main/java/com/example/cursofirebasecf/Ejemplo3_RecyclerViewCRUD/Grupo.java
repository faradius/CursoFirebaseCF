package com.example.cursofirebasecf.Ejemplo3_RecyclerViewCRUD;

public class Grupo {
    public int id;
    public String groupnanme;

    public Grupo() {
        super();
    }

    public Grupo(int id, String groupnanme) {
        this.id = id;
        this.groupnanme = groupnanme;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupnanme() {
        return groupnanme;
    }

    public void setGroupnanme(String groupnanme) {
        this.groupnanme = groupnanme;
    }
}
