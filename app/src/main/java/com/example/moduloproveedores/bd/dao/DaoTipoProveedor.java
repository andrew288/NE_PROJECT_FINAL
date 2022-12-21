package com.example.moduloproveedores.bd.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DaoTipoProveedor {

    @Query("SELECT * FROM tipo_proveedor")
    List<TipoProveedor> getTiposProveedor();

    @Query("SELECT * FROM tipo_proveedor WHERE id= :id")
    TipoProveedor getTipoProveedorById(int id);

    @Insert
    void insertTipoProveedor(TipoProveedor...tipoProveedors);

    @Update
    public void updateTipoProveedor(TipoProveedor...tipoProveedors);

    @Delete
    public void deleteTipoProveedor(TipoProveedor...tipoProveedors);
}
