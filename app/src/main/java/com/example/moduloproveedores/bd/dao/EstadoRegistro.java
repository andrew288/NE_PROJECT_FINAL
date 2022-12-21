package com.example.moduloproveedores.bd.dao;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "estado_registro")
public class EstadoRegistro {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String description;
    private char estReg;

    public EstadoRegistro(String description, char estReg) {
        this.description = description;
        this.estReg = estReg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public char getEstReg() {
        return estReg;
    }

    public void setEstReg(char estReg) {
        this.estReg = estReg;
    }
}
