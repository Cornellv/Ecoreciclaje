package com.example.ecoreciclaje;

import androidx.constraintlayout.motion.utils.CustomSupport;

public class ReclajeUsu {
    private Integer id;
    private String reciclaje;
    private String reciclajeCustom;

    public ReclajeUsu(){
    }
    public ReclajeUsu(Integer id, String reciclaje) {
        this.id = id;
        this.reciclaje = reciclaje;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReciclaje() {
        return reciclaje;
    }

    public void setReciclaje(String reciclaje) {
        this.reciclaje = reciclaje;
    }

    @Override
    public String toString() {
        return reciclaje;
    }
}
