package com.example.moduloproveedores.bd.dao;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "pais",
        foreignKeys = {
                @ForeignKey(entity = EstadoRegistro.class,
                        parentColumns = "id",
                        childColumns = "idEstReg")
        })
public class Pais {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    @NonNull
    private int idEstReg;

    public Pais(String name, int idEstReg) {
        this.name = name;
        this.idEstReg = idEstReg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdEstReg() {
        return idEstReg;
    }

    public void setIdEstReg(int idEstReg) {
        this.idEstReg = idEstReg;
    }
}
