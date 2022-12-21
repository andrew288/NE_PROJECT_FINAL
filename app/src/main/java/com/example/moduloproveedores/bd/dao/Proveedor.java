package com.example.moduloproveedores.bd.dao;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "proveedor",
        foreignKeys = {
                @ForeignKey(entity = Pais.class,
                        parentColumns = "id",
                        childColumns = "idPais"),
                @ForeignKey(entity = TipoProveedor.class,
                        parentColumns = "id",
                        childColumns = "idTipPro"),
                @ForeignKey(entity = EstadoRegistro.class,
                        parentColumns = "id",
                        childColumns = "idEstReg")
        })
public class Proveedor {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nombre;
    private String RUC;
    @NonNull
    private int idTipPro;
    @NonNull
    private int idPais;
    @NonNull
    private int idEstReg;

    public Proveedor(String nombre, String RUC, int idTipPro, int idPais, int idEstReg) {
        this.nombre = nombre;
        this.RUC = RUC;
        this.idTipPro = idTipPro;
        this.idPais = idPais;
        this.idEstReg = idEstReg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRUC() {
        return RUC;
    }

    public void setRUC(String RUC) {
        this.RUC = RUC;
    }

    public int getIdTipPro() {
        return idTipPro;
    }

    public void setIdTipPro(int idTipPro) {
        this.idTipPro = idTipPro;
    }

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public int getIdEstReg() {
        return idEstReg;
    }

    public void setIdEstReg(int idEstReg) {
        this.idEstReg = idEstReg;
    }
}
